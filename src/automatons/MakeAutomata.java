package automatons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Map.Entry;
import java.util.TreeSet;

public class MakeAutomata {

	//Automaton automaton;
	public MakeAutomata() {	
		
	}

	/**
	 * generates an Automaton that accepts all words
	 * note that this Automaton is not minimal! It has 2 States where only 1 
	 * would be needed, done for testing the minimization algorithm.
	 * @param varSet
	 * @return
	 */
	public static Automaton FromString(String s)
	{
		// split into 4 Strings: edges, finalstates, startstate
		// variables
		String[] a = s.split("\n");
		ArrayList<String> edges = new ArrayList<String>();
		ArrayList<String> finals = new ArrayList<String>();
		String s_s = "";
		for(String line : a) {
			if(line.contains("->")) {
				edges.add(line);
			} else if(line.contains("peripheries")) {
				finals.add(line);
			} else if(line.contains("diamond")) {
				s_s = line;
			}
		}
		String s_v = s.split("\\Q}\\E\n")[1];
		
		Automaton A = new Automaton();
		
		// startstate
		
		A.startState = s_s.split("\\Q[\\E")[0];
		
		A.variables = new TreeSet<String>();
		
		// variables
		for(int i=0; i<s_v.length(); i++)
			A.variables.add(s_v.substring(i,i+1)); // test
		
		// final states:
		for(String f : finals) {
			A.finalState.add(f.split("\\Q[\\E")[0]);
		}

		// edges
		for(String edge : edges) {
			String[] parts = edge.split("\\Q[\\E");
			String from, to;
			from = parts[0].split(" -> ")[0].trim();
			to = parts[0].split(" -> ")[1].trim();
			Transitions t = new Transitions();
			HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
			A.trans.put(from, hm);
			hm.put(to, t);
			String[] labels = (((parts[1].split("\""))[1]).trim()).split(" ");
			for(String label : labels) {
				boolean[] e = new boolean[label.length()];
				for(int i=0; i<label.length(); i++) {
					e[i] = label.charAt(i)=='1';
				}
				t.addTransition(e);
			}
		}

		return A;
	}

	
	
	/**
	 * generates an Automaton that accepts all words
	 * note that this Automaton is not minimal! It has 2 States where only 1 
	 * would be needed, done for testing the minimization algorithm.
	 * @param varSet
	 * @return
	 */
	public static Automaton UniversalAutomation(SortedSet<String> varSet)
	{
		Automaton A = new Automaton();
		A.startState = "1";
		A.finalState.add(A.startState);
		A.variables = new TreeSet<String>();
		A.variables.addAll(varSet);

		Transitions t = generateAllCombinations(varSet.size());
		HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
		String state2 = "2";
		A.finalState.add(state2);
		hm.put(state2,t);
		A.trans.put(A.startState, hm);

		Transitions t2 = generateAllCombinations(varSet.size());
		HashMap<String,Transitions> hm2 = new HashMap<String,Transitions>();
		hm2.put(state2,t2);

		
		A.trans.put(state2, hm2);
		return A;
	}
	
	@SuppressWarnings("unused")
	private static Transitions generateAllCombinations(int size) {
		// TODO Auto-generated method stub
		Transitions t = new Transitions();
		
		for(int i=0; i<1 << size; ++i) {
			boolean[] e = new boolean[size];
			int tmp = i;
			for(int j=0; j<size; ++j) {
				e[j] = tmp%2==0;
				tmp = tmp >> 1;
			}
			boolean ret = t.StateTransitionVector.add(e);			
		}
		
		return t;
	}

	public static Automaton EmptyAutomation(SortedSet<String> varSet)
	{
		Automaton A = new Automaton();
		A.startState = "1";
		A.finalState.add(A.startState);
		A.variables = varSet;

		Transitions t = generateAllCombinations(varSet.size());
		
		HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
		hm.put(A.startState,t);
		A.trans.put(A.startState, hm);
		return A;
	}
	
	/**
	 * determines the total variables for both the automatons A1 and A2.
	 * @param A1, A2
	 * @return varset
	 */
	public SortedSet<String> TotalVariableSet(SortedSet<String> A1Variables, SortedSet<String> A2Variables) {
		SortedSet<String> varset = new TreeSet<String>();
		varset.addAll(A1Variables);
		for(String var :A2Variables)
		{
			if(!varset.contains(var))
				varset.add(var);
		}
		return varset;
	}
	

	// needed Methods
	
