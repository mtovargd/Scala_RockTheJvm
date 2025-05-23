package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match { // like switch case but more powerful
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ = WILDCARD
  }

  println(x)
  println(description)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"hii my name is $n and i cant drink in the US" // extract and use the parameters
    case Person(n, a) => s"hii my name is $n and im $a years old" // extract and use the parameters
    case _ => "idk who th i am"
  }
  println(greeting)

  /*
    1. cases are matched in order
    2. what if no cases match? MatchError
    3. what is the type? Any if there are diff types or type if there is only one type
    4. PM works really well with case classes
  */

  // Pattern Matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("chihuahua")
  animal match {
    case Dog(someBreed) => println(s"matched a dog of the $someBreed breed")
  }

  // match everything
  val isEven =  x match {
    case n if n % 2 == 0 => true
    case _ => false
  }

  val isEvenCond = if (x % 2 ==0) true else false // ?!!
  val isEvenNormal = x % 2 == 0 // this returns the condition needed

  /*
    Exercise
    function that takes math expressions and show it as human language
    Sum(Number(3), Number(2)) => 2 + 3
    Sum(Number(3), Number(2), Number(4)) => 2 + 3 + 4
    Prod(Sum(Number(2), Number(1)), Number(3)) => (2 + 1) * 3
    Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
   */

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match { // recursive functions and pattern matches
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def maybeShowParentheses(exp: Expr) = exp match{
        case Prod(_ ,_) => show(exp)
        case Number(_) => show(exp)
        case _ => s"(${show(exp)})"
      }
      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(show(Sum(Number(3), Number(2))))
  println(show(Sum(Sum(Number(3), Number(2)), Number(4))))
  println(show(Prod(Sum(Number(2), Number(1)), Number(3))))
  println(show(Sum(Prod(Number(2), Number(1)), Number(3))))

}
