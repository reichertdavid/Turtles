package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.util.Type.*
import kotlin.math.sin

@Arguments(types = [STRING])
@Instruction(name = "printstring", aliases = ["prints"])
class InstructionPrintString : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        print(args[0])
    }

}
