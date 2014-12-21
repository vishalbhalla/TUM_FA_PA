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
		Automaton A1 = Automaton.UniversalAutomation(ss);
		
		Automaton A2 = Automaton.EmptyAutomation(ss);
		
		Automaton objAutomaton = new Automaton();
		Automaton A = objAutomaton.Union(A2,A1);
		A.variables = ss;
		System.out.println(A.toString());
		
	}
	
	@Test
	public void IntersectionTest() {
		
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton A1 = Automaton.UniversalAutomation(ss);
		
		Automaton A2 = Automaton.EmptyAutomation(ss);
		
		Automaton objAutomaton = new Automaton();
		Automaton A = objAutomaton.Intersect(A2,A1);
		A.variables = ss;
		System.out.println(A.toString());
		
	}

}
