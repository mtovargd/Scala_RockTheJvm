package lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favMovie
    //def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"$name ($nickname)", favMovie)
    def unary_! : String = s"$name, wth?!!"
    def unary_+ : Person = new Person(name, favMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"hiii my name is $name and i like $favMovie"
    def apply(n: Int): String = s"$name watched $favMovie $n times"

    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // natural language
  // infix notation = operator notation (syntactic sugar)

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  //println(mary hangOutWith tom)
  println(mary + tom)
  //println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))
  // ALL OPERATORS ARE METHODS

  // prefix notation -> all about unary operators
  val x = -1  // equivalent to 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  //postfix notation -> functions that dont receive parameters, can be used in a postfix
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  /*
  1. Overload the + operator to receive a string an return a person with a nickname
    example: mary + "the rockstar" +> new Person "Mary (the rockstar)"
  2. Add an age to the Person class default 0
    Add unary + operator that increments the age of the person
    example: +mary +> mary with age + 1
  3. Add a "learns" method that receives a string and returns a sentence
    example: learns("scala") => "Mary learns scala"
    Add learnsScala method, calls learns method with "scala" as parameter
    Use it with postfix notation
  4. Overload apply to receive a number and returns a string
    mary.apply(2) => "Mary watched Inception 2 times"
  */

  println((mary + "the huracan") ())
  println((+mary).age)
  println(mary learnsScala)
  println( mary learns "spanish")
  println(mary(10))
  /* Outputs:
    - hiii my name is Mary (the huracan) and i like Inception
    - 1
    - Mary is learning Scala
    - Mary is learning spanish
    - Mary watched Inception 10 times
  */

}
