package parsing.otree;

public class BinTree extends OTree {
	public OTree left, right;
	
	public String toString() {
		return "(" + left.toString() + " " + OTree.comp2string(this.type)
					+ " " + right.toString() + ")";
	}
}
