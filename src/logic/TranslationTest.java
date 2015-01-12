package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import automatons.Automaton;
import automatons.MakeAutomata;

public class TranslationTest {


	private boolean notzero(int[] ints) {
		for(int i=0; i<ints.length; i++) {
			if(ints[i]>0) return true;
		}
		return false;
	}
	
	private ArrayList<boolean[]> wordfromints(int[] ints) {
		
		ArrayList<boolean[]> word = new ArrayList<boolean[]>();

		while(notzero(ints)) {
			boolean[] e = new boolean[ints.length];
			for(int i=0; i<ints.length; i++) {
				e[i] = (ints[i]%2)==1;
				ints[i] /= 2;
			}
			word.add(e);
		}
		return word;
	}
	
	
	@Test
	public void test_AF2DFA() {
		MakeAutomata objMakeAutomaton = new MakeAutomata();

		ArrayList<String> objlsvar = new ArrayList<String>();
		objlsvar.add("x");
		ArrayList<Integer> objlscoeff = new ArrayList<Integer>();
		objlscoeff.add(new Integer(1));
		Automaton A = Translation.AF2DFA(1, objlsvar, objlscoeff);

		System.out.println("The Ineqation: x <= 1");
		
		System.out.println("The resulting Automaton");
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {7})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {10})));
		
	}

}
