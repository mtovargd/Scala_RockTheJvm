package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // Anonymous class
  val funnyAnimal: Animal = new Animal{
    override def eat: Unit = println("ahahahhahah")
  }

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def hi: Unit = println(s"hii my name is $name how can i help")
  }

  val jim = new Person("Jim") {
    override def hi: Unit = println(s"hii my name is Jim what do u want bud")
  }

}
