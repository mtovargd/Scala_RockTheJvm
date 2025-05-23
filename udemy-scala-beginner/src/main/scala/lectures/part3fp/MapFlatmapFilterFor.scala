package lectures.part3fp

import scala.jdk.Accumulator

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  val numbers = List(1, 2, 3, 4)
  val chars = List('a','b','c','d')
  val colors = List("black", "white")

  // ITERATIONS

  // List ("a1","a2","a3",...,"d4")
  // two loops use flatmap and map
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinations)

  // three loops use flatmap, flatmap and map
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
  println(combinations2)

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    //n <- numbers // normal
    n <- numbers if n % 2 == 0 // adding a condition to keep only even numbers
    // that filter is equivalent to:
    // val combinations2 = numbers.filter(_ % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + "-" + color)))
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color // more readable

  println(forCombinations)

  for {
    n <- numbers
  } println(n) // equivalent to foreach number println

  // syntax overload
  list.map {
    x => x * 2
  }

  /*
  
  */





}