	/**
	 * extend the free variables to the varSet
	 * 
	 * invariant: variables subset of varSet
	 * @param varSet
	 * @return
	 */
	public static Automaton extendTo(Automaton A, SortedSet<String> varSet){
		
		int[] transform = new int[varSet.size()];
		{
			int i=0;
			for(String s : varSet) {
				int j=0; 
				for(String s2 : A.variables) {
					if(s.equals(s2))
						break;
					j++;
				}
				if(j==A.variables.size()) 
					transform[i++] = -1; 
				else
					transform[i++] = j;
			}
		}		
		
		A.variables = new TreeSet<String>();
		A.variables.addAll(varSet);
		
		for(Map.Entry<String, HashMap<String,Transitions>> transition : A.trans.entrySet()) {
			for(Map.Entry<String, Transitions> entry : transition.getValue().entrySet()) {
				entry.getValue().extendTo(transform); 
			}
		}	

		
		
		return A;
	}
	
	

	
	/**
	 * determines the Union of A1 automaton with A2
	 * to that end first calculates the extensions and than
	 * uses the private method AutomatonUnion
	 * @param A1, A2
	 * @return
	 */
	public Automaton Union(Automaton A1, Automaton A2) {
		//TODO
		//SortedSet<String> varset = merge(this.variables, A2.variables);
		//A2 = A2.extendTo(varset);
		//extendTo(varset);
			
		Automaton A1UnionA2 = AutomatonUnion(A1, A2);
		return A1UnionA2;
	}
	
	/**
	 * determines the Union of A1 automaton and A2,
	 * assuming that they operate on the same variable set
	 * @param A1, A2
	 * @return
	 */
	public Automaton AutomatonUnion(Automaton A1, Automaton A2) {
		//TODO
		//...
		Automaton A1UnionA2 = ProductAutomaton(A1, A2, true); // boolUnion is set to true for Union operation
		
		return A1UnionA2;
	}
	
	/**
	 * determines the Product Automaton of A1 and A2,
	 * assuming that they operate on the same variable set
	 * @param A1, A2, boolUnion -> to determine the set of final States and is set to True for Union and False for Intersection.
	 * @return
	 */
	public Automaton ProductAutomaton(Automaton oldA1, Automaton oldA2, boolean boolUnion) {
		
		Automaton A =  new Automaton();
		A.variables = TotalVariableSet(oldA1.variables, oldA2.variables);
		
		Automaton A1 = MakeAutomata.extendTo(oldA1, A.variables);
		Automaton A2 = MakeAutomata.extendTo(oldA2, A.variables);
		
		// Set the Start state of the product automation
		A.startState = A1.startState.toString() + A2.startState.toString();
		String oldAState = null;
		String newAState = null;
		
		for (Entry<String, HashMap<String, Transitions>> entryA1 : A1.trans.entrySet()) {
		    String keyFromStateA1 = entryA1.getKey();
		    HashMap<String, Transitions> valueA1 = entryA1.getValue();
		    
		    
		    for (Entry<String, HashMap<String, Transitions>> entryA2 : A2.trans.entrySet()) {
			    String keyFromStateA2 = entryA2.getKey();
			    if(!keyFromStateA2.equals(keyFromStateA1))
			    	continue;
			    HashMap<String, Transitions> valueA2 = entryA2.getValue();
			    
			    for (Entry<String, Transitions> entryTransitionsA1 : valueA1.entrySet()) {
				    String keyToStateA1 = entryTransitionsA1.getKey();
				    Transitions valueTransitionsA1 = entryTransitionsA1.getValue();
				    
				    for (Entry<String, Transitions> entryTransitionsA2 : valueA2.entrySet()) {
					    String keyToStateA2 = entryTransitionsA2.getKey();
					    Transitions valueTransitionsA2 = entryTransitionsA2.getValue();
				
					    oldAState = keyFromStateA1 + keyFromStateA2;
						newAState = keyToStateA1 + keyToStateA2;
						HashMap<String, Transitions> newATransition = new HashMap<String, Transitions>();
						
					    if(valueTransitionsA1==null)
					    {
					    	newATransition.put(newAState, valueTransitionsA2);
					    }
					    else if(valueTransitionsA2==null)
					    {
					    	newATransition.put(newAState, valueTransitionsA1);
					    }
					    else
					    {
					    	for( boolean[] t1 : valueTransitionsA1.StateTransitionVector)
						    {
						    	for( boolean[] t2 : valueTransitionsA2.StateTransitionVector)
							    {
						    		if(Arrays.equals(t1, t2))
								    {
						    			newATransition.put(newAState, valueTransitionsA1);
						    			A.trans.put(oldAState, newATransition);
										if(boolUnion) //For Union of 2 automata, Product automaton has a final state if either of the 2 automata has a final state.
										{
											if(A1.finalState.contains(keyToStateA1) || A2.finalState.contains(keyToStateA2))
											{
												if(!A.finalState.contains(newAState))
													A.finalState.add(newAState);
												break;
											}
										}
										else //For Intersection of 2 automata, Product automaton has a final state if and only if both the automata have final states.
										{
											if(A1.finalState.contains(keyToStateA1) && A2.finalState.contains(keyToStateA2))
											{
												if(!A.finalState.contains(newAState))
													A.finalState.add(newAState);
												break;
											}
										}
								    }// else continue to check for next transition for same state in A2
							    }
						    }
					    }
			    
			    }// Check for next transition for same state in A1 // End of innermost for loop for Transitions of A2
			    
			}// Check for next transition for same state in A1 // End of second last for loop for Transitions of A1
		    
		}// Check transitions for next state in A2 // End of second for loop of A2

	}// Check transitions for next state in A1 // End of outermost and first for loop of A1

		
		return A;
	}
	
	
	/**
	 * determines the Intersection of A1 automaton with A2
	 * to that end first calculates the extensions and than
	 * uses the private method AutomatonIntersection
	 * @param A1, A2
	 * @return
	 */
	public Automaton Intersect(Automaton A1, Automaton A2) {
	
		Automaton A1IntersectA2 = AutomatonIntersection(A1, A2);
		return A1IntersectA2;
	}
	
	
	public Automaton AutomatonIntersection(Automaton A1, Automaton A2) {
		//TODO
		//...
		Automaton A1IntersectA2 = ProductAutomaton(A1, A2, false); // boolUnion is set to false for Intersection operation
		
		return A1IntersectA2;
	}
	
