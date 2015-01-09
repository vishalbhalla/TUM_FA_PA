package automatons;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

class Transitions {
	
	static Comparator<boolean[]> ourComp = new Comparator<boolean[]>(){
        public int compare(boolean[] t1, boolean[] t2){
        	for (int j = 0; j<t1.length; j++)
        	{
        		   if (t1[j] == false && t2[j]== true) 
        		      return -1;
        		   else if (t1[j] == true && t2[j]== false) 
        			   return 1;
        	}
        	return 0;
        	}};
	
	//HashSet<boolean[]> StateTransitionVector = new HashSet< boolean[]>();
	TreeSet<boolean[]> StateTransitionVector = new TreeSet<boolean[]>(ourComp);
	
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

	public void project(int index) {
		// delete from every boolean array the component at column @index
		TreeSet<boolean[]> tmp = new TreeSet< boolean[]>(ourComp);
		for(boolean[] e : StateTransitionVector) {
			boolean[] e2 = new boolean[e.length-1];
			for(int i=0,j=0; i<e.length; i++) {
				if(i!=index) {
					e2[j++] = e[i];
				}
			}
			tmp.add(e2);
		}
		StateTransitionVector = tmp;
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
}
