package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.exceptions.InstructionOutOfCanvasException
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.AbstractInstruction
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.util.Type
import java.awt.Point

/**
 * This instructions stops everything and finally creates the image
 */
@Arguments(types = [Type.INT, Type.INT])
@Instruction(name = "setpos", aliases = ["setxy", "xy", "pos"])
class InstructionSetPos : AbstractInstruction() {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        val x = args[0] as Int
        val y = args[1] as Int
        val width = interpreter.canvasData.size - 1
        val height = interpreter.canvasData[0].size - 1

        if(x < 0 || x > width ||
            y < 0 || y > height) {
            throw InstructionOutOfCanvasException("Turtle position went outside of canvas (line: ${interpreter.currentLine})! Position is [$x, $y] but canvas maximum is [$width, $height]")
        }
        interpreter.position = Point(args[0] as Int, args[1] as Int)
    }


}