	/**
	 * projects away the variable in variableName
	 * TODO: Take Care !! this method alters the automaton A1 !!
	 * @param A1, variableName
	 */
	public Automaton Project(Automaton A1, String variableName) {
		
		if(!A1.variables.contains(variableName)) {
			System.out.println("ERROR: variable not in Set of free Variables!");
			return A1;
		}
		int index=0;
		for(String v : A1.variables) {
			if(v.equals(variableName))
				break;
			index++;
		}
		
		for(Map.Entry<String, HashMap<String,Transitions>> transition : A1.trans.entrySet()) {
			for(Map.Entry<String, Transitions> entry : transition.getValue().entrySet()) {
				entry.getValue().project(index); 
			}
		}	
		return A1;
	}
		
	
	
	/**
	 * complements the Automaton (Assuming that the input automation is a DFA)
	 */
	public Automaton Complement(Automaton A) {
		Automaton AComplement = new Automaton();
		AComplement.startState = A.startState;
		AComplement.variables = A.variables;
		AComplement.trans = A.trans;
		
		for (Entry<String, HashMap<String, Transitions>> entryA : A.trans.entrySet()) 
		{
		    String keyFromStateA = entryA.getKey();
			if(!(A.finalState.contains(keyFromStateA)))
			{
				if(!AComplement.finalState.contains(keyFromStateA))
					AComplement.finalState.add(keyFromStateA);
				// break; why a break here???? @max thinks that's wrong 
			}
		}		
		return AComplement;
	}

	
	
	/**
	 * exports the automaton in the dotty-format (visualize e.g. with GraphViz)
	 * @return
	 */
	public String ToString( Automaton a) {
		String list_of_edges = "";
		ArrayList<String> edgelist = new ArrayList<String>();
		for(Map.Entry<String, HashMap<String,Transitions>> transition : a.trans.entrySet()) {
			String from = transition.getKey();
			for(Map.Entry<String, Transitions> entry : transition.getValue().entrySet()) {
				String to = entry.getKey();
				edgelist.add(from + " -> " + to + " [label=\"" +entry.getValue().toString() + "\"];"); 
			}
		}	
		Collections.sort(edgelist);
		for(String edge : edgelist) {
			list_of_edges += "\n" + edge;
		}
					
		// final states
		String list_of_final_states = "";
		ArrayList<String> f = (ArrayList<String>)a.finalState.clone();
		Collections.sort(f);
		for(String s : f) { // TODO: make sure its sorted
			list_of_final_states += "\n" + s + "[peripheries=2];";
		}
		
		// initial state
		String initial_state = "\n" + a.startState + "[shape=<diamond>];\n";
		
		// free variables
		String list_of_free_variables = "";
		for(String v : a.variables) { 
			list_of_free_variables += v;
		}
		
		if(a.variables.size()==0) list_of_free_variables = "true";
		return "digraph G {" + list_of_edges 
						+ list_of_final_states 
						+ initial_state
						+ "}\n"
						+ list_of_free_variables;
	}


	
	// TEST FUNCTIONS:
	
