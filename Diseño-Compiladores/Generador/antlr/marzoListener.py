# Generated from marzo.g4 by ANTLR 4.9.3
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .marzoParser import marzoParser
else:
    from marzoParser import marzoParser

# This class defines a complete listener for a parse tree produced by marzoParser.
class marzoListener(ParseTreeListener):

    # Enter a parse tree produced by marzoParser#program.
    def enterProgram(self, ctx:marzoParser.ProgramContext):
        pass

    # Exit a parse tree produced by marzoParser#program.
    def exitProgram(self, ctx:marzoParser.ProgramContext):
        pass


    # Enter a parse tree produced by marzoParser#asignacion.
    def enterAsignacion(self, ctx:marzoParser.AsignacionContext):
        pass

    # Exit a parse tree produced by marzoParser#asignacion.
    def exitAsignacion(self, ctx:marzoParser.AsignacionContext):
        pass


    # Enter a parse tree produced by marzoParser#suma.
    def enterSuma(self, ctx:marzoParser.SumaContext):
        pass

    # Exit a parse tree produced by marzoParser#suma.
    def exitSuma(self, ctx:marzoParser.SumaContext):
        pass


    # Enter a parse tree produced by marzoParser#division.
    def enterDivision(self, ctx:marzoParser.DivisionContext):
        pass

    # Exit a parse tree produced by marzoParser#division.
    def exitDivision(self, ctx:marzoParser.DivisionContext):
        pass


    # Enter a parse tree produced by marzoParser#multiplicacion.
    def enterMultiplicacion(self, ctx:marzoParser.MultiplicacionContext):
        pass

    # Exit a parse tree produced by marzoParser#multiplicacion.
    def exitMultiplicacion(self, ctx:marzoParser.MultiplicacionContext):
        pass


    # Enter a parse tree produced by marzoParser#primaria.
    def enterPrimaria(self, ctx:marzoParser.PrimariaContext):
        pass

    # Exit a parse tree produced by marzoParser#primaria.
    def exitPrimaria(self, ctx:marzoParser.PrimariaContext):
        pass


    # Enter a parse tree produced by marzoParser#resta.
    def enterResta(self, ctx:marzoParser.RestaContext):
        pass

    # Exit a parse tree produced by marzoParser#resta.
    def exitResta(self, ctx:marzoParser.RestaContext):
        pass


    # Enter a parse tree produced by marzoParser#mayor_que.
    def enterMayor_que(self, ctx:marzoParser.Mayor_queContext):
        pass

    # Exit a parse tree produced by marzoParser#mayor_que.
    def exitMayor_que(self, ctx:marzoParser.Mayor_queContext):
        pass


    # Enter a parse tree produced by marzoParser#menor_que.
    def enterMenor_que(self, ctx:marzoParser.Menor_queContext):
        pass

    # Exit a parse tree produced by marzoParser#menor_que.
    def exitMenor_que(self, ctx:marzoParser.Menor_queContext):
        pass



del marzoParser