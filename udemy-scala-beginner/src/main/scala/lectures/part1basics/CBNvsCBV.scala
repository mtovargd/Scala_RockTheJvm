package lectures.part1basics

object CBNvsCBV extends App {

  def callByValue(x: Long): Unit = {
    println("by value " + x)
    println("by value " + x)
  }

  def callByName(x: => Long): Unit = {
    println("by name " + x)
    println("by name " + x)
  }

  //callByValue(System.nanoTime()) // evaluate the function before use the value
  //callByName(System.nanoTime()) // evaluate just when the value is used

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34) // error
  //printFirst(34, infinite()) // 34 because the y value is never used and is never evaluated


}
