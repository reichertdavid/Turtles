package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.util.Type.*
import kotlin.math.sin

@Instruction(name = "newline", aliases = ["nl"])
class InstructionNewLine : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        println()
    }

}
