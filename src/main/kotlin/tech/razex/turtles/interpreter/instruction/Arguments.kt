package tech.razex.turtles.interpreter.instruction

import tech.razex.turtles.util.Type

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Arguments(val types: Array<Type>)
