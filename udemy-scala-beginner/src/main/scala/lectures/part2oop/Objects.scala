package lectures.part2oop

object Objects {

  object Person { // objects dont receive parameters
    val N_EYES = 2
    def canFly: Boolean = false
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  // "static"/"class" - level functionality

  class Person(name: String) {
    // instance level functionality
  }

  def main(args: Array[String]): Unit = {

    // class and object are COMPANIONS

    println(Person.N_EYES)
    println(Person.canFly)

    val mary = new Person("mary") // instance of the Person type
    val john = new Person("john")
    println(mary == john) // false because they point to the different instance

    // Scala object = SINGLETON INSTANCE
    val person1 = Person
    val person2 = Person
    println(person1 == person2) // true

    val bobbie = Person(mary, john)

    // Scala Applications = a Scala object with a def main that receives an array of strings
    // def main(args: Array[String]): Unit
  }







}
