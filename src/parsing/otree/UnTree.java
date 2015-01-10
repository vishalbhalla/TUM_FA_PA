package parsing.otree;

public class UnTree extends OTree {
	public OTree son;
	
	public String toString() {
		return "(" + OTree.comp2string(this.type) + son.toString() + ")";
	}
}
