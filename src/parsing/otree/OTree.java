package parsing.otree;

import parsing.PALexer;




/**
 * Implementation of an abstract syntax tree.
 * 
 * @author max
 *
 */
public class OTree {
	public int type;

	/**
	 * gives a String for every Token
	 * @param x
	 * @return
	 */
	public static String comp2string (int x) {
		
		switch(x) {
		case PALexer.ALL: 
			return "A ";
		case PALexer.EX: 
			return "E ";

		case PALexer.EQ: 
			return "==";
		case PALexer.GEQ: 
			return ">=";
		case PALexer.LEQ: 
			return "<=";
		case PALexer.GT: 
			return ">";
		case PALexer.LT: 
			return "<";
		case PALexer.NEQ: 
			return "!=";
			
		case PALexer.NEG: 
			return "~";
			
		case PALexer.AND: 
			return "/\\";
		case PALexer.OR: 
			return "\\/";
		case PALexer.IMP: 
			return "->";
		case PALexer.EQV: 
			return "<->";
		
		default:
			return "fuckyou";
		}
	}
	public String toString() {
		return "";
	}
}
