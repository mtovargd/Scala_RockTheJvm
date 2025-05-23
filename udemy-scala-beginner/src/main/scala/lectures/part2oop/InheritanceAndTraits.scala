package lectures.part2oop

object InheritanceAndTraits extends App {

  class Animal {
    def eat: Unit = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch



}
