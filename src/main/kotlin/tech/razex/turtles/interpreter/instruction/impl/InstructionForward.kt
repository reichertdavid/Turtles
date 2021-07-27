package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.exceptions.InstructionOutOfCanvasException
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.util.Type

/**
 * This instructions stops everything and finally creates the image
 */
@Arguments([Type.INT])
@Instruction(name = "forward", aliases = ["fd"])
class InstructionForward : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        for(i in 0 until args[0] as Int) {
            interpreter.position.x++

            val x = interpreter.position.x
            val y = interpreter.position.y
            val width = interpreter.canvasData.size - 1
            val height = interpreter.canvasData[0].size - 1

            if(x < 0 || x > width ||
                y < 0 || y > height) {
                throw InstructionOutOfCanvasException("Turtle position went outside of canvas (line: ${interpreter.currentLine})! Position is [$x, $y] but canvas maximum is [$width, $height]")
            }

            if(interpreter.shouldColor) {
                interpreter.canvasData[interpreter.position.x][interpreter.position.y] = interpreter.currentColor
            }
        }
    }


}
