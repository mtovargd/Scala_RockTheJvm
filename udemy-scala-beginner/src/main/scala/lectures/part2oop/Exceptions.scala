package lectures.part2oop

object Exceptions extends App {

  val x: String = null
  //println(x.length)

  // throwing and catching exceptions

  //val aWeirdValue: String = throw new NullPointerException // crash intentionally

  // throwable classes extends the Throwable class
  // Exception and Error are the major Throwable subtypes
  // Exception denotes something that went wrong with the program (NPE),
  // Error is something wrong with the System (StackOverflow)

  // How to CATCH exceptions
  def  getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you man")
    else 42

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43 // println("caught a Runtime Exception") // if dont match with the exception, it will be not catch
  } finally { // finally is optional. dont  influence the return. only for side effects
    // code that will get executed no matter what
    println("Finally")
  }

  println(potentialFail)

  // how to define your own exceptions
  class MyException extends Exception
  val exception = new Exception

  //throw exception

  // OverflowException
  // val array = Array.ofDim(Int.MaxValue)

  // StackOverflow
  // def infinite: Int = 1 + infinite
  // val limitless = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def substract(x: Int, y: Int) = {
      val result = x - y

      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

  }

    //println(PocketCalculator.add(Int.MaxValue, 10))
    println(PocketCalculator.divide(2, 0))


}
