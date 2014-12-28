package automatons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

import java.util.Map.Entry;



public class MakeAutomata {

	//Automaton automaton;
	public MakeAutomata() {	
		
	}
	
	public static Automaton UniversalAutomation(SortedSet<String> varSet)
	{
		Automaton A = new Automaton();
		A.startState = "1";
		A.finalState.add(A.startState);
		A.variables = varSet;

		Transitions t = generateAllCombinations(varSet.size());
		HashMap<String,Transitions> hm = new HashMap<String,Transitions>();
		String state2 = "2";
		hm.put(state2,t);
		A.trans.put(A.startState, hm);
		A.trans.put(state2, hm);
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
		SortedSet<String> varset = null;
		varset = A1Variables;
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
	public void extendTo(SortedSet<String> varSet){
	}
	
	
	/**
	 * write a method for negating the automation i.e. swap the final and non-final states
	 * It obviously operates on the same variable set
	 * 
	 * @param A
	 * @return
	 */
	public Automaton Negation(Automaton A)
	{
		Automaton NegA = new Automaton();
		return NegA;
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
	public Automaton ProductAutomaton(Automaton A1, Automaton A2, boolean boolUnion) {
		
		Automaton A =  new Automaton();
		A.variables = TotalVariableSet(A1.variables, A2.variables);
		
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
		//TODO
		//SortedSet<String> varset = merge(this.variables, A2.variables);
		//A2 = A2.extendTo(varset);
		//extendTo(varset);
			
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
	 * @param A1, variableName
	 */
	public Automaton Project(Automaton A1, String variableName) {
		//TODO
		//is s in variables? if not, do nothing
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
				break;
			}
		}		
		return AComplement;
	}

	
	
	/**
	 * exports the automaton in the dotty-format (visualize e.g. with GraphViz)
	 * @return
	 */
	public String ToString( Automaton a) {
		// edges TODO: group the labels for the edges!!!
		String list_of_edges = "";
		for(Map.Entry<String, HashMap<String,Transitions>> transition : a.trans.entrySet()) {
			String from = transition.getKey();
			for(Map.Entry<String, Transitions> entry : transition.getValue().entrySet()) {
				String to = entry.getKey();
				list_of_edges += "\n" +  from + " -> " + to + " [label=\"" +entry.getValue().toString() + "\"];"; 
			}
		}	
					
		// final states
		String list_of_final_states = "";
		for(String s : a.finalState) { // TODO: make sure its sorted
			list_of_final_states += "\n" + s + "[peripheries=2];";
		}
		
		// initial state
		String initial_state = "\n" + a.startState + "[shape=<diamond>];\n";
		
		// free variables
		String list_of_free_variables = "";
		for(String v : a.variables) { // TODO: check whether they come sorted
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
	 */
	public boolean member(String word) {
		
		return false;
	}
	
	public boolean accepts(String s, ArrayList<boolean[]> word) {
		
		// for all transitions from s labelled with word[0]
		// if one of those returns true -> return true
		
		return false;
	}
}
