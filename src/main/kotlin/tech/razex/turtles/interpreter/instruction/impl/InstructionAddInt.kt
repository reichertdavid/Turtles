package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.util.Type.*

@Arguments(types = [STRING, INT])
@Instruction(name = "addint", aliases = ["addi"])
class InstructionAddInt : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        if(interpreter.allocatedInts[args[0] as String] != null) {
            interpreter.allocatedInts[args[0] as String] = interpreter.allocatedInts[args[0] as String]!! + args[1] as Int
        }
    }

}