	/**
	 * decides if a word is accepted by this automaton 
	 * @param word
	 * @return
	 * @author max
	 */
	public static boolean member(Automaton A, ArrayList<boolean[]> word) {
		
		return accepts(A, A.startState, word);
	}
	
	
	/**
	 * decides if a word is accepted from @state by this automaton 
	 * @param word
	 * @return
	 * @author max
	 */
	public static boolean accepts(Automaton A, String state, ArrayList<boolean[]> word) {
		
		LinkedList<String> queue = new LinkedList<String>();
		LinkedList<String> queue2;
		queue.add(state);
		int i=0;
		for(; i<word.size() && !queue.isEmpty(); ++i) {
			queue2 = new LinkedList<String>();
			boolean[] label = word.get(i); // the ith character of the word
			while(!queue.isEmpty()) {
				String currentstate = queue.poll();
				// go through all edges from currentstate labelled label
				add_all_labelled_edges(A, currentstate, label, queue2);
			}
			queue = queue2;
		}
		if(word.size()<i || queue.isEmpty()) return false;
		while(!queue.isEmpty()) {
			String currentstate = queue.poll();
			if(isfinal(A, currentstate)) return true;
		}
		return false;
	}

	/**
	 * returns whether currentstate is a finalstate
	 * @param A
	 * @param currentstate
	 * @return
	 * @author max
	 */
	private static boolean isfinal(Automaton A, String currentstate) {
		return A.finalState.contains(currentstate);
	}

	/**
	 * goes through all edges and adds the ones labeled with "label"
	 * to the queue
	 * TODO: quite inefficient, but it should work!
	 * @param currentstate
	 * @param label
	 * @param q
	 * @author max
	 */
	private static void add_all_labelled_edges(Automaton A, String currentstate, boolean[] label,
			LinkedList<String> q) {

		HashMap<String, Transitions> trans = A.trans.get(currentstate);
		if(trans==null) {
			// has no outgoing edges
			return;
		}
		for(Entry<String, Transitions> entry: trans.entrySet()) {
			for(boolean[] word : entry.getValue().StateTransitionVector) {
				if(label.length==word.length) {
					int i=0;
					for(; i<label.length; ++i) {
						if(label[i]!=word[i]) break;
					}	
					if(i==label.length)
						q.add(entry.getKey());
				}
			}
		}
	}		

	
	
	
	//NFA with 3 variables
		public static Automaton CreateStubNFAWith3Variables(SortedSet<String> varSet)
		{
			Automaton A = new Automaton();
			A.startState = "1";
			A.variables = varSet;

			Transitions t = generateAllCombinations(varSet.size());
			
			HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
			String state2 = "2";
			hm.put(state2,t);
			A.trans.put(A.startState, hm);
			A.finalState.add(state2);
			
			HashMap<String,Transitions> hm2 = new HashMap<String,Transitions>();
			String state3 = "3";
			hm2.put(state3,t);
			
			String state4 = "4";
			hm2.put(state4,t);
			A.trans.put(state2, hm2);
			A.finalState.add(state4);
			
			HashMap<String,Transitions> hm3 = new HashMap<String,Transitions>();
			hm3.put(state4,t);
			A.trans.put(state3, hm3);
			
			HashMap<String,Transitions> hm4 = new HashMap<String,Transitions>();
			String state5 = "5";
			hm4.put(state5,t);
			A.trans.put(state4, hm4);
			
			HashMap<String,Transitions> hm5 = new HashMap<String,Transitions>();
			hm5.put(state3,t);
			hm5.put(A.startState,t);
			A.trans.put(state5, hm5);
			
			return A;
		}
		
