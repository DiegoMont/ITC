from antlr.marzoListener import marzoListener
from antlr.marzoParser import marzoParser

class GenCode(marzoListener):
    
    def enterProgram(self, ctx:marzoParser.ProgramContext):
        print(".text")
        print(".globl main")
        print("main:")

    def exitPrimaria(self, ctx:marzoParser.PrimariaContext):
        self._print_instruction("load", ["$1", ctx.Numero().getText()])

    def exitSuma(self, ctx:marzoParser.SumaContext):
        print("ADD")

    def _print_instruction(self, word, arguments):
        line = "    {word}".format(word=word)
        for argument in arguments:
            line += " {arg}".format(arg=argument)
        print(line)
