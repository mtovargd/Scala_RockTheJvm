package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // sequences:
  // have a defined order
  // can be indexed
  // Operators: apply, iterator, length, reverse for indexing and iterating,
  // concatenation, appending, prepending, grouping, sorting, zipping, searching, slicing

  val aSequence = Seq(2,1,4,3)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // apply returns the value at that particular index
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 until 10 // 1 to 10 is 10 inclusive, until is exclusive
  aRange.foreach(println)

  (1 to 10).foreach(x => println("hiii"))

  // List
  // head, tail, isEmpty methods are fast O(1)
  // most operations are O(n): length, reverse

  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 83 // :: also works the operator +: same way :+ is for appending
  println(prepended)

  val apples5 = List.fill(5) ("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // Arrays
  // can be manually predefine length
  // can be mutated
  // fast indexing
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3) // Int default initialization value is 0. if is String, is null
  println(threeElements)
  threeElements.foreach(println)
  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and sequences
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // Vectors
  // fast element addition (append/prepend)
  // good performance at large sizes
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs list
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getRightTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // lists saves the reference to the tail
  // updating an element in the middle takes more time
  println(getRightTime(numbersList))
  // depth of the tree is small
  // it needs to replace an entire 32-element chunk
  println(getRightTime(numbersVector))



}
