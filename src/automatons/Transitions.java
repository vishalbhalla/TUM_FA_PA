package automatons;

import java.util.Comparator;
import java.util.TreeSet;

public class Transitions {
	
	/**
	 * the Comparator that we use, in order to be able to use an OrderedSet
	 * @author vishal
	 */
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
        }
	};
        	
        	
	/**
	 * adds a Transition to the StateTransitionVector with 
	 * Label val; val here is an integer but should represent a bitstring
	 * thus this only works until size=32 for 32bit integers!!
	 * @param val
	 * @param size
	 * @author max
	 */
    public void addTransition(int val, int size) {
    	boolean[] e = new boolean[size];
    	for(int i=0; i<size; i++) {
    		e[i] = (val%2)==1;
    		val /= 2;
    	}
    	addTransition(e);
    }
    
    /**
     * adds a Transition labeled with e to the StateTransitionVector
     * @param e
     * @author max
     */
    public void addTransition(boolean[] e) {
    	StateTransitionVector.add(e);
    }
        	
	//HashSet<boolean[]> StateTransitionVector = new HashSet< boolean[]>();
	TreeSet<boolean[]> StateTransitionVector = new TreeSet<boolean[]>(ourComp);
	
	/**
	 * method to get a String representation of the Transitions
	 * intended for Dotty Format!
	 * @author max
	 */
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

	private void extend(int i, boolean[] cur, int[] transform, boolean[] old, TreeSet<boolean[]> tmp) {
		if(i==transform.length) {
			boolean[] copy = cur.clone();
			tmp.add(copy);
			return;
		}
		
		if(transform[i]==-1) {
			cur[i] = false;
			extend(i+1, cur, transform, old, tmp);
			cur[i] = true;
			extend(i+1, cur, transform, old, tmp);
		} else {
			cur[i] = old[transform[i]];
			extend(i+1, cur, transform, old, tmp);
		}
		
	}
	
	/**
	 * extends the labels to more variables
	 * @param index
	 * @author max
	 */
	public void extendTo(int[] transform) {
		// extend every boolean array the component at column @index
		TreeSet<boolean[]> tmp = new TreeSet< boolean[]>(ourComp);
		for(boolean[] e : StateTransitionVector) {
			boolean[] e2 = new boolean[transform.length];
			extend(0, e2, transform, e, tmp);
		}
		StateTransitionVector = tmp;
	}

	
	
	/**
	 * projects away the index component
	 * @param index
	 * @author max
	 */
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