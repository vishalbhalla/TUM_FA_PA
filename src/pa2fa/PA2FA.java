package pa2fa;

import java.io.*;
import java.util.ArrayList;

import logic.Translation;

import org.antlr.runtime.ANTLRStringStream;

//import antlr.RecognitionException;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.CommonTree;

import parsing.*;
import parsing.otree.*;

import automatons.Automaton;




public class PA2FA {

	/**
	 * reads from a file with filename @file into a String
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String file) throws IOException{
		
		BufferedReader reader = new BufferedReader( new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.seperator");
		
		while( (line = reader.readLine()) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( ls );
		}
		
		reader.close();
		
		return stringBuilder.toString();
		
	}

	/* 
	 * FUNCTIONS TO CONVERT FROM CommonTree to OTree
	 */
	
	public static int convertSign(CommonTree t) {
		return t.getType()==PALexer.MINUS ? -1 : 1;
	}
	
	public static Integer convertInteger(CommonTree b) {
    	int mult = convertSign(b);
    	return new Integer(b.getChild(0).getText()) * mult;		
	}
	
	public static int convertCoeff(CommonTree v) {
    	int m = convertSign(v);  
    	return Integer.parseInt(v.getChild(0).getText())*m;  
	}
	public static String convertVariable(CommonTree v) {
    	return v.getText();	
	}
	
	/**
	 * function to convert a Formaula from a normal CommonTree
	 * into a Abstract Syntax Tree in the OTree format
	 * @param ast
	 * @return
	 */
	public static OTree convertFormulaTree(CommonTree ast) {
		
        int token = ast.getToken().getType();

        switch (token) {
        
        		// the quantifiers
        		case PALexer.ALL : 
        		case PALexer.EX : {         	
                	Quant q = new Quant();
                	q.type = token;
                	
                	q.var = convertVariable((CommonTree)ast.getChild(0));
                	q.son = convertFormulaTree((CommonTree)ast.getChild(1));

                	
                	return q;
                }

        		// the unary boolean connectives
        		case PALexer.NEG : {         	
                	UnTree q = new UnTree();
                	q.type = token;
                
                	q.son = convertFormulaTree((CommonTree)ast.getChild(0));
                	                	
                	return q;
                }

        		// all the binary boolean connectives
        		case PALexer.OR:
        		case PALexer.AND:
        		case PALexer.IMP:
        		case PALexer.EQV: {         	
                	BinTree q = new BinTree();
                	q.type = token;

                	q.left = convertFormulaTree((CommonTree)ast.getChild(0));
                	q.right = convertFormulaTree((CommonTree)ast.getChild(1));
                	
                	return q;
                }

        		
        		// all the comperators starting a atomic formula
                case PALexer.EQ :
                case PALexer.NEQ :
                case PALexer.GEQ :
                case PALexer.LEQ :
                case PALexer.GT :
                case PALexer.LT : {                	
                	AF formula = new AF();
                	formula.type = token;
                	formula.rightside = convertInteger((CommonTree)ast.getChild(0));
                	formula.lscoeff = new ArrayList<Integer>();
                	formula.lsvar = new ArrayList<String>();
                	for(int i=1; i<ast.getChildCount(); ++i) {
                		CommonTree v = (CommonTree)ast.getChild(i);
                		formula.lscoeff.add(convertCoeff(v));
                		formula.lsvar.add(convertVariable((CommonTree)v.getChild(1)));
                	}
                	return formula;
                }
                default:
                	return null;
        }
	}

	
	
	/**
	 * uses the ANTLR framework to parse the input string @expr
	 * after that it employs convertFormulaTree inorder to
	 * convert it into a OTree, this is then returned
	 * @param expr
	 * @return
	 */
	public static OTree compile(String expr) {
		try {
			// lexer
			ANTLRStringStream input = new ANTLRStringStream(expr);
			TokenStream tokens = new CommonTokenStream(new PALexer(input));
			
			// parser
			PAParser parser = new PAParser(tokens);
			PAParser.formula_return ret = parser.formula(); // formula ist das toplevel "prädikat"
			CommonTree ast = (CommonTree) ret.getTree();
			
			
			return convertFormulaTree(ast);
		
		} catch(RecognitionException e) {
			System.out.println("some error");
			return null;
		}
	}
	
	
	
	/**
	 * the main function, handels parameter passing, and starts 
	 * appropriate the algorithm
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// handle arguments
		
		// read file
		String text = readFile("/home/max/Dropbox/11. Semester/Automaten/pa/test/test1.txt");
		
		// parse input
		OTree ast = compile(text);
		
		// lets see the formula again:
		System.out.println(ast.toString());
		
		// translate presburger arithmetic expression to DFA/NFA
		Automaton out = Translation.pa2fa(ast);
		
		System.out.println(out.toString());
		
	}
	
}
