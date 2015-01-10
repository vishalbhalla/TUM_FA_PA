package parsing.otree;

import java.util.ArrayList;

public class AF extends OTree {
	public int rightside;
	public ArrayList<String> lsvar;
	public ArrayList<Integer> lscoeff;
	
	public String toString() {
		String ret = "";
		for(int i=0; i< lsvar.size(); ++i) {
			ret += (i>0?" +":"") + "(" + lscoeff.get(i) + ")" + lsvar.get(i);
		}
		return ret + " " + OTree.comp2string(this.type) + " " + rightside;
	}
}

