// Generated from marzo.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class marzoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Numero=7, WS=8;
	public static final int
		RULE_program = 0, RULE_expression = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'<'", "'>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "Numero", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "marzo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public marzoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(5); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(4);
				expression(0);
				}
				}
				setState(7); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Numero );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SumaContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SumaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterSuma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitSuma(this);
		}
	}
	public static class DivisionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public DivisionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitDivision(this);
		}
	}
	public static class MultiplicacionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MultiplicacionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterMultiplicacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitMultiplicacion(this);
		}
	}
	public static class PrimariaContext extends ExpressionContext {
		public TerminalNode Numero() { return getToken(marzoParser.Numero, 0); }
		public PrimariaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterPrimaria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitPrimaria(this);
		}
	}
	public static class RestaContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public RestaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterResta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitResta(this);
		}
	}
	public static class Mayor_queContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Mayor_queContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterMayor_que(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitMayor_que(this);
		}
	}
	public static class Menor_queContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public Menor_queContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).enterMenor_que(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof marzoListener ) ((marzoListener)listener).exitMenor_que(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new PrimariaContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(10);
			match(Numero);
			}
			_ctx.stop = _input.LT(-1);
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(30);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new SumaContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(12);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(13);
						match(T__0);
						setState(14);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new RestaContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(15);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(16);
						match(T__1);
						setState(17);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new MultiplicacionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(18);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(19);
						match(T__2);
						setState(20);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new DivisionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(21);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(22);
						match(T__3);
						setState(23);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new Menor_queContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(24);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(25);
						match(T__4);
						setState(26);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new Mayor_queContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(27);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(28);
						match(T__5);
						setState(29);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n&\4\2\t\2\4\3\t"+
		"\3\3\2\6\2\b\n\2\r\2\16\2\t\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\3\3"+
		"\2\3\4\4\2\4\2\2\2*\2\7\3\2\2\2\4\13\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b"+
		"\t\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n\3\3\2\2\2\13\f\b\3\1\2\f\r\7\t\2"+
		"\2\r\"\3\2\2\2\16\17\f\t\2\2\17\20\7\3\2\2\20!\5\4\3\n\21\22\f\b\2\2\22"+
		"\23\7\4\2\2\23!\5\4\3\t\24\25\f\7\2\2\25\26\7\5\2\2\26!\5\4\3\b\27\30"+
		"\f\6\2\2\30\31\7\6\2\2\31!\5\4\3\7\32\33\f\5\2\2\33\34\7\7\2\2\34!\5\4"+
		"\3\6\35\36\f\4\2\2\36\37\7\b\2\2\37!\5\4\3\5 \16\3\2\2\2 \21\3\2\2\2 "+
		"\24\3\2\2\2 \27\3\2\2\2 \32\3\2\2\2 \35\3\2\2\2!$\3\2\2\2\" \3\2\2\2\""+
		"#\3\2\2\2#\5\3\2\2\2$\"\3\2\2\2\5\t \"";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}