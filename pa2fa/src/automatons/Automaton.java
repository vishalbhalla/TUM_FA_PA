package automatons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;

class Transitions {
	
	HashSet<boolean[]> StateTransitionVector = new HashSet< boolean[]>();
	
	public String toString() {
		String ret = "";
		boolean first=true;
		for(boolean[] e : StateTransitionVector) { //TODO: sort it!
			if(!first) ret += " ";
			first = false;
			for(int i=0; i<e.length; i++) {
				ret += e[i]?"1":"0";
			}
		}
		return ret;
	}
	
}

public class Automaton {
	/* A = ( Sigma, States, Transitions, start state(s), final states)
	 * Sigma = {0,1}^var_c
	 * States
	 * Transitions: an adjacency list (how to implement? BDD, list?)
	 */
	
	String startState = null;
	ArrayList<String> finalState = new ArrayList<String>();

	
	int var_c; // number of variables
	
	SortedSet<String> variables; // variable names
	
	// Sorted map of ArrayLists?
	HashMap<String,HashMap<String, Transitions>> trans = new HashMap<String,HashMap<String,Transitions>>();
		
	// Some constructors
	
	public Automaton() {
		var_c = 0;
		//Todo: how to implement SortedSet, sorted list I suppose, need merge operation
		//variables = new SortedSet<String>(); 
		
	}

	public static Automaton UniversalAutomation(SortedSet<String> varSet)
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
			t.StateTransitionVector.add(e);			
		}
		
		
		return t;
	}

	public static Automaton emptyFA()
	{
		return new Automaton();
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
	 * @param A1
	 * @return
	 */
	public Automaton Negation()
	{
		return new Automaton();
	}
	
	
	/**
	 * determines the union of this automaton with A2
	 * to that end first calculates the extensions and than
	 * uses the private method union2
	 * @param A2
	 * @return
	 */
	public Automaton union(Automaton A2) {
		//TODO
		//SortedSet<String> varset = merge(this.variables, A2.variables);
		//A2 = A2.extendTo(varset);
		//extendTo(varset);
		union2(A2);
		return A2;
	}
	
	/**
	 * determines the union of this automatons and A2,
	 * assuming that they operate on the same variable set
	 * @param A2
	 * @return
	 */
	public Automaton union2(Automaton A2) {
		//TODO
		//...
		return A2;
	}
	
	
	
	
	// INTERSECTION
	
	public Automaton intersect(Automaton A2) {
		/*
		Automaton IntersectionAut = new Automaton();
		
		foreach (State s  in  A2.states)
		{
			if(transitions[s.StateNo,0,0])
		}
		if()
		{
			IntersectionAut.startState = startState;
			IntersectionAut.states.add(startState);
			IntersectionAut.states.add(finalState);
			
		}
		else
		{
			
		}
		return A2;*/return null;
	}
	
	
	public Automaton intersect2(Automaton A2) {
		return A2;
	}
	
	/**
	 * projects away the variable in vars
	 * @param vars
	 */
	public void project(String s) {
		//TODO
		//is s in variables? if not, do nothing
	}
	
	/**
	 * complements the Automaton
	 */
	public Automaton complement(Automaton A) {
		return A;
	}

	
	
	/**
	 * exports the automaton in the dotty-format (visualize e.g. with GraphViz)
	 * @return
	 */
	public String toString() {
		// edges TODO: group the labels for the edges!!!
		String list_of_edges = "";
		for(Map.Entry<String, HashMap<String,Transitions>> transition : trans.entrySet()) {
			String from = transition.getKey();
			for(Map.Entry<String, Transitions> entry : transition.getValue().entrySet()) {
				String to = entry.getKey();
				list_of_edges += "\n" +  from + " -> " + to + " [label=\"" +entry.getValue().toString() + "\"];"; 
			}
		}	
					
		// final states
		String list_of_final_states = "";
		for(String s : this.finalState) { // TODO: make sure its sorted
			list_of_final_states += "\n" + s + "[peripheries=2];";
		}
		
		// initial state
		String initial_state = "\n" + startState + "[shape=<diamond>];\n";
		
		// free variables
		String list_of_free_variables = "";
		for(String v : variables) { // TODO: check whether they come sorted
			list_of_free_variables += v;
		}
		
		if(variables.size()==0) list_of_free_variables = "true";
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
