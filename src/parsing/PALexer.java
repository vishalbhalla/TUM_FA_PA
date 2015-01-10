package parsing;
// $ANTLR 3.5.1 /home/max/Dropbox/11. Semester/Automaten/pa/PA.g 2014-12-02 14:36:40

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class PALexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public PALexer() {} 
	public PALexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public PALexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "/home/max/Dropbox/11. Semester/Automaten/pa/PA.g"; }

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:2:7: ( '!' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:2:9: '!'
			{
			match('!'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:3:7: ( '!=' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:3:9: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:4:7: ( '&&' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:4:9: '&&'
			{
			match("&&"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:5:7: ( '(' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:5:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:6:7: ( ')' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:6:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:7:7: ( '+' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:7:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:8:7: ( '-' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:8:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:9:7: ( '->' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:9:9: '->'
			{
			match("->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:10:7: ( '<' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:10:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:11:7: ( '<->' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:11:9: '<->'
			{
			match("<->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:12:7: ( '<=' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:12:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:13:7: ( '==' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:13:9: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:14:7: ( '>' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:14:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:15:7: ( '>=' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:15:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:16:7: ( 'A' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:16:9: 'A'
			{
			match('A'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:17:7: ( 'E' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:17:9: 'E'
			{
			match('E'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:18:7: ( '||' )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:18:9: '||'
			{
			match("||"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "VAR"
	public final void mVAR() throws RecognitionException {
		try {
			int _type = VAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:73:6: ( ( 'a' .. 'z' ) ( 'a' .. 'z' | '0' .. '9' | '_' )* )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:73:9: ( 'a' .. 'z' ) ( 'a' .. 'z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:73:19: ( 'a' .. 'z' | '0' .. '9' | '_' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VAR"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:75:5: ( ( '0' .. '9' )+ )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:75:7: ( '0' .. '9' )+
			{
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:75:7: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:77:4: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:77:6: ( ' ' | '\\n' | '\\t' | '\\r' )+
			{
			// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:77:6: ( ' ' | '\\n' | '\\t' | '\\r' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\t' && LA3_0 <= '\n')||LA3_0=='\r'||LA3_0==' ') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:8: ( T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | VAR | INT | WS )
		int alt4=20;
		switch ( input.LA(1) ) {
		case '!':
			{
			int LA4_1 = input.LA(2);
			if ( (LA4_1=='=') ) {
				alt4=2;
			}

			else {
				alt4=1;
			}

			}
			break;
		case '&':
			{
			alt4=3;
			}
			break;
		case '(':
			{
			alt4=4;
			}
			break;
		case ')':
			{
			alt4=5;
			}
			break;
		case '+':
			{
			alt4=6;
			}
			break;
		case '-':
			{
			int LA4_6 = input.LA(2);
			if ( (LA4_6=='>') ) {
				alt4=8;
			}

			else {
				alt4=7;
			}

			}
			break;
		case '<':
			{
			switch ( input.LA(2) ) {
			case '-':
				{
				alt4=10;
				}
				break;
			case '=':
				{
				alt4=11;
				}
				break;
			default:
				alt4=9;
			}
			}
			break;
		case '=':
			{
			alt4=12;
			}
			break;
		case '>':
			{
			int LA4_9 = input.LA(2);
			if ( (LA4_9=='=') ) {
				alt4=14;
			}

			else {
				alt4=13;
			}

			}
			break;
		case 'A':
			{
			alt4=15;
			}
			break;
		case 'E':
			{
			alt4=16;
			}
			break;
		case '|':
			{
			alt4=17;
			}
			break;
		case 'a':
		case 'b':
		case 'c':
		case 'd':
		case 'e':
		case 'f':
		case 'g':
		case 'h':
		case 'i':
		case 'j':
		case 'k':
		case 'l':
		case 'm':
		case 'n':
		case 'o':
		case 'p':
		case 'q':
		case 'r':
		case 's':
		case 't':
		case 'u':
		case 'v':
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			{
			alt4=18;
			}
			break;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			{
			alt4=19;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt4=20;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 4, 0, input);
			throw nvae;
		}
		switch (alt4) {
			case 1 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:10: T__22
				{
				mT__22(); 

				}
				break;
			case 2 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:16: T__23
				{
				mT__23(); 

				}
				break;
			case 3 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:22: T__24
				{
				mT__24(); 

				}
				break;
			case 4 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:28: T__25
				{
				mT__25(); 

				}
				break;
			case 5 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:34: T__26
				{
				mT__26(); 

				}
				break;
			case 6 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:40: T__27
				{
				mT__27(); 

				}
				break;
			case 7 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:46: T__28
				{
				mT__28(); 

				}
				break;
			case 8 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:52: T__29
				{
				mT__29(); 

				}
				break;
			case 9 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:58: T__30
				{
				mT__30(); 

				}
				break;
			case 10 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:64: T__31
				{
				mT__31(); 

				}
				break;
			case 11 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:70: T__32
				{
				mT__32(); 

				}
				break;
			case 12 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:76: T__33
				{
				mT__33(); 

				}
				break;
			case 13 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:82: T__34
				{
				mT__34(); 

				}
				break;
			case 14 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:88: T__35
				{
				mT__35(); 

				}
				break;
			case 15 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:94: T__36
				{
				mT__36(); 

				}
				break;
			case 16 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:100: T__37
				{
				mT__37(); 

				}
				break;
			case 17 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:106: T__38
				{
				mT__38(); 

				}
				break;
			case 18 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:112: VAR
				{
				mVAR(); 

				}
				break;
			case 19 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:116: INT
				{
				mINT(); 

				}
				break;
			case 20 :
				// /home/max/Dropbox/11. Semester/Automaten/pa/PA.g:1:120: WS
				{
				mWS(); 

				}
				break;

		}
	}



}
