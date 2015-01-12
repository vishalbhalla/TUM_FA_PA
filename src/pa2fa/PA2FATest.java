package pa2fa;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import automatons.Automaton;
import automatons.MakeAutomata;

public class PA2FATest {

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
	public void test_easy() throws IOException {
		System.out.println("========= TEST EASY ==========");
		Automaton A = PA2FA.dothejob("Test/easy.txt");
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,4})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,3})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {5,0})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1,0})));
	}
	
	
	@Test
	public void test_negation() throws IOException {
		System.out.println("========= TEST negation ==========");
		Automaton A = PA2FA.dothejob("Test/negtest.txt");
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);

		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {5})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {6})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {7})));
	}
	
	
	@Test
	public void test_and() throws IOException {
		System.out.println("========= TEST using AND ==========");
		
		Automaton A = PA2FA.dothejob("Test/usingand1.txt");
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,4})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,3})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {5,0})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1,0})));
	}

}
