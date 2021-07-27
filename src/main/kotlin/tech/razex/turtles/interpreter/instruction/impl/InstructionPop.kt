package tech.razex.turtles.interpreter.instruction.impl

import tech.razex.turtles.interpreter.Interpreter
import tech.razex.turtles.interpreter.exceptions.InstructionOutOfCanvasException
import tech.razex.turtles.interpreter.instruction.Arguments
import tech.razex.turtles.interpreter.instruction.IInstruction
import tech.razex.turtles.interpreter.instruction.Instruction
import tech.razex.turtles.util.Type
import java.awt.Point
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

@Instruction(name = "pop", aliases = [])
class InstructionPop : IInstruction {

    override fun execute(interpreter: Interpreter, args: Array<Any>) {
        interpreter.shouldColor = false
    }


}
