package tech.razex.turtles.interpreter.instruction

import tech.razex.turtles.interpreter.Interpreter

interface IInstruction {

    fun execute(interpreter: Interpreter, args: Array<Any>)

    fun getName() = this.javaClass.getAnnotation(Instruction::class.java).name
    fun getAliases() = this.javaClass.getAnnotation(Instruction::class.java).aliases

    fun getArgumentData() =
        if(this.javaClass.isAnnotationPresent(Arguments::class.java))
            this.javaClass.getAnnotation(Arguments::class.java)
        else
            null
}
