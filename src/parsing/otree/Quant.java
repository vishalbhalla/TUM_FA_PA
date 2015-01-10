package parsing.otree;

public class Quant extends OTree {
	public OTree son;
	public String var;
	
	public String toString() {
		return "(" + OTree.comp2string(this.type) + " " + var + "." + son.toString() + ")";
	}
}
