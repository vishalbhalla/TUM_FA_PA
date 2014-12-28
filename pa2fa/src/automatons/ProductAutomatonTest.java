package automatons;

import static org.junit.Assert.*;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class ProductAutomatonTest {

	@Test
	public void UnionTest() {
		
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A1 = MakeAutomata.UniversalAutomation(ss);
		
		Automaton A2 = MakeAutomata.EmptyAutomation(ss);
		
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		Automaton A = objMakeAutomaton.Union(A2,A1);
		
		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
		
	}
	
	@Test
	public void IntersectionTest() {
		
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A1 = MakeAutomata.UniversalAutomation(ss);
		
		Automaton A2 = MakeAutomata.EmptyAutomation(ss);
		
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		Automaton A = objMakeAutomaton.Intersect(A2,A1);

		String dottyformatOp = objMakeAutomaton.ToString(A);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);

	}

}
