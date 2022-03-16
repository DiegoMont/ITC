// Generated from marzo.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link marzoParser}.
 */
public interface marzoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link marzoParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(marzoParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link marzoParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(marzoParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suma}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuma(marzoParser.SumaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suma}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuma(marzoParser.SumaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code division}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDivision(marzoParser.DivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code division}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDivision(marzoParser.DivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multiplicacion}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicacion(marzoParser.MultiplicacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multiplicacion}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicacion(marzoParser.MultiplicacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaria}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaria(marzoParser.PrimariaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primaria}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaria(marzoParser.PrimariaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code resta}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterResta(marzoParser.RestaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code resta}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitResta(marzoParser.RestaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mayor_que}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMayor_que(marzoParser.Mayor_queContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mayor_que}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMayor_que(marzoParser.Mayor_queContext ctx);
	/**
	 * Enter a parse tree produced by the {@code menor_que}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMenor_que(marzoParser.Menor_queContext ctx);
	/**
	 * Exit a parse tree produced by the {@code menor_que}
	 * labeled alternative in {@link marzoParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMenor_que(marzoParser.Menor_queContext ctx);
}