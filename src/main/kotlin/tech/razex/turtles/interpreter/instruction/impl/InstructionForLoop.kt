package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.util.Type.*
import kotlin.math.sin

@Arguments(types = [STRING, INT, INT])
@Instruction(name = "forloop", aliases = ["for", "loop"])
class InstructionForLoop : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        if(interpreter.allocatedInts[args[0] as String] != null) {
            interpreter.allocatedInts[args[0] as String] = (sin(args[1] as Double) * 2).toInt()
        }
    }

}
