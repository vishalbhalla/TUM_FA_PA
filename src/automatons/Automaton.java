package automatons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;



public class Automaton {
	/* A = ( Sigma, States, Transitions, start state(s), final states)
	 * Sigma = {0,1}^var_c
	 * States
	 * Transitions: an adjacency list (how to implement? BDD, list?)
	 */
	
	public String startState = null;
	public ArrayList<String> finalState = new ArrayList<String>();

	
	public int var_c; // number of variables
	
	public SortedSet<String> variables; // variable names
	
	// Sorted map of ArrayLists?
	public HashMap<String,HashMap<String, Transitions>> trans = new HashMap<String,HashMap<String,Transitions>>();
		
	// Some constructors
	
	public Automaton() {
		var_c = 0;
		//Todo: how to implement SortedSet, sorted list I suppose, need merge operation
		//variables = new SortedSet<String>(); 
		
	}
}
