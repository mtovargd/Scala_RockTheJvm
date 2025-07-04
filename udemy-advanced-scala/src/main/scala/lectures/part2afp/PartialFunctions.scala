package lectures.part2afp


object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // Function1[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  }
  //  {1,2,5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {  // proper function
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(2)) // this calls the apply method from partial function
  //  println(aPartialFunction(57273))

  // PF utilities
  println(aPartialFunction.isDefinedAt(67)) // test to validate if the partial function can run with that parameter
  // returns false

  // lift
  val lifted = aPartialFunction.lift // transform the function to: Int => Option[Int]
  println(lifted(2)) // print 56
  println(lifted(98)) // return None

  val pfChain = aPartialFunction.orElse[Int, Int] { //
    case 45 => 67 // this is "added" to the arguments to the original PF
  }

  println(pfChain(2))
  println(pfChain(45))

  // Partial Functions extend normal functions

  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept partial functions as well
  val aMappedList = List(1,2,3).map { // the (1,2,3) should match with the cases, if not it will throw an error
    case 1 => 42
    case 2 => 78
    case 3 => 1000
  }
  println(aMappedList)

  /*
    Note: PF can only have ONE parameter type
   */

  /**
   * Exercises
   *
   * 1 - construct a PF instance yourself (anonymous class)
   * 2 - dumb chatbot as a PF
   */

  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(x: Int): Int = x match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }

    override def isDefinedAt(x: Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  val chatbot: PartialFunction[String, String] = {
    case "hello" => "Hi, my name is HAL9000"
    case "goodbye" => "Once you start talking to me, there is no return, human!"
    case "call mom" => "Unable to find your phone without your credit card"
  }

  scala.io.Source.stdin.getLines().map(chatbot).foreach(println)

  // TODO: Maybe fix the crash of the bot

}