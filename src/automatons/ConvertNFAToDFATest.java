package automatons;

import static org.junit.Assert.*;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.Test;

public class ConvertNFAToDFATest {

	/*	
	@Test
	public void ConvertNFAToDFAWith2VariablesTest() 
	{
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		ss.add("z");
		Automaton aNFA = MakeAutomata.CreateStubNFAWith3Variables(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		String dottyformatOp = objMakeAutomaton.ToString(aNFA);
		System.out.println(dottyformatOp);
		
		Automaton aDFA = objMakeAutomaton.ConvertNFAToDFA(aNFA);
		dottyformatOp = objMakeAutomaton.ToString(aDFA);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
	}*/

	/*
	@Test
	public void ConvertNFAToDFAWith2VariablesTest() 
	{
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		Automaton aNFA = MakeAutomata.CreateStubNFAWith2Variables(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		String dottyformatOp = objMakeAutomaton.ToString(aNFA);
		System.out.println(dottyformatOp);
		
		Automaton aDFA = objMakeAutomaton.ConvertNFAToDFA(aNFA);
		dottyformatOp = objMakeAutomaton.ToString(aDFA);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
	}
	*/
	
	@Test
	public void ConvertNFAToDFAWithDeadAndUnreachableStatesTest() 
	{
		SortedSet<String> ss = new TreeSet<String>();
		ss.add("x");
		ss.add("y");
		Automaton aNFA = MakeAutomata.CreateStubNFAWithDeadAndUnreachableStates(ss);
		MakeAutomata objMakeAutomaton = new MakeAutomata();
		String dottyformatOp = objMakeAutomaton.ToString(aNFA);
		System.out.println(dottyformatOp);
		
		Automaton aDFA = objMakeAutomaton.ConvertNFAToDFA(aNFA);
		dottyformatOp = objMakeAutomaton.ToString(aDFA);
		System.out.println(dottyformatOp);
		
		assertTrue(dottyformatOp!=null);
	}

}