		//NFA with 2 variables
		public static Automaton CreateStubNFAWith2Variables(SortedSet<String> varSet)
		{
			Automaton A = new Automaton();
			A.startState = "1";
			A.variables = varSet;

			Transitions t1 = new Transitions();
			Transitions t2 = new Transitions();
			Transitions t3 = new Transitions();
			Transitions t4 = new Transitions();
			boolean[] e1 = new boolean[2];
			boolean[] e2 = new boolean[2];
			boolean[] e3 = new boolean[2];
			boolean[] e4 = new boolean[2];
			
			
			e1[0] = false;
			e1[1] = false;
			t1.StateTransitionVector.add(e1);
			
			e2[0] = false;
			e2[1] = true;
			t2.StateTransitionVector.add(e2);
			
			e3[0] = true;
			e3[1] = false;
			t3.StateTransitionVector.add(e3);
			
			e4[0] = true;
			e4[1] = true;
			t4.StateTransitionVector.add(e4);

			Transitions t5 = new Transitions();
			t5.StateTransitionVector.add(e1);
			t5.StateTransitionVector.add(e2);
			t5.StateTransitionVector.add(e3);
			t5.StateTransitionVector.add(e4);
			

			Transitions t6 = new Transitions();
			t6.StateTransitionVector.add(e2);
			t6.StateTransitionVector.add(e4);
			
			HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
			String state2 = "2";
			hm.put(state2,t1);
			A.trans.put(A.startState, hm);
			A.finalState.add(state2);
			
			HashMap<String,Transitions> hm2 = new HashMap<String,Transitions>();
			String state3 = "3";
			hm2.put(state3,t6);
			String state4 = "4";
			hm2.put(state4,t5);
			A.trans.put(state2, hm2);
			
			HashMap<String,Transitions> hm3 = new HashMap<String,Transitions>();
			hm3.put(state4,t6);
			A.trans.put(state3, hm3);
			
			HashMap<String,Transitions> hm5 = new HashMap<String,Transitions>();
			hm5.put(state2,t6);
			hm5.put(state3,t2);
			A.trans.put(state4, hm5);
			A.finalState.add(state4);
			
			return A;
		}
		
		//NFA with 2 variables with Dead and Unreachable States.
		public static Automaton CreateStubNFAWithDeadAndUnreachableStates(SortedSet<String> varSet)
		{
			Automaton A = new Automaton();
			A.startState = "1";
			A.variables = varSet;

			Transitions t1 = new Transitions();
			Transitions t2 = new Transitions();
			Transitions t3 = new Transitions();
			Transitions t4 = new Transitions();
			boolean[] e1 = new boolean[2];
			boolean[] e2 = new boolean[2];
			boolean[] e3 = new boolean[2];
			boolean[] e4 = new boolean[2];
			
			e1[0] = false;
			e1[1] = false;
			t1.StateTransitionVector.add(e1);
			
			e2[0] = false;
			e2[1] = true;
			t2.StateTransitionVector.add(e2);
			
			e3[0] = true;
			e3[1] = false;
			t3.StateTransitionVector.add(e3);
			
			e4[0] = true;
			e4[1] = true;
			t4.StateTransitionVector.add(e4);

			Transitions t5 = new Transitions();
			t5.StateTransitionVector.add(e1);
			t5.StateTransitionVector.add(e2);
			t5.StateTransitionVector.add(e3);
			t5.StateTransitionVector.add(e4);
			

			Transitions t6 = new Transitions();
			t6.StateTransitionVector.add(e2);
			t6.StateTransitionVector.add(e4);
			
			HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
			String state2 = "2";
			hm.put(state2,t1);
			A.trans.put(A.startState, hm);
			A.finalState.add(state2);
			
			HashMap<String,Transitions> hm2 = new HashMap<String,Transitions>();
			String state3 = "3";
			hm2.put(state3,t6);
			A.trans.put(state2, hm2);
			
			String state4 = "4";
			HashMap<String,Transitions> hm5 = new HashMap<String,Transitions>();
			hm5.put(state2,t6);
			hm5.put(state3,t2);
			A.trans.put(state4, hm5);
			A.finalState.add(state4);
			
			return A;
		}
		
		
		
		
		
