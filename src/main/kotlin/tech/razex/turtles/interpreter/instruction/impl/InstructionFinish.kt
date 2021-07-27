package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.instruction.IInstruction
import tech.razex.turtles.interpreter.instruction.Instruction
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * This instructions stops everything and finally creates the image
 */
@Instruction(name = "finish", aliases = ["end", "stop"])
class InstructionFinish : IInstruction {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        val image = BufferedImage(interpreter.canvasData.size, interpreter.canvasData[0].size, BufferedImage.TYPE_INT_RGB)

        interpreter.canvasData.forEachIndexed { x, yValues ->
            yValues.forEachIndexed { y, color ->
                image.setRGB(x, y, color)
            }
        }

        ImageIO.write(image, "PNG", File("${interpreter.file.nameWithoutExtension}.png"))
    }


}
