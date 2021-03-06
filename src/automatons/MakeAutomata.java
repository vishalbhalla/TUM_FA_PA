package automatons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.Map.Entry;
import java.util.TreeSet;


class Pair<A,B> {

    public Pair(A a, B b) {
    	f=a;
    	s=b;
    }
	public A f;
    public B s;
    
    
}


public class MakeAutomata {


	
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
	public static SortedSet<String> TotalVariableSet(SortedSet<String> A1Variables, SortedSet<String> A2Variables) {
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
	 * calculates for Automaton At the partition of States from X that on transitions labelled with
	 * label go into Y or not
	 * @param At
	 * @param X
	 * @param Y
	 * @param label
	 * @return
	 */
	public static Pair<Set<String>, Set<String>> splits(Automaton At, Set<String> X, Set<String> Y, int label) {
		if(X.size()<=1 || Y.size()==0) {
			Set<String> X0 = new HashSet<String>();
			return new Pair<Set<String>, Set<String>>(X0,X);
		}
		
		Set<String> X0 = new HashSet<String>();
		Set<String> X1 = new HashSet<String>();
		int dim = At.variables.size();
		boolean[] e = new boolean[dim];
		for(int i=0; i<dim; i++) {
			e[i] = label%2==1;
			label /=2;
		}
		for(String s : X) {
			boolean found = false;
			for(Map.Entry<String, Transitions> entry: At.trans.get(s).entrySet()) {
				for(boolean[] e2 : entry.getValue().StateTransitionVector) {
					boolean same = true;
					for(int i=0; i<dim; i++) {
						if(e[i] != e2[i]) {
							same = false;
							break;
						}
					}
					if(same) {
						if(Y.contains(entry.getKey())) {
							// iff the destination is in X, add to X0
							X0.add(s);
						} else {
							X1.add(s);							
						}
						found = true;
						break; // this has to be a dfa, so we can stop now
					}
				}
				if(found) break;
			}			
		}
		return new Pair<Set<String>, Set<String>>(X0,X1);
	}
	
	public static Map<String, Integer>  lanpar(Automaton At) {
		
		int maxlabel = 1 << At.variables.size();
		
		Set<Set<String>> P = new HashSet<Set<String>>();
		Set<String> F = new HashSet<String>();
		Set<String> nF = new HashSet<String>();
		for(String s : At.trans.keySet()) {
			if(At.finalState.contains(s))
				F.add(s);
			else
				nF.add(s);
		}
		P.add(F);
		P.add(nF);
		
		/*
		 * here I decide to use only the State Set inside of the Workinglist
		 * and not the pair of StateSet and label (which would be more efficient)
		 */
		Queue<Set<String>> W = new LinkedList<Set<String>>();
		if(F.size()<nF.size())
			W.add(F);
		else
			W.add(nF);
		while(!W.isEmpty()) {
			Set<String> A = W.poll();
			for(int i=0; i<maxlabel; i++) {
				// go through all Partitions, and decide whether A splits it
				Queue<Set<String>> W2 = new LinkedList<Set<String>>();
				W2.addAll(P);
				while(!W2.isEmpty()) {
					Set<String> B = W2.poll();
					Pair<Set<String>, Set<String>> p = splits(At, B, A, i);
					if(p.f.size() > 0 && p.s.size() > 0) {
						// remove B from P, and add p.f p.s
						P.remove(B);
						P.add(p.f);
						P.add(p.s);
						
						// also from the worklist
						W.remove(B);
						// naiv: add both, but only one is necesary (put the smallest TODO)
						W.add(p.f);
						W.add(p.s);
					}
				}
			}
		}
		

		Map<String, Integer> renameing = new HashMap<String, Integer>();
		int i=0;
		for(Set<String> partition : P) {
			// every State in this partition will be mapped to i
			for(String s : partition) {
				renameing.put(s,i);
			}
			i++;
		}
		
		return renameing;
	}
	
	
	/**
	 * minimizes the Automaton A1
	 * @param A1
	 * @return
	 */
	public static Automaton minimize(Automaton A1) {
		
		Automaton A = new Automaton();
		A.variables = new TreeSet<String>();
		A.variables.addAll(A1.variables);
		

		Map<String, Integer> renaming = lanpar(A1);
		
		//transitions
		for(Map.Entry<String, HashMap<String, Transitions>> entry: A1.trans.entrySet()) {
			HashMap<String, Transitions> out;
			String mpdFrom = Integer.toString(renaming.get(entry.getKey()));
			if(A.trans.containsKey(mpdFrom)) {
				out = A.trans.get(mpdFrom);
			} else {
				out = new HashMap<String, Transitions>();
				A.trans.put(mpdFrom, out);
			}
			for(Map.Entry<String, Transitions> t : entry.getValue().entrySet()) {
				Transitions tr;
				String mpdTo = Integer.toString(renaming.get(t.getKey()));
				if(out.containsKey(mpdTo)) {
					tr = out.get(mpdTo);
				} else {
					tr = new Transitions();
					out.put(mpdTo, tr);
				}
				tr.StateTransitionVector.addAll(t.getValue().StateTransitionVector);				
			}
		}
		
		//final states
		A.finalState = new ArrayList<String>();
		for(String s : A1.finalState) {
			String mpd = Integer.toString(renaming.get(s));
			if(!A.finalState.contains(mpd))
					A.finalState.add(mpd);
		}
		
		//startstate
		A.startState = Integer.toString(renaming.get(A1.startState));
		System.out.println("before num of states" + A1.trans.keySet().size());
		System.out.println("after num of states" + A.trans.keySet().size());
		
		return A;
	}
	

	
	/**
	 * determines the Union of A1 automaton with A2
	 * to that end first calculates the extensions and than
	 * uses the private method AutomatonUnion
	 * @param A1, A2
	 * @return
	 */
	public static Automaton Union(Automaton A1, Automaton A2) {
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
	public static Automaton AutomatonUnion(Automaton A1, Automaton A2) {

		Automaton A1UnionA2 = ProductAutomaton(A1, A2, true); // boolUnion is set to true for Union operation
		
		return A1UnionA2;
	}
	
	/**
	 * determines the Product Automaton of A1 and A2,
	 * assuming that they operate on the same variable set
	 * @param A1, A2, boolUnion -> to determine the set of final States and is set to True for Union and False for Intersection.
	 * @return
	 */
	public static Automaton ProductAutomaton(Automaton oldA1, Automaton oldA2, boolean boolUnion) {
		
		Automaton A =  new Automaton();
		A.variables = TotalVariableSet(oldA1.variables, oldA2.variables);
		
		Automaton A1 = MakeAutomata.extendTo(oldA1, A.variables);
		Automaton A2 = MakeAutomata.extendTo(oldA2, A.variables);
		
		// Set the Start state of the product automation
		A.startState = A1.startState.toString() + "_" + A2.startState.toString();
		String oldAState = null;
		String newAState = null;
		
		for (Entry<String, HashMap<String, Transitions>> entryA1 : A1.trans.entrySet()) {
		    String keyFromStateA1 = entryA1.getKey();
		    HashMap<String, Transitions> valueA1 = entryA1.getValue();
		    
		    for (Entry<String, HashMap<String, Transitions>> entryA2 : A2.trans.entrySet()) {
			    String keyFromStateA2 = entryA2.getKey();
			    /*if(!keyFromStateA2.equals(keyFromStateA1))
			    	continue;*/
			    HashMap<String, Transitions> valueA2 = entryA2.getValue();
			    
			    for (Entry<String, Transitions> entryTransitionsA1 : valueA1.entrySet()) {
				    String keyToStateA1 = entryTransitionsA1.getKey();
				    Transitions valueTransitionsA1 = entryTransitionsA1.getValue();
				    
				    for (Entry<String, Transitions> entryTransitionsA2 : valueA2.entrySet()) {
					    String keyToStateA2 = entryTransitionsA2.getKey();
					    Transitions valueTransitionsA2 = entryTransitionsA2.getValue();
				
					    oldAState = keyFromStateA1 + "_" + keyFromStateA2;
						newAState = keyToStateA1 + "_" + keyToStateA2;
						
					    {
					    
					    	for( boolean[] t1 : valueTransitionsA1.StateTransitionVector)
						    {
						    	for( boolean[] t2 : valueTransitionsA2.StateTransitionVector)
							    {
						    		if(Arrays.equals(t1, t2))
								    {
						    			HashMap<String, Transitions> newTransition;
						    			Transitions myTrans;
						    			if(A.trans.containsKey(oldAState))
						    			{
						    				newTransition = A.trans.get(oldAState);
						    				if(newTransition.containsKey(newAState))
						    				{
						    					myTrans = newTransition.get(newAState);
						    					
						    				}
						    				else
						    				{
						    					myTrans = new Transitions();
						    					newTransition.put(newAState, myTrans);
						    				}
						    				
						    			} else
						    			{
						    				newTransition = new HashMap<String, Transitions>();
						    				myTrans = new Transitions();
					    					newTransition.put(newAState, myTrans);
					    					A.trans.put(oldAState, newTransition);
						    			}
						    			myTrans.addTransition(t1);
										
								    }// else continue to check for next transition for same state in A2
							    }
						    }
					    	//A.trans.put(oldAState, newATransition); // Adding to Hashmap after getting all transitions.
					    }
			    
			    }// Check for next transition for same state in A1 // End of innermost for loop for Transitions of A2
			    
			}// Check for next transition for same state in A1 // End of second last for loop for Transitions of A1
		    
		}// Check transitions for next state in A2 // End of second for loop of A2

	}// Check transitions for next state in A1 // End of outermost and first for loop of A1

		
		//Add the final states of our newly constructed Automaon A based on the final states in each of the individual input Automatons and the flag passed for either Union or Intersection.
		for(Entry<String, HashMap<String, Transitions>> entryA : A.trans.entrySet())
		{
			String keyStateA = entryA.getKey();
			
			for(Entry<String, HashMap<String, Transitions>> entryA1 : A1.trans.entrySet())
			{
				String keyStateA1 = entryA1.getKey();
				for(Entry<String, HashMap<String, Transitions>> entryA2 : A2.trans.entrySet())
				{
					String keyStateA2 = entryA2.getKey();
				    if(keyStateA.equals(keyStateA1 + "_" + keyStateA2))
				    {
				    	if(boolUnion) //For Union of 2 automata, Product automaton has a final state if either of the 2 automata has a final state.
						{
				    		//System.out.print(keyStateA1 + "_" + keyStateA2);
							if(A1.finalState.contains(keyStateA1) || A2.finalState.contains(keyStateA2))
							{
								//System.out.println(" same");
								A.finalState.add(keyStateA);
								break;
							}
							//System.out.println("");
						}
						else //For Intersection of 2 automata, Product automaton has a final state if and only if both the automata have final states.
						{
							if(A1.finalState.contains(keyStateA1) && A2.finalState.contains(keyStateA2))
							{
								A.finalState.add(keyStateA);
								break;
							}
						}
				    }
				}
				
				if(A.finalState.contains(keyStateA))
					break;
			}
		}
		
		// minimize the automaton here 		
		return minimize(A);
		// Remap the given DFA with new state names.
					//return DFAWithRemappedStates(A);
	}
	
	
	/**
	 * determines the Intersection of A1 automaton with A2
	 * to that end first calculates the extensions and than
	 * uses the private method AutomatonIntersection
	 * @param A1, A2
	 * @return
	 */
	public static Automaton Intersect(Automaton A1, Automaton A2) {
	
		Automaton A1IntersectA2 = AutomatonIntersection(A1, A2);
		return A1IntersectA2;
	}
	
	
	public static Automaton AutomatonIntersection(Automaton A1, Automaton A2) {

		Automaton A1IntersectA2 = ProductAutomaton(A1, A2, false); // boolUnion is set to false for Intersection operation
		
		return A1IntersectA2;
	}
	
	/**
	 * projects away the variable in variableName
	 * TODO: Take Care !! this method alters the automaton A1 !!
	 * @param A1, variableName
	 */
	public static Automaton Project(Automaton A1, String variableName) {
		
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
		A1.variables.remove(variableName);
		for(Map.Entry<String, HashMap<String,Transitions>> transition : A1.trans.entrySet()) {
			for(Map.Entry<String, Transitions> entry : transition.getValue().entrySet()) {
				entry.getValue().project(index); 
			}
		}	
		
		// Do the Padding
		boolean change = true;
		while(change) {
			change = false;
			// go through all transitions:
			for(Map.Entry<String, HashMap<String, Transitions>> me : A1.trans.entrySet()) {
				if(!A1.finalState.contains(me.getKey())) {
					// not in finalState, we might possibly add it
					for(Map.Entry<String, Transitions> me2 : me.getValue().entrySet()) {
						if(A1.finalState.contains(me2.getKey())) {
							// where it heads to is final, it might be final
							// if there goes a onlyZero label
							for(boolean[] e : me2.getValue().StateTransitionVector) {
								boolean allZeros=true;
								for(int i=0; i<A1.variables.size(); i++) {
									if(e[i]) {
										allZeros=false;
										break;
									}
								}
								if(allZeros) {
									// have to pad = insert into final states
									if(!A1.finalState.contains(me.getKey())) {
										A1.finalState.add(me.getKey());
										change = true;
									}
								}
							}
						}
					}
				}				
			}			
		}
		
		
		
		return A1;
	}
		
	
	
	/**
	 * complements the Automaton (Assuming that the input automation is a DFA)
	 */
	public static Automaton Complement(Automaton A) {
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
	public static String ToString( Automaton a) {
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
		@SuppressWarnings("unchecked")
		ArrayList<String> f = (ArrayList<String>)a.finalState.clone();
		Collections.sort(f);
		for(String s : f) { // TODO: make sure its sorted
			list_of_final_states += "\n" + s + "[peripheries=2];";
		}
		
		// initial state
		String initial_state = "\n" + a.startState + "[shape=<diamond>];\n";
		
		// free variables
		String list_of_free_variables = "";
		if(a.variables.size()==0) {
			if(a.finalState.contains(a.startState))
				list_of_free_variables = "true";
			else
				list_of_free_variables = "false";
		} else {
			for(String v : a.variables) { 
				list_of_free_variables += v;
			}
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
		 * This method determinizes the given Automaton
		 * 
		 * it does not alter the input
		 * @param aNFA
		 * @return
		 * @author max
		 */
		public static Automaton determinize(Automaton aNFA) 
		{
			Automaton DFA = new Automaton();
			DFA.variables = new TreeSet<String>();
			DFA.variables.addAll(aNFA.variables);
			int dim = aNFA.variables.size();
		
			Queue<Set<String>> W = new LinkedList<Set<String>>();
			Set<String> s = new HashSet<String>();
			s.add(aNFA.startState);
			W.add(s);
			
			Map<Set<String>, String> rename = new HashMap<Set<String>, String>();
			int newstatename = 0;
			DFA.startState = Integer.toString(newstatename++);
			rename.put(s, DFA.startState);
						
			while(!W.isEmpty()) {
				Set<String> curS = W.poll();
				String DFAname = rename.get(curS);
				
				if(DFA.trans.containsKey(DFAname)) {
					// allready worked on that StateSet
					continue;
				}
				HashMap<String, Transitions> tr = new HashMap<String, Transitions>();
				DFA.trans.put(DFAname, tr);
				
				// care about the transitions
				for(int i=0; i<(1 << dim); i++) {
					int x = i;
					boolean[] e = new boolean[dim];
					for(int j=0; j<dim; j++) {
						e[j] = x%2==1;
						x/=2;
					}
					Set<String> toS = new HashSet<String>();
					for(String subS : curS) {
						// now add the state that is reached from subS via an edge labelled e
						for(Map.Entry<String, Transitions> entry : aNFA.trans.get(subS).entrySet()) {
							String to = entry.getKey();
							for(boolean[] e2 : entry.getValue().StateTransitionVector) {								
								boolean same = true;
								for(int j=0; j<dim; j++) {
									if(e[j]!=e2[j]) {
										same = false; break;
									}
								}
								if(same) {
									toS.add(to);
									break; // if it is already in, more cant be done!
								}
							}
						}
					}
					// now we know for label e where we go from state curS
					// add the toS to the StateMap rename 
					String NFAStatename;
					if(rename.containsKey(toS)) {
						NFAStatename = rename.get(toS);
					} else {
						NFAStatename = Integer.toString(newstatename++);
						rename.put(toS, NFAStatename);
					}
					// and the Transition to DFA
					Transitions t;
					if(tr.containsKey(NFAStatename)) {
						t = tr.get(NFAStatename);
					} else {
						t = new Transitions();
						tr.put(NFAStatename, t);
					}
					t.addTransition(e);
					
					// also add toS to the worklist
					W.add(toS);
				}
				
				// care about whether this state is final
				boolean fin = false;
				for(String subS : curS) {
					if(aNFA.finalState.contains(subS)) {
						fin = true;
						break;
					}
				}
				if(fin)
					DFA.finalState.add(DFAname);
			}
			
			
			return DFA;
		}
		
		/**
		 * Converts the given NFA to a DFA.
		 * @author vishal
		 */
		public static Automaton ConvertNFAToDFA(Automaton aNFA) 
		{
			Automaton aDFA = new Automaton();
			aDFA.startState = aNFA.startState;
			aDFA.variables = aNFA.variables;
			int CountOfVariables = aDFA.variables.size();
			
			int countOfNFAStates = aNFA.trans.size();
			double maxCountOfDFAStates = Math.pow(2, countOfNFAStates);
			
			//The PowerSet List can be avoided but used to check if state is already added or processed. It also helps to note down the states in the power set added till now for debugging purposes.
			ArrayList<String> powerSetStateList = new ArrayList<String>();
			//Keeps a track of all unprocessed states and iterates until it is empty.
			Queue<String> queueDFANewUnprocessedStateSet = new LinkedList<String>();
			String oldNFAState  = aNFA.startState;
			
			//We use this to put an individual transition in an ArrayList and another ArrayList to store the multiple states separated by "_". Both these ArrayLists are mapped by the same index position.
			ArrayList<String> aDFATransSet = new ArrayList<String>();
			ArrayList<String> aDFAStateSet = new ArrayList<String>();
	        
			
			Transitions newTransitionVector = new Transitions();
			 //We use this to put together all the computed transitions for each state in aDFATSet.
			Map<Transitions, String> aDFATransitionSet = new HashMap<Transitions, String>();
			
			//We iterate only once using the below for-loop to get the first state of the NFA. It has constant run-time complexity.
			//Moreover, it makes sure that only reachable states from the Start state are processed.
			
			//Add the missing transitions to a dead state as it is a DFA.
		    String deadState = "0";
		    //Generate all transitions. 
		    Transitions tAllTransitions = generateAllCombinations(aDFA.variables.size());
		    //If we have a dead state in the DFA, we need to add all the transitions emerging from it to itself.
		    Transitions copyOftAllTransitions = generateAllCombinations(aDFA.variables.size());
		    
			//Iterate through all the computed transitions in aDFATSet and add it based on the state.
			//Note that Transition set has String i.e. the state name as its key.
			
			powerSetStateList.add(oldNFAState);
			queueDFANewUnprocessedStateSet.add(oldNFAState);
			
			//Add this newly created Transition Set into the NFA Transition map created above.
			
			//Add all the transitions to the New states from the Old state into the DFA now.
			
			//Process all unprocessed new states generated from the first state of the NFA above.
			//We add only new states and not states which were processed earlier and the while loop is executed until there are no new states.
			while (!queueDFANewUnprocessedStateSet.isEmpty()) 
			{ 
			    String nextUnprocessedState = queueDFANewUnprocessedStateSet.remove();
			    String[] unprocessedIndividualStates = new String[(int) maxCountOfDFAStates];
			    if(nextUnprocessedState.contains("_"))
			    {
			    	unprocessedIndividualStates = nextUnprocessedState.split("_");
			    }
			    else
			    {
			    	unprocessedIndividualStates[0] = nextUnprocessedState;
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
						    	String strArrayTransitions = "";
								int iTrans=1;
								for(boolean bool : t)
								{
									if(bool)
										strArrayTransitions += "1";
									else
										strArrayTransitions += "0";
									iTrans++;
									if(iTrans<CountOfVariables)
										strArrayTransitions += "-";
								}
								
						    	if(aDFATransSet.contains(strArrayTransitions)) 
						    	{	
						    		int indexOfTransition = aDFATransSet.indexOf(strArrayTransitions);
						    		String oldDFAState =  aDFAStateSet.get(indexOfTransition);
						    		String newDFAState =  aDFAStateSet.get(indexOfTransition);
						    		
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
							    		
							    		aDFAStateSet.remove(oldDFAState);
							    		aDFAStateSet.add(newDFAState);
						    	}
						    	}
							    else // for the first transition and when there are intermediate single transitions
							    {
							    	aDFATransSet.add(strArrayTransitions);
							    	aDFAStateSet.add(keyToStateaNFA);
							    }	
						    }		
						    	}
					    }
						break;
					}
						    
			  //Add the missing transitions to a dead state as it is a DFA.
			  //Generate all transitions.
			  //Note: Can't reuse copyOftAllTransitions which contains the list of all transitions due to properties of the Transition class.
			    tAllTransitions = generateAllCombinations(aDFA.variables.size());
			    
					for(String nxtState : aDFAStateSet)
				    {
						if(aDFATransitionSet.containsValue(nxtState))
							 continue;
						for( String strTransition : aDFATransSet)
					    {
							boolean[] transition = new boolean[CountOfVariables];
							String[] arr = new String[CountOfVariables];
							
							if(strTransition.contains("-"))
								arr = strTransition.split("-");
							else
								arr = strTransition.split("");
							
							int i=0;
							for(String a : arr)
							{
								transition[i] = Boolean.getBoolean(a);
								i++;
							}
							int indexOfTransition = aDFATransSet.indexOf(strTransition);
							String returnedState = aDFAStateSet.get(indexOfTransition);
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
					
					aDFATransSet.clear();
					aDFAStateSet.clear();
					
					HashMap<String, Transitions> newDFATransitions = new HashMap<String, Transitions>();
				    
					if(aDFA.trans.containsKey(oldNFAState)) {
						newDFATransitions = aDFA.trans.get(oldNFAState);
						} else {
						newDFATransitions = new HashMap<String, Transitions>();
						aDFA.trans.put(oldNFAState, newDFATransitions);
						}
					
					
					for (Entry<Transitions, String> entry : aDFATransitionSet.entrySet()) 
				    {
				        Transitions multipleTransitions = entry.getKey();
				        String newDFAState = entry.getValue();
				        //newDFATransitions.put(newDFAState, multipleTransitions);
						
				        if(newDFATransitions.containsKey(newDFAState)) {
				        	Transitions t = newDFATransitions.get(newDFAState);
				        	t.StateTransitionVector.addAll(multipleTransitions.StateTransitionVector);
				        	} else {
				        	newDFATransitions.put(newDFAState, multipleTransitions);
				        	}
				        
				        
						for(String finalState : aNFA.finalState)
						{
							if(newDFAState.contains("_" + finalState.toString().trim()) || newDFAState.contains(finalState.toString().trim() + "_") ||
									newDFAState.equals(finalState.toString().trim()))
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
					//aDFA.trans.put(oldNFAState, newDFATransitions);
					aDFATransitionSet.clear();
			}
			
			if(aNFA.finalState.contains(aDFA.startState.trim()))
				{
				   if(!aDFA.finalState.contains(aDFA.startState))
				      aDFA.finalState.add(aDFA.startState);
				}
			
			// Remap the given DFA with new state names.
			return DFAWithRemappedStates(aDFA);
		}
	
		/**
		 * Remap the given DFA with new state names.
		 */
		public static Automaton DFAWithRemappedStates(Automaton A) {
			Automaton ADFAWithRemappedStates = new Automaton();
			//ADFAWithRemappedStates.startState = A.startState;
			ADFAWithRemappedStates.variables = A.variables;
			
			Map<String, Integer> mappings = new HashMap<String, Integer>();
			int i = 1;
			for (Entry<String, HashMap<String, Transitions>> entryA : A.trans.entrySet()) 
			{
			    mappings.put(entryA.getKey(), i);
			    i++;
			}
			
			//Set the Start State.
			ADFAWithRemappedStates.startState = Integer.toString(mappings.get(A.startState));
			
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
