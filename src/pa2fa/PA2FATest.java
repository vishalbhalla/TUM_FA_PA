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
		
		Automaton A = PA2FA.dothejob("Test/easy.txt");
		
		boolean[] character = new boolean[] { true, true, true};
		ArrayList<boolean[]> word = new ArrayList<boolean[]>();
		word.add(character);

		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,4})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,3})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {5,0})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1,0})));
	}

}
