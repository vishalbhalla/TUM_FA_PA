package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import automatons.Automaton;
import automatons.MakeAutomata;

public class TranslationTest {

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
	}

}
