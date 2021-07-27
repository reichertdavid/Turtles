package tech.razex.turtles.interpreter

import tech.razex.turtles.interpreter.exceptions.InstructionNotFoundException
import tech.razex.turtles.interpreter.exceptions.InstructionUnexpectedArgumentException
import tech.razex.turtles.interpreter.instruction.impl.InstructionCanvas
import tech.razex.turtles.util.Type
import java.io.File

/**
 * This will read a text file and interpret several calls line by line
 */
class Interpreter(val file: File) {

    /**
     * This will contain a list of all instructions this class can understand and execute
     */
    val instructions = mutableListOf(
        InstructionCanvas()
    )

    /**
     * contains all pixel data of our canvas
     */
    lateinit var canvasData: Array<IntArray>

    /**
     * Start to interpret all instructions contained in source file
     */
    fun interpret() {
        val instructions = this.file.readLines()

        instructions.forEachIndexed { index, inst ->
            val interpretable = inst.split(" ")
            val instName = interpretable[0]
            val arguments = interpretable.drop(1)

            // get argument types to assert everything is at right place
            val argumentTypes = mutableListOf<Type>()
            arguments.forEach { s ->
                when {
                    s.matches("-?\\d+?".toRegex()) -> argumentTypes.add(Type.INT)
                    s.matches("-?\\d+(\\.\\d+)?".toRegex()) -> argumentTypes.add(Type.DECIMAL)
                    else -> argumentTypes.add(Type.STRING)
                }
            }

            val instObj = this.instructions.firstOrNull { i -> i.getName() == instName.lowercase() || i.getAliases().contains(instName.lowercase()) }
                ?: throw InstructionNotFoundException("Cannot determine instruction \"$instName\" on line ${index + 1}")

            if(instObj.getArgumentData() != null) {
                val expectedTypes = instObj.getArgumentData()!!.types

                if(!expectedTypes.contentEquals(argumentTypes.toTypedArray())) {
                    throw InstructionUnexpectedArgumentException("Could not interpret Instruction ${instObj.getName().uppercase()} on line ${index + 1}. Expected argument types ${expectedTypes.contentToString()} " +
                            "but was ${argumentTypes.toTypedArray().contentToString()}")
                }

                val castedArguments = Array<Any>(expectedTypes.size) {0}

                expectedTypes.forEachIndexed { index, type ->
                    when(type) {
                        Type.INT -> castedArguments[index] = arguments[index].toInt()
                        Type.DECIMAL -> castedArguments[index] = arguments[index].toDouble()
                        Type.STRING -> castedArguments[index] = arguments[index]
                    }
                }

                instObj.execute(this, castedArguments)
            } else {
                if(arguments.isNotEmpty()) {
                    throw InstructionUnexpectedArgumentException("Could not interpret Instruction ${instObj.getName().uppercase()} on line ${index + 1}. Expected argument types [] but was ${argumentTypes.toTypedArray().contentToString()}")
                }
            }
        }
    }

}
