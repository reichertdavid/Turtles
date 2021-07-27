package tech.razex.turtles

import tech.razex.turtles.interpreter.Interpreter
import java.io.File
import java.util.*

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val interpreter = Interpreter(File("source.turtles"))
        interpreter.interpret()
    }

}
