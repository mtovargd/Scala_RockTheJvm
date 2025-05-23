package lectures.part3fp

object HigherOrderFuncsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // return type is another function which takes an int and returns an int: (Int => Int)
  // first parameter: Int. second is String, (Int => Boolean)) => Int

  // Higher Order Function (HOF)

  // function that applies a function n times over value x
  // nTimes(f, n, x) f is the function, n is the times, x is the subject of the applications of the function
  // nTime(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x))
  // nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // ntb(f,n) = x => f(f(f...(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOne(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1) (f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  //functions with multiple params lists
  def curriedFormatter(c: String) (x: Double): String = c.format(x)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val precisedFormat: (Double => String) = curriedFormatter("%10.8f")
  println(standardFormat(Math.PI))
  println(precisedFormat(Math.PI))

  /*
  MyList:
    1. foreach method A => Unit: example [1, 2, 3] foreach(x => println(x))
    2. sort method
    3. zipwith method
    4. fold method

    toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
    fromcurry(f: (Int => Int => Int)) => (Int, Int) => Int

    compose(f,g) => x => f(g(x))
    andThen(f,g) => x => g(f(x))
  */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x) (y)

  // FunctionX
  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, C](f: A => B, g: B => C): A => C =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)

  println(add4(17))

  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))


}
