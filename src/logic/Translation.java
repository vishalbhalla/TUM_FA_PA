package logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

import parsing.*;
import parsing.otree.*;
import automatons.Automaton;
import automatons.MakeAutomata;
import automatons.Transitions;



public class Translation {

	
	/**
	 * divides i by two and then floors, s.th div2floor(-1)=2
	 * @param i
	 * @return
	 * @author max
	 */
	static int div2floor(int i) {
		if(i>=0) return i/2;
		else if(i%2==0) return i/2;
		else return (i-1)/2;
	}
	
	/**
	 * translates an Atomic formula of Presburger Arithmetic
	 * into a deterministic finite automaton
	 * @param objlscoeff 
	 * @param objlsvar 
	 * @param rightside
	 * @author max 
	 * @return
	 */
	public static Automaton AF2DFA(int rightside, ArrayList<String> objlsvar, ArrayList<Integer> objlscoeff) {
		
		// check whether input is alright, i.e. length of ArrayLists are the same
		if(objlsvar.size()==0 || objlsvar.size()!=objlscoeff.size()) {
			System.out.println("ERROR: Arrays not same size or empty!");
			return null;
		}
		
		int n = objlsvar.size();
		
		Automaton A = new Automaton();
		
		// initialize
		A.startState = ""+rightside;
		A.variables = new TreeSet<String>();
		A.variables.addAll(objlsvar);
		
		Queue<String> q = new LinkedList<String>();
		q.add(A.startState);
		
		while(!q.isEmpty()) {
			String sk = q.poll();
			if(A.trans.containsKey(sk)) { // already visited
				//System.out.println("Skipping" + sk);
				continue;
			}
			//System.out.println("Processing " + sk);
			int k = Integer.parseInt(sk);
			HashMap<String, Transitions> outofsk = new HashMap<String, Transitions>();
			A.trans.put(sk, outofsk);
			if(k>=0) A.finalState.add(sk);
			
			for(int i=0; i< (1<<n); ++i) {
				// calculate the j
				int j = k;
				int rest = i;
				for(int m=0; m<n; m++) {
					j -= (rest%2)*objlscoeff.get(m); // TODO: is this the right order, or should n-m be used?
					rest /= 2;
				}				
				j = div2floor(j);
				// add to queue and transition table
				String sj = ""+j;
				if(!A.trans.containsKey(sj)) q.add(sj);
				if(!outofsk.containsKey(sj))
					outofsk.put(sj, new Transitions());
				//System.out.println("add transition " + sk + "->" + sj + " labelled " + i);
				outofsk.get(sj).addTransition(i, n);
			}
		}
		
		return A;
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
				Automaton ANegSon1 = objMakeAutomaton.Complement(ASon1);
				Automaton AAll = objMakeAutomaton.Project(ANegSon1, varname1);
				return AAll;
				
				// all the comparators starting a atomic formula
            case PALexer.EQ : { // case Ab = x, transformed to Ab <= x && !(Ab<=x-1)
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
				
				Automaton left = AF2DFA(rightside, objlsvar, objlscoeff);
				Automaton right = objMakeAutomaton.Complement(AF2DFA(rightside-1, objlsvar, objlscoeff));
				
				return objMakeAutomaton.Intersect(left, right);
			}
            case PALexer.NEQ :{ // case Ab != x, transformed to !(Ab <= x) || Ab<=x-1
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
				
				Automaton left = objMakeAutomaton.Complement(AF2DFA(rightside, objlsvar, objlscoeff));
				Automaton right = AF2DFA(rightside-1, objlsvar, objlscoeff);
				
				return objMakeAutomaton.Union(left, right);
			}
            case PALexer.GEQ :{ // case Ab >= x, transformed to !(Ab <= x-1)
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
								
				return objMakeAutomaton.Complement(AF2DFA(rightside-1, objlsvar, objlscoeff));
			}
            case PALexer.LEQ : { // standard case <=
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
				
				return AF2DFA(rightside, objlsvar, objlscoeff); }
				
            case PALexer.GT : { // case Ab > x, transformed to !(Ab<=x)
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
				
				return objMakeAutomaton.Complement(AF2DFA(rightside, objlsvar, objlscoeff));
            }
            case PALexer.LT : { // case  Ab < x tranformed to Ab <= x-1  
				AF objAF = (AF)objtree;
				int rightside = objAF.rightside;
				ArrayList<String> objlsvar = objAF.lsvar;
				ArrayList<Integer> objlscoeff = objAF.lscoeff;
				
				return AF2DFA(rightside-1, objlsvar, objlscoeff); }
						
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
				UnTree u = (UnTree)objtree;				
				Automaton NegA = objMakeAutomaton.Complement(pa2fa(u.son));
				return NegA;
			
			case PALexer.IMP:
				BinTree objBinTreeImp = (BinTree)objtree;
				Automaton ALeftImp = pa2fa(objBinTreeImp.left);
				Automaton ALeftImpNeg = objMakeAutomaton.Complement(ALeftImp);
				Automaton ARightImp = pa2fa(objBinTreeImp.right);
				Automaton A1ImpA2 = objMakeAutomaton.Union(ALeftImpNeg, ARightImp);
				return A1ImpA2;
				
			case PALexer.EQV:		
				BinTree objBinTreeEqvImp = (BinTree)objtree;
				Automaton ALeftEqvImp = pa2fa(objBinTreeEqvImp.left);
				Automaton ALeftEqvImpNeg = objMakeAutomaton.Complement(ALeftEqvImp);
				Automaton ARightEqvImp = pa2fa(objBinTreeEqvImp.right);
				Automaton ABimpTerm1 = objMakeAutomaton.Union(ALeftEqvImpNeg, ARightEqvImp);
				
				Automaton ARightEqvImpNeg = objMakeAutomaton.Complement(ARightEqvImp);
				Automaton ABimpTerm2 = objMakeAutomaton.Union(ARightEqvImpNeg, ALeftEqvImp);
				
				Automaton A1BimpA2 = objMakeAutomaton.Intersect(ABimpTerm1, ABimpTerm2);
				return A1BimpA2;
				
			default:
				return null;
		}
	}
	
}