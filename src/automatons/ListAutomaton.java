package automatons;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;




class Transition {

	public Transition(String fr, String to, boolean[] w) {
		var_c = w.length;
		word = w;
		from = fr;
		this.to = to;		
	}

	public String toString() {
		String ws = "";
		for(int i=0; i<var_c; i++) {
			ws += word[i]?"1":"0";
		}
		return from + " -> " + to + "[label=\"" + ws +"\"];";
	}
	
	int var_c;
	
	String from, to;
	
	boolean word[];
	
}

public class ListAutomaton {
	/* A = ( Sigma, States, Transitions, startstate(s), final states)
	 * Sigma = {0,1}^var_c
	 * States
	 * Transitions: an adjacency list (how to implement? BDD, list?)
	 */
	
	int var_c; // number of variables
		
	SortedSet<String> variables;
	
	SortedMap<String,ArrayList<Transition>> adjlists;
		
	String startstate;
	Set<String> finalSet;
	
	// Some constructors
	
	
	
	public ListAutomaton() {
		var_c = 0;
		
		//Todo: how to implement SortedSet, sorted list I suppose, need merge operation
		//variables = new SortedSet<String>(); 
		finalSet = new HashSet<String>();
		adjlists = new TreeMap<String,ArrayList<Transition>>();
		variables = new TreeSet<String>();
	}

	
	
	public ListAutomaton(String[] vars) {
		var_c = vars.length;
		
		finalSet = new HashSet<String>();
		adjlists = new TreeMap<String,ArrayList<Transition>>();
		variables = new TreeSet<String>();
		
		for(String s : vars) {
			variables.add(s);
		}
		
		startstate = "0";
		finalSet.add("End");

		ArrayList<Transition> zerotrans = new ArrayList<Transition>();
		allcomb(zerotrans, "0", "1", (1 << vars.length)-1, vars.length);
		adjlists.put("0", zerotrans);
		adjlists.put("1", new ArrayList<Transition>());	
	}
	
	
	
	private void allcomb(ArrayList<Transition> t,
			String f, String to, int i, int length) {
		//System.out.println("allcomb("+i+")");
		if(i<0) return;
		
		boolean[] w = new boolean[length];
		int ha = i;
		for(int j=0; j<length; j++) {
			w[j] = ha%2==0;
			ha = ha >> 1;
		}
		t.add(new Transition(f,to,w));
		allcomb(t, f, to, i-1, length);
	}

	public static ListAutomaton emptyFA() {
		return new ListAutomaton();
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
	 * determines the union of this automaton with A2
	 * to that end first calculates the extensions and than
	 * uses the private method union2
	 * @param A2
	 * @return
	 */
	public void union(ListAutomaton A2) {
		//TODO
		//SortedSet<String> varset = merge(this.variables, A2.variables);
		//A2 = A2.extendTo(varset);
		//extendTo(varset);
		union2(A2);
	}
	
	/**
	 * determines the union of this automatons and A2,
	 * assuming that they operate on the same variable set
	 * @param A2
	 * @return
	 */
	public void union2(ListAutomaton A2) {
		//TODO
		//...
	}
	
	
	
	
	// INTERSECTION
	
	public void intersect(ListAutomaton A2) {
		
	}
	
	
	public void intersect2(ListAutomaton A2) {
		
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
	public void complement() {
		
	}

	
	
	/**
	 * exports the automaton in the dotty-format (visualize e.g. with GraphViz)
	 * @return
	 */
	public String toString() {
		// edges TODO: group the labels for the edges!!!
		String list_of_edges = "";
		for(Map.Entry<String, ArrayList<Transition>> entry : adjlists.entrySet()) {
			for(Transition t : entry.getValue()) {
				list_of_edges += "\n" + t.toString(); 
			}
		}
		
		// final states
		String list_of_final_states = "";
		for(String s : finalSet) { // TODO: make sure its sorted
			list_of_final_states += "\n" + s + "[peripheries=2];";
		}
		
		// initial state
		String initial_state = "\n" + startstate + "[shape=<diamond>];\n";
		
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
	public boolean member(ArrayList<boolean[]> word) {
		
		return accepts(startstate, word);
	}
	
	public boolean accepts(String state, ArrayList<boolean[]> word) {
		
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
				add_all_labelled_edges(currentstate, label, queue2);
			}
			queue = queue2;
		}
		if(word.size()<i || queue.isEmpty()) return false;
		while(!queue.isEmpty()) {
			String currentstate = queue.poll();
			if(isfinal(currentstate)) return true;
		}
		return false;
	}

	private boolean isfinal(String currentstate) {
		return finalSet.contains(currentstate);
	}


	/**
	 * goes through all edges and adds the ones labeled with "label"
	 * to the queue
	 * TODO: quite inefficient, but it should work!
	 * @param currentstate
	 * @param label
	 * @param q
	 */
	private void add_all_labelled_edges(String currentstate, boolean[] label,
			LinkedList<String> q) {
		
		ArrayList<Transition> trans = adjlists.get(currentstate);
		
		for(Transition edge : trans) {
			if(label.length==edge.word.length) {
				int i=0;
				for(; i<label.length; ++i) {
					if(label[i]!=edge.word[i]) break;
				}
				if(i==label.length)
					q.add(edge.to);
			}
		}
		
	}



	public Automaton Negation() {
		// TODO Auto-generated method stub
		return null;
	}



	public Automaton intersect(Automaton aRightAnd) {
		// TODO Auto-generated method stub
		return null;
	}



	public Automaton union(Automaton aRightOr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
