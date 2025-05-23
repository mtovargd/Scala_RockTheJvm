package lectures.part1basics

object ValuesVariablesTypes extends App {
  //val x: Int = 42
  val x = 42
  println(x)

  // x = 2
  // VALS ARE IMMUTABLE
  // TYPES OF VALS ARE OPTIONAL, COMPILER CAN INFER THEM
  val aString: String = "Hiiiii"; val anotherString = "Byeeee" // ; not needed, unless multiple actions in one line

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  val aLong: Long = 46130398434343L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 5 // side effects



}
