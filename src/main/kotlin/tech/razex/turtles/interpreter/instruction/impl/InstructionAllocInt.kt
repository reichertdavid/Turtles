package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.interpreter.instruction.IInstruction
import tech.razex.turtles.util.Type.*

@Arguments(types = [STRING, INT])
@Instruction(name = "allocint", aliases = ["int"])
class InstructionAllocInt : IInstruction {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.allocatedInts[args[0] as String] = args[1] as Int
    }

}