		/**
		 * Converts the given NFA to a DFA.
		 */
		public Automaton ConvertNFAToDFA(Automaton aNFA) 
		{
			Automaton aDFA = new Automaton();
			aDFA.startState = aNFA.startState;
			aDFA.variables = aNFA.variables;
			
			int countOfNFAStates = aNFA.trans.size();
			double maxCountOfDFAStates = Math.pow(2, countOfNFAStates);
			
			//The PowerSet List can be avoided but used to check if state is already added or processed. It also helps to note down the states in the power set added till now for debugging purposes.
			ArrayList<String> powerSetStateList = new ArrayList<String>();
			//Keeps a track of all unprocessed states and iterates until it is empty.
			Queue<String> queueDFANewUnprocessedStateSet = new LinkedList<String>();
			String oldNFAState  = "";
			
			 //We use this to put an individual transition as a key with multiple states as its value separated by "_".
			Map<boolean[], String> aDFATSet = new HashMap<boolean[], String>();
			
			Transitions newTransitionVector = new Transitions();
			 //We use this to put together all the computed transitions for each state in aDFATSet.
			Map<Transitions, String> aDFATransitionSet = new HashMap<Transitions, String>();
			
			
			//We iterate only once using the below for-loop to get the first state of the NFA. It has constant run-time complexity.
			//Moreover, it makes sure that only reachable states from the Start state are processed.
			for (Entry<String, HashMap<String, Transitions>> entryaNFA : aNFA.trans.entrySet()) 
			{
			    String keyFromStateaNFA = entryaNFA.getKey();
			    HashMap<String, Transitions> valueaNFA = entryaNFA.getValue();
			    oldNFAState = keyFromStateaNFA;
				
			    for (Entry<String, Transitions> entryTransitionsaNFA : valueaNFA.entrySet())
			    {
			    	String keyToStateaNFA = entryTransitionsaNFA.getKey();
				    Transitions valueTransitionsaNFA = entryTransitionsaNFA.getValue();
				    
				    for(boolean[] t : valueTransitionsaNFA.StateTransitionVector)
				    {
				    	if(aDFATSet.containsKey(t))
					    {
					    	String newDFAState = aDFATSet.get(t);
					    	if(!newDFAState.contains(keyToStateaNFA.toString().trim()))
					    	{
					    		if(newDFAState.contains("_"))
							    {
							    	String[] newIndividualStates = new String[(int) maxCountOfDFAStates];
							    	newIndividualStates = newDFAState.split("_");
							    	ArrayList<Integer> intnewIndividualStates = new ArrayList<Integer>(); 
							    	
							    	for(String nxtIndividualState : newIndividualStates)
								    {
							    		intnewIndividualStates.add(Integer.parseInt(nxtIndividualState));
							    	}
							    	
							    	intnewIndividualStates.add(Integer.parseInt(keyToStateaNFA));
							    	Collections.sort(intnewIndividualStates); //Sort to keep it as same state. Example: 1_2_3 and 2_3_1 are the same states and represented by only 1_2_3.
							    	
							    	newDFAState = "";
							    	for(Integer i : intnewIndividualStates)
							    	{
							    		if(newDFAState == "")
							    			newDFAState += i;
							    		else
							    			newDFAState += "_" + i;
							    	}
							    }
							    else
							    {
							    	if(Integer.parseInt(keyToStateaNFA) < Integer.parseInt(newDFAState))
							    		newDFAState = keyToStateaNFA + "_" + newDFAState;
							    	else
							    		newDFAState += "_" +keyToStateaNFA;
							    }
							    aDFATSet.remove(t);
						    	aDFATSet.put(t, newDFAState);
						    }
					    }
					    else // for the first transition and when there are intermediate single transitions
					    {
					    	aDFATSet.put(t, keyToStateaNFA);
					    }	
				    }		
			    }
				break;
			}
			
			//Add the missing transitions to a dead state as it is a DFA.
		    String deadState = "0";
		    //Generate all transitions. 
		    Transitions tAllTransitions = generateAllCombinations(aDFA.variables.size());
		    //If we have a dead state in the DFA, we need to add all the transitions emerging from it to itself.
		    Transitions copyOftAllTransitions = generateAllCombinations(aDFA.variables.size());
		    
			//Iterate through all the computed transitions in aDFATSet and add it based on the state.
			//Note that Transition set has String i.e. the state name as its key.
			for(String nxtState : aDFATSet.values())
		    {
				if(aDFATransitionSet.containsValue(nxtState))
					 continue;
				for( boolean[] transition : aDFATSet.keySet())
			    {
					String returnedState = aDFATSet.get(transition);
					if(nxtState.equalsIgnoreCase(returnedState))
						newTransitionVector.StateTransitionVector.add(transition);
					
					//Remove all the matching transitions so as to arrive at the missing ones to be pointed at the dead state.
					if(tAllTransitions.StateTransitionVector.contains(transition))
						tAllTransitions.StateTransitionVector.remove(transition);
			    }
				aDFATransitionSet.put(newTransitionVector, nxtState);
				newTransitionVector = new Transitions();
		    }
			
			//Check if there are missing transitions. If yes, then add them, pointing to the dead state.
		    if(!tAllTransitions.StateTransitionVector.isEmpty())
		    {
		    	//Pointed all the missing transitions to the dead state.
		    	aDFATransitionSet.put(tAllTransitions, deadState);

				//As we are guaranteed about a dead state in the DFA. Lets add all the transitions emerging from it to itself.
		    	HashMap<String, Transitions> deadStateDFATransition = new HashMap<String, Transitions>();
		    	deadStateDFATransition.put(deadState, copyOftAllTransitions);
				aDFA.trans.put(deadState, deadStateDFATransition);
				powerSetStateList.add(deadState);
		    }
			
			aDFATSet.clear();
			
			powerSetStateList.add(oldNFAState);
			
			HashMap<String, Transitions> newFirstDFATransitions = new HashMap<String, Transitions>();
		    
			//Add this newly created Transition Set into the NFA Transition map created above.
			for (Map.Entry<Transitions, String> entry : aDFATransitionSet.entrySet()) 
		    {
		        Transitions multipleTransitions = entry.getKey();
		        String newDFAState = entry.getValue();
		        newFirstDFATransitions.put(newDFAState, multipleTransitions);
				
				for(String finalState : aNFA.finalState)
				{
					if(newDFAState.contains(finalState.toString().trim()))
					{
						if(!aDFA.finalState.contains(newDFAState))
							aDFA.finalState.add(newDFAState);
						break;
					}
				}
				//Add  all unprocessed new states generated from the first state of the NFA which we will iterate later.
				if(!powerSetStateList.contains(newDFAState))
				{
					queueDFANewUnprocessedStateSet.add(newDFAState);
					powerSetStateList.add(newDFAState);
				}
		    }
			//Add all the transitions to the New states from the Old state into the DFA now.
			aDFA.trans.put(oldNFAState, newFirstDFATransitions);
			aDFATransitionSet.clear();
			aDFATSet.clear();
			boolean processNextState = false;
			
			//Process all unprocessed new states generated from the first state of the NFA above.
			//We add only new states and not states which were processed earlier and the while loop is executed until there are no new states.
			while (!queueDFANewUnprocessedStateSet.isEmpty()) 
			{ 
			    String nextUnprocessedState = queueDFANewUnprocessedStateSet.remove();
			    String[] unprocessedIndividualStates = new String[(int) maxCountOfDFAStates];
			    if(nextUnprocessedState.contains("_"))
			    {
			    	unprocessedIndividualStates = nextUnprocessedState.split("_");
			    	processNextState = true;
			    }
			    else
			    {
			    	unprocessedIndividualStates[0] = nextUnprocessedState;
			    	processNextState = false;
			    }
			    
			    oldNFAState = nextUnprocessedState;
			    
			    for(String nxtStateToBeProcessed : unprocessedIndividualStates)
			    {
			    	if(nxtStateToBeProcessed==null)
			    		break;
			    	
			    	if(aNFA.trans.containsKey(nxtStateToBeProcessed))
					{
						for (Entry<String, Transitions> entryTransitionsaNFA : aNFA.trans.get(nxtStateToBeProcessed).entrySet())
					    {
					    	String keyToStateaNFA = entryTransitionsaNFA.getKey();
						    Transitions valueTransitionsaNFA = entryTransitionsaNFA.getValue();
						    
						    for(boolean[] t : valueTransitionsaNFA.StateTransitionVector)
						    {
						    	if(aDFATSet.containsKey(t))
							    {
							    	String newDFAState = aDFATSet.get(t);
							    	
							    	if(!newDFAState.contains(keyToStateaNFA.toString().trim()))
							    	{
							    		if(newDFAState.contains("_"))
									    {
									    	String[] newIndividualStates = new String[(int) maxCountOfDFAStates];
									    	newIndividualStates = newDFAState.split("_");
									    	ArrayList<Integer> intnewIndividualStates = new ArrayList<Integer>(); 
									    	
									    	for(String nxtIndividualState : newIndividualStates)
										    {
									    		intnewIndividualStates.add(Integer.parseInt(nxtIndividualState));
									    	}
									    	
									    	intnewIndividualStates.add(Integer.parseInt(keyToStateaNFA));
									    	Collections.sort(intnewIndividualStates);
									    	
									    	newDFAState = "";
									    	for(Integer i : intnewIndividualStates)
									    	{
									    		if(newDFAState == "")
									    			newDFAState += i;
									    		else
									    			newDFAState += "_" + i;
									    	}
									    }
									    else
									    {
									    	if(Integer.parseInt(keyToStateaNFA) < Integer.parseInt(newDFAState))
									    		newDFAState = keyToStateaNFA + "_" + newDFAState;
									    	else
									    		newDFAState += "_" +keyToStateaNFA;
									    }
									    aDFATSet.remove(t);
								    	aDFATSet.put(t, newDFAState);
								    }
							    }
							    else // for the first transition and when there are intermediate single transitions
							    {
							    	aDFATSet.put(t, keyToStateaNFA);
							    }	
						    }			
					    }
					}
					
			    }
			    
			  //Add the missing transitions to a dead state as it is a DFA.
			  //Generate all transitions.
			  //Note: Can't reuse copyOftAllTransitions which contains the list of all transitions due to properties of the Transition class.
			    tAllTransitions = generateAllCombinations(aDFA.variables.size());
			    
			    processNextState = false;
			    
			    if(!processNextState)
				{
					for(String nxtState : aDFATSet.values())
				    {
						if(aDFATransitionSet.containsValue(nxtState))
							 continue;
						for( boolean[] transition : aDFATSet.keySet())
					    {
							String returnedState = aDFATSet.get(transition);
							if(nxtState.equalsIgnoreCase(returnedState))
								newTransitionVector.StateTransitionVector.add(transition);
							
							//Remove all the matching transitions so as to arrive at the missing ones to be pointed at the dead state.
							if(tAllTransitions.StateTransitionVector.contains(transition))
								tAllTransitions.StateTransitionVector.remove(transition);
							
					    }
						aDFATransitionSet.put(newTransitionVector, nxtState);
						newTransitionVector = new Transitions();
				    }

					  //Check if there are missing transitions. If yes, then add them, pointing to the dead state.
					    if(!tAllTransitions.StateTransitionVector.isEmpty())
					    {
					    	//Pointed all the missing transitions to the dead state.
					    	aDFATransitionSet.put(tAllTransitions, deadState);

							//As we are guaranteed about a dead state in the DFA. Lets add all the transitions emerging from it to itself.
					    	if(!powerSetStateList.contains(deadState))
					    	{
					    		HashMap<String, Transitions> deadStateDFATransition = new HashMap<String, Transitions>();
					    		deadStateDFATransition.put(deadState, copyOftAllTransitions);
					    		aDFA.trans.put(deadState, deadStateDFATransition);
								powerSetStateList.add(deadState);
					    	}
					    }
					
					aDFATSet.clear();
					
					HashMap<String, Transitions> newDFATransitions = new HashMap<String, Transitions>();
				    
					for (Entry<Transitions, String> entry : aDFATransitionSet.entrySet()) 
				    {
				        Transitions multipleTransitions = entry.getKey();
				        String newDFAState = entry.getValue();
				        newDFATransitions.put(newDFAState, multipleTransitions);
						
						for(String finalState : aNFA.finalState)
						{
							if(newDFAState.contains(finalState.toString().trim()))
							{
								if(!aDFA.finalState.contains(newDFAState))
									aDFA.finalState.add(newDFAState);
								break;
							}
						}
						if(!powerSetStateList.contains(newDFAState))
						{
							queueDFANewUnprocessedStateSet.add(newDFAState);
							powerSetStateList.add(newDFAState);
						}
				    }
					//Add all the transitions to the New states from the Old state into the DFA now.
					aDFA.trans.put(oldNFAState, newDFATransitions);
					aDFATransitionSet.clear();
				}
			}
			
			//return aDFA;
			// Remap the given DFA with new state names.
			return DFAWithRemappedStates(aDFA);
		}
		
		
		/**
		 * Remap the given DFA with new state names.
		 */
		public Automaton DFAWithRemappedStates(Automaton A) {
			Automaton ADFAWithRemappedStates = new Automaton();
			ADFAWithRemappedStates.startState = A.startState;
			ADFAWithRemappedStates.variables = A.variables;
			
			Map<String, Integer> mappings = new HashMap<String, Integer>();
			int i = 1;
			for (Entry<String, HashMap<String, Transitions>> entryA : A.trans.entrySet()) 
			{
			    mappings.put(entryA.getKey(), i);
			    i++;
			}	
			
			for (Entry<String, HashMap<String, Transitions>> entry : A.trans.entrySet()) 
			{
			    String keyFromState = entry.getKey();
			    int newFromStateNo = mappings.get(keyFromState);
			    HashMap<String, Transitions> valueaNFA = entry.getValue();
				
			    HashMap<String, Transitions> transitions = new HashMap<String, Transitions>();
			    for (Entry<String, Transitions> entryTransitions : valueaNFA.entrySet())
			    {
			    	String keyToStateaNFA = entryTransitions.getKey();
				    Transitions valueTransitions = entryTransitions.getValue();
				    
				    int newToStateNo = mappings.get(keyToStateaNFA);
				    transitions.put(Integer.toString(newToStateNo) ,valueTransitions);
			    }
			    ADFAWithRemappedStates.trans.put(Integer.toString(newFromStateNo), transitions);
			}
			
			for(String finalState : A.finalState)
			{
				int newToStateNo = mappings.get(finalState);
			    String newfinalState = Integer.toString(newToStateNo);
			    		
				if(!ADFAWithRemappedStates.finalState.contains(newfinalState))
					ADFAWithRemappedStates.finalState.add(newfinalState);
			}
			
			return ADFAWithRemappedStates;
		}
			
}
