package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.IInstruction
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.util.Type
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * This instructions stops everything and finally creates the image
 */
@Arguments(types = [Type.INT])
@Instruction(name = "setcolor", aliases = ["color", "col"])
class InstructionSetColor : IInstruction {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.currentColor = args[0] as Int
    }


}
