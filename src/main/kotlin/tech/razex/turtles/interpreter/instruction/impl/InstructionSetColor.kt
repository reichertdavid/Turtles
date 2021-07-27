package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.util.Type


@Arguments(types = [Type.INT])
@Instruction(name = "setcolor", aliases = ["color", "col"])
class InstructionSetColor : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.currentColor = args[0] as Int
    }


}
