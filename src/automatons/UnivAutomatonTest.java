package automatons;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class UnivAutomatonTest {

	
	


	
	
	@Test
	public void test() {
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
		
	}
	
	
	@Test
	public void test_determinize() {
		System.out.println("======= Test determinize ======");
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		System.out.println("======= universalAuto with var x ======");
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);

		System.out.println("======= NFA2DFA ======");
		ss.add("y");		
		Automaton Adet = MakeAutomata.determinize(A);
		String Adetout = objMakeAutomaton.ToString(Adet);
		System.out.println(Adetout);
	
	}
	
	
	@Test
	public void test_minimize() {
		System.out.println("======= Test minimize ======");
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		System.out.println("======= universalAuto with var x ======");
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);

		System.out.println("======= minimized ======");
		ss.add("y");		
		Automaton Amin = MakeAutomata.minimize(A);
		String Aminout = objMakeAutomaton.ToString(Amin);
		System.out.println(Aminout);
		
		{
			boolean[] character = new boolean[] { true};
			ArrayList<boolean[]> word = new ArrayList<boolean[]>();
			word.add(character);
			assertTrue(MakeAutomata.member(Amin, word));
		}{
			boolean[] character = new boolean[] { false};
			ArrayList<boolean[]> word = new ArrayList<boolean[]>();
			word.add(character);
			assertTrue(MakeAutomata.member(Amin, word));
		}
	}
	
	
	@Test
	public void test_extendTo() {
		System.out.println("======= Test extendTO ======");
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();

		System.out.println("======= universalAuto with var x ======");
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		/*
		System.out.println("======= universalAuto with var x (should change nothing) ======");
		Automaton Aextended = MakeAutomata.extendTo(A, ss);
		dottyformatOp = objMakeAutomaton.ToString(Aextended);
		System.out.println(dottyformatOp);
		*/
		System.out.println("======= universalAuto with var x, y(should change nothing) ======");
		ss.add("y");		
		Automaton Aextended2 = MakeAutomata.extendTo(A, ss);
		dottyformatOp = objMakeAutomaton.ToString(Aextended2);
		System.out.println(dottyformatOp);

		{
			boolean[] character = new boolean[] { true, true};
			ArrayList<boolean[]> word = new ArrayList<boolean[]>();
			word.add(character);
			assertTrue(MakeAutomata.member(Aextended2, word));
		}{
			boolean[] character = new boolean[] { true, false};
			ArrayList<boolean[]> word = new ArrayList<boolean[]>();
			word.add(character);
			assertTrue(MakeAutomata.member(Aextended2, word));
		}{
			boolean[] character = new boolean[] { false, true};
			ArrayList<boolean[]> word = new ArrayList<boolean[]>();
			word.add(character);
			assertTrue(MakeAutomata.member(Aextended2, word));
		}{
			boolean[] character = new boolean[] { false, false};
			ArrayList<boolean[]> word = new ArrayList<boolean[]>();
			word.add(character);
			assertTrue(MakeAutomata.member(Aextended2, word));
		}
	}
	
	
	
	

	@Test
	public void test_accept() {
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		boolean[] character = new boolean[] { true, true, true};
		ArrayList<boolean[]> word = new ArrayList<boolean[]>();
		word.add(character);

		// must accept (111)
		assertTrue(MakeAutomata.member(A, word));

		word.add(character);

		
		// and also (111) (111)
		assertTrue(MakeAutomata.member(A, word));
		
	}
	
	@Test
	public void test_fromString() {

		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		
		Automaton A2 = MakeAutomata.FromString(dottyformatOp);

		boolean[] character = new boolean[] { true, true, true};
		ArrayList<boolean[]> word = new ArrayList<boolean[]>();
		word.add(character);

		// must accept (111)
		assertTrue(MakeAutomata.member(A2, word));

		word.add(character);

		
		// and also (111) (111)
		assertTrue(MakeAutomata.member(A2, word));

		
	}
	
	@Test
	public void test_project() {
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A = MakeAutomata.UniversalAutomation(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();

		System.out.println("UniversalAutomaton with 3 variables x y z");
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);

		A = objMakeAutomaton.Project(A, "x");
		
		System.out.println("Now projected for variable x");
		System.out.println(objMakeAutomaton.ToString(A));
		
		assertTrue(dottyformatOp!=null);
		
	}

}
