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
	public void test_easyrevmin() throws IOException {
		System.out.println("========= TEST EASYrev with minimization ==========");
		Automaton A = PA2FA.dothejob("Test/easyrev.txt");
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,4})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {4,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3,0})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,5})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,1})));
		
		System.out.println("minimized Automaton");
		Automaton Amin = MakeAutomata.minimize(A);
		String dottyformatOpmin = MakeAutomata.ToString(Amin);
		System.out.println(dottyformatOpmin);
		
		
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {0,4})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {4,0})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {3,0})));		
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {0,5})));		
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {0,0})));
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {1,0})));
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {0,1})));
		
	}
	
	@Test
	public void test_veryeasymin() throws IOException {
		System.out.println("========= TEST very EASYrev with minimization ==========");
		Automaton A = PA2FA.dothejob("Test/veryeasy.in"); // x<=4
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {5})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {77})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {7})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		
		System.out.println("minimized Automaton");
		Automaton Amin = MakeAutomata.minimize(A);
		String dottyformatOpmin = MakeAutomata.ToString(Amin);
		System.out.println(dottyformatOpmin);
		

		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {5})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {77})));		
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {7})));		
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {4})));
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {2})));
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {0})));
		
	}
	
	@Test
	public void test_easyrev() throws IOException {
		System.out.println("========= TEST EASYrev ==========");
		Automaton A = PA2FA.dothejob("Test/easyrev.txt");
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,4})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {4,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3,0})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,5})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,1})));
	}
	
	
	@Test
	public void test_testEx() throws IOException {
		System.out.println("========= TEST Ex ==========");
		Automaton A = PA2FA.dothejob("Test/testEx.txt"); // Ey x + y <= 2 
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {4})));		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {5})));			
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {10})));		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {2})));
	}
	@Test
	public void test_teststrangeEx() throws IOException {
		System.out.println("========= TEST strange Ex ==========");
		Automaton A = PA2FA.dothejob("Test/test_teststrangeEx.txt"); // Ex x <= 2 
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {})));
	}
	
	
	@Test
	public void test_easy() throws IOException {
		System.out.println("========= TEST EASY ==========");
		Automaton A = PA2FA.dothejob("Test/easy.txt");
		
		String dottyformatOp = MakeAutomata.ToString(A);
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
		
		String dottyformatOp = MakeAutomata.ToString(A);
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
		
		String dottyformatOp = MakeAutomata.ToString(A);
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
	public void test_NEQ() throws IOException {
		System.out.println("========= TEST using NEQ ==========");
		
		Automaton A = PA2FA.dothejob("Test/testNEQ.txt"); // x!=1
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4})));
	}
	
	
	@Test
	public void test_EQ() throws IOException {
		System.out.println("========= TEST using EQ ==========");
		
		Automaton A = PA2FA.dothejob("Test/testEQ.txt");
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {4})));
	}

	@Test
	public void test_ALL() throws IOException {
		System.out.println("========= TEST using ALL ==========");
		
		Automaton A = PA2FA.dothejob("Test/testALL.txt"); // Ax x - y >= 0
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		

		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {11})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {311})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {12})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {13})));
	}
	
	@Test
	public void test_OR() throws IOException {
		System.out.println("========= TEST using OR ==========");
		
		Automaton A = PA2FA.dothejob("Test/testOR.txt"); // x<=3 || y<=10
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		

		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {5,9})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1,1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,11})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {2,11})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {3,12})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {4,13})));
	}
	
			
	@Test
	public void test_GEQ() throws IOException {
		System.out.println("========= TEST using GEQ ==========");
		
		Automaton A = PA2FA.dothejob("Test/testGEQ.txt"); // x>=3
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {110})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {12})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0})));
	}
			
	@Test
	public void test_GT() throws IOException {
		System.out.println("========= TEST using GT ==========");
		
		Automaton A = PA2FA.dothejob("Test/testGT.txt"); // x>10
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {110})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {12})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {11})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {10})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {2})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {3})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {4})));
	}
	
	@Test
	public void test_LT() throws IOException {
		System.out.println("========= TEST using LT ==========");
		
		Automaton A = PA2FA.dothejob("Test/testLT.txt"); // x<6
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {4})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {5})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {6})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {7})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {10})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {31})));
	}
	
	@Test
	public void test_MoritzTest2() throws IOException {
		System.out.println("========= TEST using Moritz Test2 ==========");
		
		Automaton A = PA2FA.dothejob("Test/test2.txt"); // ((x<15 && y>=15) && x+y+z <=15)
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		for(int i=0; i<=16; i++) {
			for(int j=0; j<=16-i; j++) {
				for(int k=0; k<=16-i-j; k++) {
					System.out.print(i + " " + j + " " + k + " -> " );
					boolean erg = MakeAutomata.member(A, wordfromints(new int[] {i,j,k}));
					if((i<15 && j>=15) && i+j+k <=15) {
						//if(!erg) System.out.println("should be True but isnt -----------\n");
						//else  System.out.println("should be True and is\n");
						assertTrue(erg);						
					} else {
						//if(erg) System.out.println("should be False but isnt -----------\n");
						//else  System.out.println("should be False and is\n");
						assertFalse(erg);
					}
				}
			}
		}
		/*
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,15,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,0,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {1,15,0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0,15,1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {15,15,15})));
		
		// with renaming:
		
		Automaton Are = MakeAutomata.DFAWithRemappedStates(A);
		
		dottyformatOp = MakeAutomata.ToString(Are);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(Are, wordfromints(new int[] {0,15,0})));
		assertFalse(MakeAutomata.member(Are, wordfromints(new int[] {0,0,0})));
		assertFalse(MakeAutomata.member(Are, wordfromints(new int[] {1,15,0})));
		assertFalse(MakeAutomata.member(Are, wordfromints(new int[] {0,15,1})));
		assertFalse(MakeAutomata.member(Are, wordfromints(new int[] {15,15,15})));
		
		
		// with minimization:
		
		Automaton Amin = MakeAutomata.minimize(Are);
		
		dottyformatOp = MakeAutomata.ToString(Amin);
		System.out.println(dottyformatOp);
		
		assertTrue(MakeAutomata.member(Amin, wordfromints(new int[] {0,15,0})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {0,0,0})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {1,15,0})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {0,15,1})));
		assertFalse(MakeAutomata.member(Amin, wordfromints(new int[] {15,15,15})));
		*/
	}
	
	
	@Test
	public void test_MoritzTest3_2() throws IOException {
		System.out.println("========= TEST using Moritz Test3 ' ==========");
		
		Automaton A = PA2FA.dothejob("Test/test3_2.txt"); // Ez x+y+z+u>=15
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0,0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {11,1,1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {5,0,0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {15,0,0})));
		//assertTrue(MakeAutomata.member(A, wordfromints(new int[] {25})));
	}
	
	@Test
	public void test_MoritzTest3() throws IOException {
		System.out.println("========= TEST using Moritz Test3 ==========");
		
		Automaton A = PA2FA.dothejob("Test/test3.txt"); // ExAyEz x+y+z+u>=15
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {5})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {15})));
		//assertTrue(MakeAutomata.member(A, wordfromints(new int[] {25})));
	}
	
	
	@Test
	public void test_MoritzTest6() throws IOException {
		System.out.println("========= TEST using Moritz Test6 ==========");
		
		Automaton A = PA2FA.dothejob("Test/test6.txt"); // (x>=2 && x<=2)
		
		String dottyformatOp = MakeAutomata.ToString(A);
		System.out.println(dottyformatOp);
		
		
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {0})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {1})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {5})));
		assertFalse(MakeAutomata.member(A, wordfromints(new int[] {15})));
		assertTrue(MakeAutomata.member(A, wordfromints(new int[] {2})));
	}
	
}
