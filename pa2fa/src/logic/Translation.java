package logic;
import java.util.ArrayList;

import org.antlr.runtime.tree.CommonTree;

import parsing.*;
import parsing.otree.*;

import automatons.Automaton;
import automatons.ListAutomaton;



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
		
	    Automaton A;
		switch(objtree.type) {

			case PALexer.EX :
				Quant ex = (Quant)objtree;
				String varname = ex.var;
				OTree son  = ex.son;
				A = pa2fa(son);
				A.project(varname);
				
			case PALexer.ALL :
				Quant ex1 = (Quant)objtree;
				String varname1 = ex1.var;
				OTree son1  = ex1.son;
				A = pa2fa(son1);
				A = A.Negation();
				A.project(varname1);
				
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
				A = ALeftAnd.intersect(ARightAnd);
			
			case PALexer.OR:
				BinTree objBinTreeOr = (BinTree)objtree;
				Automaton ALeftOr = pa2fa(objBinTreeOr.left);
				Automaton ARightOr = pa2fa(objBinTreeOr.right);
				A = ALeftOr.union(ARightOr);
					
			case PALexer.NEG:		
				A = pa2fa(objtree).Negation();
			
			case PALexer.IMP:
				BinTree objBinTreeImp = (BinTree)objtree;
				Automaton ALeftImp = pa2fa(objBinTreeImp.left);
				Automaton ALeftImpNeg = ALeftImp.Negation();
				Automaton ARightImp = pa2fa(objBinTreeImp.right);
				A = ALeftImpNeg.union(ARightImp);
				
			case PALexer.EQV:		
				BinTree objBinTreeEqvImp = (BinTree)objtree;
				Automaton ALeftEqvImp = pa2fa(objBinTreeEqvImp.left);
				Automaton ALeftEqvImpNeg = ALeftEqvImp.Negation();
				Automaton ARightEqvImp = pa2fa(objBinTreeEqvImp.right);
				Automaton ABimpTerm1 = ALeftEqvImpNeg.union(ARightEqvImp);
				
				Automaton ARightEqvImpNeg = ARightEqvImp.Negation();
				Automaton ABimpTerm2 = ARightEqvImpNeg.union(ALeftEqvImp);
				
				A = ABimpTerm1.intersect(ABimpTerm2);
				
			default:
				return null;
		}
	}
	
}