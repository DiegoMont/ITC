from antlr.marzoListener import marzoListener
from antlr.marzoParser import marzoParser

class GenCode(marzoListener):
    
    def enterProgram(self, ctx:marzoParser.ProgramContext):
        print(".text")

    def exitPrimaria(self, ctx:marzoParser.PrimariaContext):
        print("load $1, " + ctx.Numero().getText())

    def exitSuma(self, ctx:marzoParser.SumaContext):
        print("ADD")
