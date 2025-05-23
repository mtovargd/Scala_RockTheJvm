package lectures.part4pm

object BracelessSyntax {

  // if - expressions
  val anIfExpression = if (2 > 3) "bigger" else "smaller"

  // java style
  val anIfExpressionV2 = {
    if (2 > 3) {
      "bigger"
    } else {
      "smaller"
    }
  }

  // compact
  val anIfExpressionV3 =
    if (2 > 3) "bigger"
    else "smaller"

  // scala 3
  val anIfExpressionV4 =
    if 2 > 3 then
      "bigger" // needs a higher indentation than the if instruction
    else
      "smaller"

  val anIfExpressionV5 =
    if 2 > 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpressionV6 = if 2 > 3 then "bigger" else "smaller"

  // for comprehensions
  val aForComprehension = for {
    n <- List(1,2,3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3 style
  val aForComprehensionV2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"
  }

  val aPatternMatchV2 = meaningOfLife match
    case 1 => "the one"
    case 2 => "double or nothing"
    case _ => "something else"

  // methods without braces
  def computeMeaningOfLife(arg: Int): Int =
    val partialResult = 40





    partialResult + 2

  // class definition with significant indentation (same for traits, objects, enums, etc)
  class Animal: // with : the compiler expects the boy of the class
    def eat(): Unit =
      println("im eating")
    end eat

    def grow(): Unit =
      println("im bigger")
    /* 3000 more lines of code
    *
    *
    *
    * */
  end Animal // just to specify where the class ended. also apply to for, if, match, for, methods, classes, traits, enums, objects

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("im special")

  // indentation = strictly larger indentation
  // 3 spaces + 2 tabs > 2 spaces + 2 tabs
  // 3 spaces + 2 tabs > 3 spaces + 1 tab
  // 3 tabs + 2 spaces ??? 2 tabs + 3 spaces

  def main(args: Array[String]): Unit = {

  }

}
