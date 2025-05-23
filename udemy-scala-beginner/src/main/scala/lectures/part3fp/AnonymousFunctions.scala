package lectures.part3fp

object AnonymousFunctions extends App {

  /*val doubler = new Function1[Int, Int] { // oriented object way of defining an anonymous function and instantiating on the spot
    override def apply(x: Int) = x * 2
  }*/
  // anonymous function (LAMBDA)
  // val doubler: Int => Int = x => x * 2
  val doubler = (x: Int) => x * 2 // functional programming way
  println(doubler(8))

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  println(adder(7, 2))

  // no params
  val justDoSomething: () => Int = () => 3
  println(justDoSomething) // function itself
  println(justDoSomething()) // actual call

  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }
  println(stringToInt("87"))

  // moar syntactic sugar
  val niceIncermenter: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  // "special" adder as an anonymous function
  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))





}
