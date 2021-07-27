package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.interpreter.instruction.Instruction


@Instruction(name = "push", aliases = [])
class InstructionPush : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.shouldColor = true
    }


}
