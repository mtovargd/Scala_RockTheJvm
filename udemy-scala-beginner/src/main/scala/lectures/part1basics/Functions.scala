package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hi", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }
  println(aRepeatedFunction("hi", 3))
  // WE USE RECURSION INSTEAD OF LOOPS

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
  println(aFunctionWithSideEffects("hiiii"))

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n+1)
  }
  println(aBigFunction(3))

  def greeting(name: String, age: Int) = "Hi my name is " + name + " and I'm " + age + " years old"
  println(greeting("mike", 20))

  def factorialNumber(n : Int): Int = {
    if (n <= 0) 1
    else (factorialNumber(n-1) * n)
  }
  println(factorialNumber(5))

  def fibonacciNumber(n: Int): Int = {
    if (n <= 2) 1
    else fibonacciNumber(n-1) + fibonacciNumber(n-2)
  }
  println(fibonacciNumber(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime(2003*6))



}
