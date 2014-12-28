/**
 * 
 */
package automatons;

import static org.junit.Assert.*;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

/**
 * @author Vishal
 *
 */
public class ComplementTest {
	
	@Test
	public void test() 
	{
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		
		Automaton A = MakeAutomata.EmptyAutomation(ss);
		
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		Automaton AComplement = objMakeAutomaton.Complement(A);
		
		String dottyformatOp = objMakeAutomaton.ToString(AComplement);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
	}

}
