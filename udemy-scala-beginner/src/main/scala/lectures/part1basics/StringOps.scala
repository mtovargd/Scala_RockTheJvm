package lectures.part1basics

object StringOps extends App {

  val str: String = "hiii im lrnin scala"
  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("hi"))
  println(str.replace(" ", "-"))
  println(str.toUpperCase())
  println(str.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // Scala specifics: String interpolators

  // S-interpolators

  val name = "David"
  val age = 12
  val greeting = s"hiii, my name is $name and im $age years old"
  val greeting2 = s"hiii, my name is $name and i will be turning ${age + 1} years old"

  println(greeting)
  println(greeting2)

  // F-interpolators for float formatting

  val speed =  1.2
  val myth =  f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // Raw-interpolator

  println(raw"this is a \n new line")
  val escaped = "this is a \n new line"
  println(raw"$escaped") // injected values get the escaped



}
