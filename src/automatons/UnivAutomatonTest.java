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
