package logic;
import java.util.ArrayList;
import parsing.*;
import parsing.otree.*;
import automatons.Automaton;
import automatons.MakeAutomata;



public class Translation {

	/**
	 * translates an Atomic formula of Presburger Arithmetic
	 * into a deterministic finite automaton
	 * @param objlscoeff 
	 * @param objlsvar 
	 * @param rightside 
	 * @return
	 */
	public static Automaton AF2DFA(int rightside, ArrayList<String> objlsvar, ArrayList<Integer> objlscoeff) {
		
		return null;
	}
	
	
	public static Automaton pa2fa(OTree objtree) {
		
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		switch(objtree.type) {

			case PALexer.EX :
				Quant ex = (Quant)objtree;
				String varname = ex.var;
				OTree son  = ex.son;
				Automaton ASon = pa2fa(son);
				Automaton AEx = objMakeAutomaton.Project(ASon, varname);
				return AEx;
				
			case PALexer.ALL :
				Quant ex1 = (Quant)objtree;
				String varname1 = ex1.var;
				OTree son1  = ex1.son;
				Automaton ASon1 = pa2fa(son1);
				Automaton ANegSon1 = objMakeAutomaton.Negation(ASon1);
				Automaton AAll = objMakeAutomaton.Project(ANegSon1, varname1);
				return AAll;
				
				// all the comparators starting a atomic formula
            case PALexer.EQ :
            case PALexer.NEQ :
            case PALexer.GEQ :
            case PALexer.LEQ :
            case PALexer.GT :
            case PALexer.LT : 
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
				
				return AF2DFA(rightside, objlsvar, objlscoeff);
						
		    case PALexer.AND:
		    	BinTree objBinTreeAnd = (BinTree)objtree;
				Automaton ALeftAnd = pa2fa(objBinTreeAnd.left);
				Automaton ARightAnd = pa2fa(objBinTreeAnd.right);
				Automaton A1IntersectA2 = objMakeAutomaton.Intersect(ALeftAnd,ARightAnd);
				return A1IntersectA2;
			
			case PALexer.OR:
				BinTree objBinTreeOr = (BinTree)objtree;
				Automaton ALeftOr = pa2fa(objBinTreeOr.left);
				Automaton ARightOr = pa2fa(objBinTreeOr.right);
				Automaton A1UnionA2 = objMakeAutomaton.Union(ALeftOr, ARightOr);
				return A1UnionA2;
					
			case PALexer.NEG:		
				Automaton NegA = objMakeAutomaton.Negation(pa2fa(objtree));
				return NegA;
			
			case PALexer.IMP:
				BinTree objBinTreeImp = (BinTree)objtree;
				Automaton ALeftImp = pa2fa(objBinTreeImp.left);
				Automaton ALeftImpNeg = objMakeAutomaton.Negation(ALeftImp);
				Automaton ARightImp = pa2fa(objBinTreeImp.right);
				Automaton A1ImpA2 = objMakeAutomaton.Union(ALeftImpNeg, ARightImp);
				return A1ImpA2;
				
			case PALexer.EQV:		
				BinTree objBinTreeEqvImp = (BinTree)objtree;
				Automaton ALeftEqvImp = pa2fa(objBinTreeEqvImp.left);
				Automaton ALeftEqvImpNeg = objMakeAutomaton.Negation(ALeftEqvImp);
				Automaton ARightEqvImp = pa2fa(objBinTreeEqvImp.right);
				Automaton ABimpTerm1 = objMakeAutomaton.Union(ALeftEqvImpNeg, ARightEqvImp);
				
				Automaton ARightEqvImpNeg = objMakeAutomaton.Negation(ARightEqvImp);
				Automaton ABimpTerm2 = objMakeAutomaton.Union(ARightEqvImpNeg, ALeftEqvImp);
				
				Automaton A1BimpA2 = objMakeAutomaton.Intersect(ABimpTerm1, ABimpTerm2);
				return A1BimpA2;
				
			default:
				return null;
		}
	}
	
}