from antlr.marzoListener import marzoListener
from antlr.marzoParser import marzoParser

class GenCode(marzoListener):

    def __init__(self):
        super().__init__()
        self._is_t0_free = True
    
    def enterProgram(self, ctx:marzoParser.ProgramContext):
        print(".text")
        print(".globl main")
        print("main:")

    def exitProgram(self, ctx: marzoParser.ProgramContext):
        self._print_instruction("li", ["$v0", "10"])
        self._print_instruction("syscall")

    def exitAsignacion(self, ctx:marzoParser.AsignacionContext):
        self._print_instruction("la", ["$t0", "$v0"])
        self._is_t0_free = True

    def exitPrimaria(self, ctx:marzoParser.PrimariaContext):
        value = ctx.Numero().getText()
        if self._is_t0_free:
            self._print_instruction("li", ["$v0", value])
            self._is_t0_free = False
        else:
            self._print_instruction("li", ["$v1", value])
    
    def exitDivision(self, ctx:marzoParser.MultiplicacionContext):
        self._print_instruction("divu", ["$v0", "$v0", "$v1"])

    def exitMultiplicacion(self, ctx:marzoParser.MultiplicacionContext):
        self._print_instruction("mul", ["$v0, $v0", "$v1"])

    def exitResta(self, ctx:marzoParser.RestaContext):
        self._print_instruction("sub", ["$v0", "$v0", "$v1"])

    def exitSuma(self, ctx:marzoParser.SumaContext):
        self._print_instruction("add", ["$v0", "$v0", "$v1"])

    def _print_instruction(self, word, arguments=None):
        if arguments is None:
            arguments = []
        line = "    {word}".format(word=word)
        args_count = len(arguments)
        i = 0
        if i < args_count:
            line += " {arg}".format(arg=arguments[i])
            i+=1
        while i < args_count:
            line += ", {arg}".format(arg=arguments[i])
            i+=1
        print(line)
