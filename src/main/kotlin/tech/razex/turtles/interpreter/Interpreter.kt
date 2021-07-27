package tech.razex.turtles.interpreter

import tech.razex.turtles.interpreter.exceptions.InstructionNotFoundException
import tech.razex.turtles.interpreter.exceptions.InstructionArgumentException
import tech.razex.turtles.interpreter.exceptions.InstructionUnexpectedException
import tech.razex.turtles.interpreter.instruction.impl.*
import tech.razex.turtles.util.Type
import java.awt.Point
import java.io.File
import java.lang.Exception

/**
 * This will read a text file and interpret several calls line by line
 */
class Interpreter(val file: File) {

    /**
     * This will contain a list of all instructions this class can understand and execute
     */
    private val instructions = mutableListOf(
        InstructionCanvas(),
        InstructionSetPos(),
        InstructionSetColor(),
        InstructionPush(),
        InstructionPop(),
        InstructionFinish()
    )

    /**
     * contains all pixel data of our canvas
     */
    lateinit var canvasData: Array<IntArray>

    /**
     * its the current position where our "turtle" is
     */
    var position = Point(0, 0)

    /**
     * This is the current color saved as an integer
     */
    var currentColor = 0

    /**
     * This will determine if current position should be colored or not
     */
    var shouldColor = false

    /**
     * This will safe the current line so we can use it in instructions
     */
    var currentLine = 0

    /**
     * Start to interpret all instructions contained in source file
     */
    fun interpret() {
        val instructions = this.file.readLines()

        instructions.forEachIndexed { index, inst ->
            currentLine = index
            val interpretable = inst.split(" ")
            val instName = interpretable[0]
            val arguments = interpretable.drop(1)

            // get argument types to assert everything is at right place
            val argumentTypes = mutableListOf<Type>()
            arguments.forEach { s ->
                when {
                    s.matches("-?\\d+?".toRegex()) -> argumentTypes.add(Type.INT)
                    s.matches("0[xX][0-9a-fA-F]+".toRegex()) -> argumentTypes.add(Type.INT)
                    s.matches("-?\\d+(\\.\\d+)?".toRegex()) -> argumentTypes.add(Type.DECIMAL)
                    else -> argumentTypes.add(Type.STRING)
                }
            }

            val instObj = this.instructions.firstOrNull { i -> i.getName() == instName.lowercase() || i.getAliases().contains(instName.lowercase()) }
                ?: throw InstructionNotFoundException("Cannot determine instruction \"$instName\" on line ${index + 1}")

            if(instObj.getArgumentData() != null) {
                if(index == 0 && instObj::class.java != InstructionCanvas::class.java)
                    throw InstructionUnexpectedException("Could not interpret Instruction ${instObj.getName().uppercase()} on line ${index + 1}. Expected instruction CANVAS but was ${instObj.getName().uppercase()}")

                val expectedTypes = instObj.getArgumentData()!!.types

                if(!expectedTypes.contentEquals(argumentTypes.toTypedArray())) {
                    throw InstructionArgumentException("Could not interpret Instruction ${instObj.getName().uppercase()} on line ${index + 1}. Expected argument types ${expectedTypes.contentToString()} " +
                            "but was ${argumentTypes.toTypedArray().contentToString()}")
                }

                val castedArguments = Array<Any>(expectedTypes.size) {0}

                expectedTypes.forEachIndexed { index, type ->
                    when(type) {
                        Type.INT -> {
                            try {
                                castedArguments[index] = arguments[index].toInt()
                            } catch(e: Exception) {
                                castedArguments[index] = Integer.decode(arguments[index])
                            }
                        }
                        Type.DECIMAL -> castedArguments[index] = arguments[index].toDouble()
                        Type.STRING -> castedArguments[index] = arguments[index]
                    }
                }

                instObj.execute(this, castedArguments)
            } else {
                if(arguments.isNotEmpty()) {
                    throw InstructionArgumentException("Could not interpret Instruction ${instObj.getName().uppercase()} on line ${index + 1}. Expected argument types [] but was ${argumentTypes.toTypedArray().contentToString()}")
                }

                instObj.execute(this, arrayOf())
            }

            if(shouldColor) {
                canvasData[position.x][position.y] = currentColor
            }
        }
    }

}
