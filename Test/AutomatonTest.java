package automatons;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;




public class AutomatonTest {
/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
*/	
	@Test
	public void mytest() {
		
		String[] vars = new String [] {"x", "y"};
		ListAutomaton a = new ListAutomaton(vars);
		
		System.out.println(a.toString());
		
		boolean w[] = new boolean [] { false, false };
		ArrayList<boolean[]> word = new ArrayList<boolean[]>();
		word.add(w);
		
		assertTrue("00 must be accepted", a.member(word));
	}

}
