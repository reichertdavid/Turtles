package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.interpreter.instruction.IInstruction
import tech.razex.turtles.util.Type.INT

@Arguments(types = [ INT, INT ])
@Instruction(name = "canvas", aliases = ["cv"])
class InstructionCanvas : IInstruction {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.canvasData = Array(args[0] as Int) { IntArray(args[1] as Int) }
    }

}
