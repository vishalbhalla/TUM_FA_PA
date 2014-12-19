package parsing;
// $ANTLR 3.5.1 /home/max/Dropbox/11. Semester/Automaten/pa/PA.g 2014-12-02 14:36:36

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class PAParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALL", "AND", "EQ", "EQV", "EX", 
		"GEQ", "GT", "IMP", "INT", "LEQ", "LT", "MINUS", "NEG", "NEQ", "OR", "PLUS", 
		"VAR", "WS", "'!'", "'!='", "'&&'", "'('", "')'", "'+'", "'-'", "'->'", 
		"'<'", "'<->'", "'<='", "'=='", "'>'", "'>='", "'A'", "'E'", "'||'"
	};
	public static final int EOF=-1;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int ALL=4;
	public static final int AND=5;
	public static final int EQ=6;
	public static final int EQV=7;
	public static final int EX=8;
	public static final int GEQ=9;
	public static final int GT=10;
	public static final int IMP=11;
	public static final int INT=12;
	public static final int LEQ=13;
	public static final int LT=14;
	public static final int MINUS=15;
	public static final int NEG=16;
	public static final int NEQ=17;
	public static final int OR=18;
	public static final int PLUS=19;
	public static final int VAR=20;
	public static final int WS=21;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public PAParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public PAParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return PAParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/home/max/Dropbox/11. Semester/Automaten/pa/PA.g"; }


	public static class formula_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "formula"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:21:1: formula : ( linearpred | '!' formula -> ^( NEG[\"not\"] formula ) | '(' formula con formula ')' -> ^( con formula formula ) | quan VAR formula -> ^( quan VAR formula ) );
	public final PAParser.formula_return formula() throws RecognitionException {
		PAParser.formula_return retval = new PAParser.formula_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal2=null;
		Token char_literal4=null;
		Token char_literal8=null;
		Token VAR10=null;
		ParserRuleReturnScope linearpred1 =null;
		ParserRuleReturnScope formula3 =null;
		ParserRuleReturnScope formula5 =null;
		ParserRuleReturnScope con6 =null;
		ParserRuleReturnScope formula7 =null;
		ParserRuleReturnScope quan9 =null;
		ParserRuleReturnScope formula11 =null;

		Object char_literal2_tree=null;
		Object char_literal4_tree=null;
		Object char_literal8_tree=null;
		Object VAR10_tree=null;
		RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
		RewriteRuleTokenStream stream_22=new RewriteRuleTokenStream(adaptor,"token 22");
		RewriteRuleTokenStream stream_25=new RewriteRuleTokenStream(adaptor,"token 25");
		RewriteRuleTokenStream stream_26=new RewriteRuleTokenStream(adaptor,"token 26");
		RewriteRuleSubtreeStream stream_con=new RewriteRuleSubtreeStream(adaptor,"rule con");
		RewriteRuleSubtreeStream stream_quan=new RewriteRuleSubtreeStream(adaptor,"rule quan");
		RewriteRuleSubtreeStream stream_formula=new RewriteRuleSubtreeStream(adaptor,"rule formula");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:22:3: ( linearpred | '!' formula -> ^( NEG[\"not\"] formula ) | '(' formula con formula ')' -> ^( con formula formula ) | quan VAR formula -> ^( quan VAR formula ) )
			int alt1=4;
			switch ( input.LA(1) ) {
			case INT:
			case VAR:
			case 27:
			case 28:
				{
				alt1=1;
				}
				break;
			case 22:
				{
				alt1=2;
				}
				break;
			case 25:
				{
				alt1=3;
				}
				break;
			case 36:
			case 37:
				{
				alt1=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:22:5: linearpred
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_linearpred_in_formula101);
					linearpred1=linearpred();
					state._fsp--;

					adaptor.addChild(root_0, linearpred1.getTree());

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:23:5: '!' formula
					{
					char_literal2=(Token)match(input,22,FOLLOW_22_in_formula107);  
					stream_22.add(char_literal2);

					pushFollow(FOLLOW_formula_in_formula109);
					formula3=formula();
					state._fsp--;

					stream_formula.add(formula3.getTree());
					// AST REWRITE
					// elements: formula
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 23:33: -> ^( NEG[\"not\"] formula )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:23:36: ^( NEG[\"not\"] formula )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NEG, "not"), root_1);
						adaptor.addChild(root_1, stream_formula.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:24:5: '(' formula con formula ')'
					{
					char_literal4=(Token)match(input,25,FOLLOW_25_in_formula140);  
					stream_25.add(char_literal4);

					pushFollow(FOLLOW_formula_in_formula142);
					formula5=formula();
					state._fsp--;

					stream_formula.add(formula5.getTree());
					pushFollow(FOLLOW_con_in_formula144);
					con6=con();
					state._fsp--;

					stream_con.add(con6.getTree());
					pushFollow(FOLLOW_formula_in_formula146);
					formula7=formula();
					state._fsp--;

					stream_formula.add(formula7.getTree());
					char_literal8=(Token)match(input,26,FOLLOW_26_in_formula148);  
					stream_26.add(char_literal8);

					// AST REWRITE
					// elements: formula, con, formula
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 24:33: -> ^( con formula formula )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:24:36: ^( con formula formula )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_con.nextNode(), root_1);
						adaptor.addChild(root_1, stream_formula.nextTree());
						adaptor.addChild(root_1, stream_formula.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:25:5: quan VAR formula
					{
					pushFollow(FOLLOW_quan_in_formula164);
					quan9=quan();
					state._fsp--;

					stream_quan.add(quan9.getTree());
					VAR10=(Token)match(input,VAR,FOLLOW_VAR_in_formula166);  
					stream_VAR.add(VAR10);

					pushFollow(FOLLOW_formula_in_formula168);
					formula11=formula();
					state._fsp--;

					stream_formula.add(formula11.getTree());
					// AST REWRITE
					// elements: formula, quan, VAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 25:33: -> ^( quan VAR formula )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:25:36: ^( quan VAR formula )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_quan.nextNode(), root_1);
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_1, stream_formula.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "formula"


	public static class linearpred_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "linearpred"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:28:1: linearpred : linearterm comp cons -> ^( comp cons linearterm ) ;
	public final PAParser.linearpred_return linearpred() throws RecognitionException {
		PAParser.linearpred_return retval = new PAParser.linearpred_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope linearterm12 =null;
		ParserRuleReturnScope comp13 =null;
		ParserRuleReturnScope cons14 =null;

		RewriteRuleSubtreeStream stream_comp=new RewriteRuleSubtreeStream(adaptor,"rule comp");
		RewriteRuleSubtreeStream stream_linearterm=new RewriteRuleSubtreeStream(adaptor,"rule linearterm");
		RewriteRuleSubtreeStream stream_cons=new RewriteRuleSubtreeStream(adaptor,"rule cons");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:29:3: ( linearterm comp cons -> ^( comp cons linearterm ) )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:29:5: linearterm comp cons
			{
			pushFollow(FOLLOW_linearterm_in_linearpred204);
			linearterm12=linearterm();
			state._fsp--;

			stream_linearterm.add(linearterm12.getTree());
			pushFollow(FOLLOW_comp_in_linearpred206);
			comp13=comp();
			state._fsp--;

			stream_comp.add(comp13.getTree());
			pushFollow(FOLLOW_cons_in_linearpred208);
			cons14=cons();
			state._fsp--;

			stream_cons.add(cons14.getTree());
			// AST REWRITE
			// elements: linearterm, cons, comp
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 29:26: -> ^( comp cons linearterm )
			{
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:29:29: ^( comp cons linearterm )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_comp.nextNode(), root_1);
				adaptor.addChild(root_1, stream_cons.nextTree());
				adaptor.addChild(root_1, stream_linearterm.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "linearpred"


	public static class linearterm_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "linearterm"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:32:1: linearterm : linmon ( sgnlinmon )* ;
	public final PAParser.linearterm_return linearterm() throws RecognitionException {
		PAParser.linearterm_return retval = new PAParser.linearterm_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope linmon15 =null;
		ParserRuleReturnScope sgnlinmon16 =null;


		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:33:3: ( linmon ( sgnlinmon )* )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:33:5: linmon ( sgnlinmon )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_linmon_in_linearterm234);
			linmon15=linmon();
			state._fsp--;

			adaptor.addChild(root_0, linmon15.getTree());

			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:33:12: ( sgnlinmon )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= 27 && LA2_0 <= 28)) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:33:13: sgnlinmon
					{
					pushFollow(FOLLOW_sgnlinmon_in_linearterm237);
					sgnlinmon16=sgnlinmon();
					state._fsp--;

					adaptor.addChild(root_0, sgnlinmon16.getTree());

					}
					break;

				default :
					break loop2;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "linearterm"


	public static class linmon_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "linmon"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:36:1: linmon : ( ( '+' )? INT VAR -> ^( PLUS[\"+\"] INT VAR ) | '-' INT VAR -> ^( MINUS[\"-\"] INT VAR ) | ( '+' )? VAR -> ^( PLUS[\"+\"] INT[\"1\"] VAR ) | '-' VAR -> ^( MINUS[\"-\"] INT[\"1\"] VAR ) );
	public final PAParser.linmon_return linmon() throws RecognitionException {
		PAParser.linmon_return retval = new PAParser.linmon_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal17=null;
		Token INT18=null;
		Token VAR19=null;
		Token char_literal20=null;
		Token INT21=null;
		Token VAR22=null;
		Token char_literal23=null;
		Token VAR24=null;
		Token char_literal25=null;
		Token VAR26=null;

		Object char_literal17_tree=null;
		Object INT18_tree=null;
		Object VAR19_tree=null;
		Object char_literal20_tree=null;
		Object INT21_tree=null;
		Object VAR22_tree=null;
		Object char_literal23_tree=null;
		Object VAR24_tree=null;
		Object char_literal25_tree=null;
		Object VAR26_tree=null;
		RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
		RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
		RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
		RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:37:3: ( ( '+' )? INT VAR -> ^( PLUS[\"+\"] INT VAR ) | '-' INT VAR -> ^( MINUS[\"-\"] INT VAR ) | ( '+' )? VAR -> ^( PLUS[\"+\"] INT[\"1\"] VAR ) | '-' VAR -> ^( MINUS[\"-\"] INT[\"1\"] VAR ) )
			int alt5=4;
			switch ( input.LA(1) ) {
			case 27:
				{
				int LA5_1 = input.LA(2);
				if ( (LA5_1==INT) ) {
					alt5=1;
				}
				else if ( (LA5_1==VAR) ) {
					alt5=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case INT:
				{
				alt5=1;
				}
				break;
			case 28:
				{
				int LA5_3 = input.LA(2);
				if ( (LA5_3==INT) ) {
					alt5=2;
				}
				else if ( (LA5_3==VAR) ) {
					alt5=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 3, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case VAR:
				{
				alt5=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}
			switch (alt5) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:37:5: ( '+' )? INT VAR
					{
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:37:5: ( '+' )?
					int alt3=2;
					int LA3_0 = input.LA(1);
					if ( (LA3_0==27) ) {
						alt3=1;
					}
					switch (alt3) {
						case 1 :
							// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:37:5: '+'
							{
							char_literal17=(Token)match(input,27,FOLLOW_27_in_linmon254);  
							stream_27.add(char_literal17);

							}
							break;

					}

					INT18=(Token)match(input,INT,FOLLOW_INT_in_linmon257);  
					stream_INT.add(INT18);

					VAR19=(Token)match(input,VAR,FOLLOW_VAR_in_linmon259);  
					stream_VAR.add(VAR19);

					// AST REWRITE
					// elements: VAR, INT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 37:18: -> ^( PLUS[\"+\"] INT VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:37:21: ^( PLUS[\"+\"] INT VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PLUS, "+"), root_1);
						adaptor.addChild(root_1, stream_INT.nextNode());
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:38:5: '-' INT VAR
					{
					char_literal20=(Token)match(input,28,FOLLOW_28_in_linmon277);  
					stream_28.add(char_literal20);

					INT21=(Token)match(input,INT,FOLLOW_INT_in_linmon280);  
					stream_INT.add(INT21);

					VAR22=(Token)match(input,VAR,FOLLOW_VAR_in_linmon282);  
					stream_VAR.add(VAR22);

					// AST REWRITE
					// elements: INT, VAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 38:18: -> ^( MINUS[\"-\"] INT VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:38:21: ^( MINUS[\"-\"] INT VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS, "-"), root_1);
						adaptor.addChild(root_1, stream_INT.nextNode());
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:39:5: ( '+' )? VAR
					{
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:39:5: ( '+' )?
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0==27) ) {
						alt4=1;
					}
					switch (alt4) {
						case 1 :
							// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:39:5: '+'
							{
							char_literal23=(Token)match(input,27,FOLLOW_27_in_linmon299);  
							stream_27.add(char_literal23);

							}
							break;

					}

					VAR24=(Token)match(input,VAR,FOLLOW_VAR_in_linmon302);  
					stream_VAR.add(VAR24);

					// AST REWRITE
					// elements: VAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 39:18: -> ^( PLUS[\"+\"] INT[\"1\"] VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:39:21: ^( PLUS[\"+\"] INT[\"1\"] VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PLUS, "+"), root_1);
						adaptor.addChild(root_1, (Object)adaptor.create(INT, "1"));
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:40:5: '-' VAR
					{
					char_literal25=(Token)match(input,28,FOLLOW_28_in_linmon324);  
					stream_28.add(char_literal25);

					VAR26=(Token)match(input,VAR,FOLLOW_VAR_in_linmon327);  
					stream_VAR.add(VAR26);

					// AST REWRITE
					// elements: VAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 40:18: -> ^( MINUS[\"-\"] INT[\"1\"] VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:40:21: ^( MINUS[\"-\"] INT[\"1\"] VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS, "-"), root_1);
						adaptor.addChild(root_1, (Object)adaptor.create(INT, "1"));
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "linmon"


	public static class sgnlinmon_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "sgnlinmon"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:43:1: sgnlinmon : ( '+' INT VAR -> ^( PLUS[\"+\"] INT VAR ) | '-' INT VAR -> ^( MINUS[\"-\"] INT VAR ) | '+' VAR -> ^( PLUS[\"+\"] INT[\"1\"] VAR ) | '-' VAR -> ^( MINUS[\"-\"] INT[\"1\"] VAR ) );
	public final PAParser.sgnlinmon_return sgnlinmon() throws RecognitionException {
		PAParser.sgnlinmon_return retval = new PAParser.sgnlinmon_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal27=null;
		Token INT28=null;
		Token VAR29=null;
		Token char_literal30=null;
		Token INT31=null;
		Token VAR32=null;
		Token char_literal33=null;
		Token VAR34=null;
		Token char_literal35=null;
		Token VAR36=null;

		Object char_literal27_tree=null;
		Object INT28_tree=null;
		Object VAR29_tree=null;
		Object char_literal30_tree=null;
		Object INT31_tree=null;
		Object VAR32_tree=null;
		Object char_literal33_tree=null;
		Object VAR34_tree=null;
		Object char_literal35_tree=null;
		Object VAR36_tree=null;
		RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
		RewriteRuleTokenStream stream_VAR=new RewriteRuleTokenStream(adaptor,"token VAR");
		RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
		RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:44:3: ( '+' INT VAR -> ^( PLUS[\"+\"] INT VAR ) | '-' INT VAR -> ^( MINUS[\"-\"] INT VAR ) | '+' VAR -> ^( PLUS[\"+\"] INT[\"1\"] VAR ) | '-' VAR -> ^( MINUS[\"-\"] INT[\"1\"] VAR ) )
			int alt6=4;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==27) ) {
				int LA6_1 = input.LA(2);
				if ( (LA6_1==INT) ) {
					alt6=1;
				}
				else if ( (LA6_1==VAR) ) {
					alt6=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 6, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA6_0==28) ) {
				int LA6_2 = input.LA(2);
				if ( (LA6_2==INT) ) {
					alt6=2;
				}
				else if ( (LA6_2==VAR) ) {
					alt6=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 6, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:44:5: '+' INT VAR
					{
					char_literal27=(Token)match(input,27,FOLLOW_27_in_sgnlinmon358);  
					stream_27.add(char_literal27);

					INT28=(Token)match(input,INT,FOLLOW_INT_in_sgnlinmon360);  
					stream_INT.add(INT28);

					VAR29=(Token)match(input,VAR,FOLLOW_VAR_in_sgnlinmon362);  
					stream_VAR.add(VAR29);

					// AST REWRITE
					// elements: VAR, INT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 44:17: -> ^( PLUS[\"+\"] INT VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:44:20: ^( PLUS[\"+\"] INT VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PLUS, "+"), root_1);
						adaptor.addChild(root_1, stream_INT.nextNode());
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:45:5: '-' INT VAR
					{
					char_literal30=(Token)match(input,28,FOLLOW_28_in_sgnlinmon380);  
					stream_28.add(char_literal30);

					INT31=(Token)match(input,INT,FOLLOW_INT_in_sgnlinmon382);  
					stream_INT.add(INT31);

					VAR32=(Token)match(input,VAR,FOLLOW_VAR_in_sgnlinmon384);  
					stream_VAR.add(VAR32);

					// AST REWRITE
					// elements: VAR, INT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 45:17: -> ^( MINUS[\"-\"] INT VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:45:20: ^( MINUS[\"-\"] INT VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS, "-"), root_1);
						adaptor.addChild(root_1, stream_INT.nextNode());
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:46:5: '+' VAR
					{
					char_literal33=(Token)match(input,27,FOLLOW_27_in_sgnlinmon401);  
					stream_27.add(char_literal33);

					VAR34=(Token)match(input,VAR,FOLLOW_VAR_in_sgnlinmon403);  
					stream_VAR.add(VAR34);

					// AST REWRITE
					// elements: VAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 46:17: -> ^( PLUS[\"+\"] INT[\"1\"] VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:46:20: ^( PLUS[\"+\"] INT[\"1\"] VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PLUS, "+"), root_1);
						adaptor.addChild(root_1, (Object)adaptor.create(INT, "1"));
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:47:5: '-' VAR
					{
					char_literal35=(Token)match(input,28,FOLLOW_28_in_sgnlinmon425);  
					stream_28.add(char_literal35);

					VAR36=(Token)match(input,VAR,FOLLOW_VAR_in_sgnlinmon427);  
					stream_VAR.add(VAR36);

					// AST REWRITE
					// elements: VAR
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 47:17: -> ^( MINUS[\"-\"] INT[\"1\"] VAR )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:47:20: ^( MINUS[\"-\"] INT[\"1\"] VAR )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS, "-"), root_1);
						adaptor.addChild(root_1, (Object)adaptor.create(INT, "1"));
						adaptor.addChild(root_1, stream_VAR.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sgnlinmon"


	public static class cons_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "cons"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:50:1: cons : ( ( '+' )? INT -> ^( PLUS[\"+\"] INT ) | '-' INT -> ^( MINUS[\"-\"] INT ) );
	public final PAParser.cons_return cons() throws RecognitionException {
		PAParser.cons_return retval = new PAParser.cons_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal37=null;
		Token INT38=null;
		Token char_literal39=null;
		Token INT40=null;

		Object char_literal37_tree=null;
		Object INT38_tree=null;
		Object char_literal39_tree=null;
		Object INT40_tree=null;
		RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
		RewriteRuleTokenStream stream_27=new RewriteRuleTokenStream(adaptor,"token 27");
		RewriteRuleTokenStream stream_28=new RewriteRuleTokenStream(adaptor,"token 28");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:51:3: ( ( '+' )? INT -> ^( PLUS[\"+\"] INT ) | '-' INT -> ^( MINUS[\"-\"] INT ) )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==INT||LA8_0==27) ) {
				alt8=1;
			}
			else if ( (LA8_0==28) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:51:5: ( '+' )? INT
					{
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:51:5: ( '+' )?
					int alt7=2;
					int LA7_0 = input.LA(1);
					if ( (LA7_0==27) ) {
						alt7=1;
					}
					switch (alt7) {
						case 1 :
							// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:51:5: '+'
							{
							char_literal37=(Token)match(input,27,FOLLOW_27_in_cons459);  
							stream_27.add(char_literal37);

							}
							break;

					}

					INT38=(Token)match(input,INT,FOLLOW_INT_in_cons462);  
					stream_INT.add(INT38);

					// AST REWRITE
					// elements: INT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 51:14: -> ^( PLUS[\"+\"] INT )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:51:17: ^( PLUS[\"+\"] INT )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(PLUS, "+"), root_1);
						adaptor.addChild(root_1, stream_INT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:52:5: '-' INT
					{
					char_literal39=(Token)match(input,28,FOLLOW_28_in_cons478);  
					stream_28.add(char_literal39);

					INT40=(Token)match(input,INT,FOLLOW_INT_in_cons480);  
					stream_INT.add(INT40);

					// AST REWRITE
					// elements: INT
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 52:14: -> ^( MINUS[\"-\"] INT )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:52:17: ^( MINUS[\"-\"] INT )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(MINUS, "-"), root_1);
						adaptor.addChild(root_1, stream_INT.nextNode());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "cons"


	public static class quan_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "quan"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:55:1: quan : ( 'A' -> ^( ALL[\"forall\"] ) | 'E' -> ^( EX[\"exists\"] ) );
	public final PAParser.quan_return quan() throws RecognitionException {
		PAParser.quan_return retval = new PAParser.quan_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal41=null;
		Token char_literal42=null;

		Object char_literal41_tree=null;
		Object char_literal42_tree=null;
		RewriteRuleTokenStream stream_36=new RewriteRuleTokenStream(adaptor,"token 36");
		RewriteRuleTokenStream stream_37=new RewriteRuleTokenStream(adaptor,"token 37");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:55:6: ( 'A' -> ^( ALL[\"forall\"] ) | 'E' -> ^( EX[\"exists\"] ) )
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==36) ) {
				alt9=1;
			}
			else if ( (LA9_0==37) ) {
				alt9=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}

			switch (alt9) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:55:8: 'A'
					{
					char_literal41=(Token)match(input,36,FOLLOW_36_in_quan501);  
					stream_36.add(char_literal41);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 55:12: -> ^( ALL[\"forall\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:55:15: ^( ALL[\"forall\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(ALL, "forall"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:56:8: 'E'
					{
					char_literal42=(Token)match(input,37,FOLLOW_37_in_quan517);  
					stream_37.add(char_literal42);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 56:12: -> ^( EX[\"exists\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:56:15: ^( EX[\"exists\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EX, "exists"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "quan"


	public static class con_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "con"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:59:1: con : ( '&&' -> ^( AND[\"and\"] ) | '||' -> ^( OR[\"or\"] ) | '->' -> ^( IMP[\"implies\"] ) | '<->' -> ^( EQV[\"iff\"] ) );
	public final PAParser.con_return con() throws RecognitionException {
		PAParser.con_return retval = new PAParser.con_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal43=null;
		Token string_literal44=null;
		Token string_literal45=null;
		Token string_literal46=null;

		Object string_literal43_tree=null;
		Object string_literal44_tree=null;
		Object string_literal45_tree=null;
		Object string_literal46_tree=null;
		RewriteRuleTokenStream stream_31=new RewriteRuleTokenStream(adaptor,"token 31");
		RewriteRuleTokenStream stream_24=new RewriteRuleTokenStream(adaptor,"token 24");
		RewriteRuleTokenStream stream_29=new RewriteRuleTokenStream(adaptor,"token 29");
		RewriteRuleTokenStream stream_38=new RewriteRuleTokenStream(adaptor,"token 38");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:59:5: ( '&&' -> ^( AND[\"and\"] ) | '||' -> ^( OR[\"or\"] ) | '->' -> ^( IMP[\"implies\"] ) | '<->' -> ^( EQV[\"iff\"] ) )
			int alt10=4;
			switch ( input.LA(1) ) {
			case 24:
				{
				alt10=1;
				}
				break;
			case 38:
				{
				alt10=2;
				}
				break;
			case 29:
				{
				alt10=3;
				}
				break;
			case 31:
				{
				alt10=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}
			switch (alt10) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:59:7: '&&'
					{
					string_literal43=(Token)match(input,24,FOLLOW_24_in_con538);  
					stream_24.add(string_literal43);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 59:13: -> ^( AND[\"and\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:59:16: ^( AND[\"and\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(AND, "and"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:60:7: '||'
					{
					string_literal44=(Token)match(input,38,FOLLOW_38_in_con554);  
					stream_38.add(string_literal44);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 60:13: -> ^( OR[\"or\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:60:16: ^( OR[\"or\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(OR, "or"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:61:7: '->'
					{
					string_literal45=(Token)match(input,29,FOLLOW_29_in_con570);  
					stream_29.add(string_literal45);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 61:13: -> ^( IMP[\"implies\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:61:16: ^( IMP[\"implies\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(IMP, "implies"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:62:7: '<->'
					{
					string_literal46=(Token)match(input,31,FOLLOW_31_in_con586);  
					stream_31.add(string_literal46);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 62:13: -> ^( EQV[\"iff\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:62:16: ^( EQV[\"iff\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EQV, "iff"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "con"


	public static class comp_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "comp"
	// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:65:1: comp : ( '==' -> ^( EQ[\"eq\"] ) | '!=' -> ^( NEQ[\"neq\"] ) | '>=' -> ^( GEQ[\"geq\"] ) | '<=' -> ^( LEQ[\"leq\"] ) | '>' -> ^( GT[\"gt\"] ) | '<' -> ^( LT[\"lt\"] ) );
	public final PAParser.comp_return comp() throws RecognitionException {
		PAParser.comp_return retval = new PAParser.comp_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token string_literal47=null;
		Token string_literal48=null;
		Token string_literal49=null;
		Token string_literal50=null;
		Token char_literal51=null;
		Token char_literal52=null;

		Object string_literal47_tree=null;
		Object string_literal48_tree=null;
		Object string_literal49_tree=null;
		Object string_literal50_tree=null;
		Object char_literal51_tree=null;
		Object char_literal52_tree=null;
		RewriteRuleTokenStream stream_30=new RewriteRuleTokenStream(adaptor,"token 30");
		RewriteRuleTokenStream stream_32=new RewriteRuleTokenStream(adaptor,"token 32");
		RewriteRuleTokenStream stream_35=new RewriteRuleTokenStream(adaptor,"token 35");
		RewriteRuleTokenStream stream_23=new RewriteRuleTokenStream(adaptor,"token 23");
		RewriteRuleTokenStream stream_33=new RewriteRuleTokenStream(adaptor,"token 33");
		RewriteRuleTokenStream stream_34=new RewriteRuleTokenStream(adaptor,"token 34");

		try {
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:65:6: ( '==' -> ^( EQ[\"eq\"] ) | '!=' -> ^( NEQ[\"neq\"] ) | '>=' -> ^( GEQ[\"geq\"] ) | '<=' -> ^( LEQ[\"leq\"] ) | '>' -> ^( GT[\"gt\"] ) | '<' -> ^( LT[\"lt\"] ) )
			int alt11=6;
			switch ( input.LA(1) ) {
			case 33:
				{
				alt11=1;
				}
				break;
			case 23:
				{
				alt11=2;
				}
				break;
			case 35:
				{
				alt11=3;
				}
				break;
			case 32:
				{
				alt11=4;
				}
				break;
			case 34:
				{
				alt11=5;
				}
				break;
			case 30:
				{
				alt11=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:65:8: '=='
					{
					string_literal47=(Token)match(input,33,FOLLOW_33_in_comp606);  
					stream_33.add(string_literal47);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 65:13: -> ^( EQ[\"eq\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:65:16: ^( EQ[\"eq\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(EQ, "eq"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:66:8: '!='
					{
					string_literal48=(Token)match(input,23,FOLLOW_23_in_comp622);  
					stream_23.add(string_literal48);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 66:13: -> ^( NEQ[\"neq\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:66:16: ^( NEQ[\"neq\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(NEQ, "neq"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 3 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:67:8: '>='
					{
					string_literal49=(Token)match(input,35,FOLLOW_35_in_comp638);  
					stream_35.add(string_literal49);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 67:13: -> ^( GEQ[\"geq\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:67:16: ^( GEQ[\"geq\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(GEQ, "geq"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 4 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:68:8: '<='
					{
					string_literal50=(Token)match(input,32,FOLLOW_32_in_comp654);  
					stream_32.add(string_literal50);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 68:13: -> ^( LEQ[\"leq\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:68:16: ^( LEQ[\"leq\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LEQ, "leq"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 5 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:69:8: '>'
					{
					char_literal51=(Token)match(input,34,FOLLOW_34_in_comp670);  
					stream_34.add(char_literal51);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 69:13: -> ^( GT[\"gt\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:69:16: ^( GT[\"gt\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(GT, "gt"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 6 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:70:8: '<'
					{
					char_literal52=(Token)match(input,30,FOLLOW_30_in_comp687);  
					stream_30.add(char_literal52);

					// AST REWRITE
					// elements: 
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 70:13: -> ^( LT[\"lt\"] )
					{
						// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:70:16: ^( LT[\"lt\"] )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(LT, "lt"), root_1);
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp"

	// Delegated rules



	public static final BitSet FOLLOW_linearpred_in_formula101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_22_in_formula107 = new BitSet(new long[]{0x000000301A501000L});
	public static final BitSet FOLLOW_formula_in_formula109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_formula140 = new BitSet(new long[]{0x000000301A501000L});
	public static final BitSet FOLLOW_formula_in_formula142 = new BitSet(new long[]{0x00000040A1000000L});
	public static final BitSet FOLLOW_con_in_formula144 = new BitSet(new long[]{0x000000301A501000L});
	public static final BitSet FOLLOW_formula_in_formula146 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_formula148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_quan_in_formula164 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_formula166 = new BitSet(new long[]{0x000000301A501000L});
	public static final BitSet FOLLOW_formula_in_formula168 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_linearterm_in_linearpred204 = new BitSet(new long[]{0x0000000F40800000L});
	public static final BitSet FOLLOW_comp_in_linearpred206 = new BitSet(new long[]{0x0000000018001000L});
	public static final BitSet FOLLOW_cons_in_linearpred208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_linmon_in_linearterm234 = new BitSet(new long[]{0x0000000018000002L});
	public static final BitSet FOLLOW_sgnlinmon_in_linearterm237 = new BitSet(new long[]{0x0000000018000002L});
	public static final BitSet FOLLOW_27_in_linmon254 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_INT_in_linmon257 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_linmon259 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_linmon277 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_INT_in_linmon280 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_linmon282 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_linmon299 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_linmon302 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_linmon324 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_linmon327 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_sgnlinmon358 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_INT_in_sgnlinmon360 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_sgnlinmon362 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_sgnlinmon380 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_INT_in_sgnlinmon382 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_sgnlinmon384 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_sgnlinmon401 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_sgnlinmon403 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_sgnlinmon425 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_VAR_in_sgnlinmon427 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_cons459 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_INT_in_cons462 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_cons478 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_INT_in_cons480 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_36_in_quan501 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_37_in_quan517 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_con538 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_38_in_con554 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_29_in_con570 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_con586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_33_in_comp606 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_23_in_comp622 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_comp638 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_32_in_comp654 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_34_in_comp670 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_comp687 = new BitSet(new long[]{0x0000000000000002L});
}
