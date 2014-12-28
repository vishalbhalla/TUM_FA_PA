package automatons;

import static org.junit.Assert.*;

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

}
