package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.interpreter.instruction.Instruction

@Instruction(name = "pop", aliases = [])
class InstructionPop : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.shouldColor = false
    }


}
