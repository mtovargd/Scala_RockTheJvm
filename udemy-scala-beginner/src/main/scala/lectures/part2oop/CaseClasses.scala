package lectures.part2oop

object CaseClasses extends App {

  case class Person(name: String, age: Int)

  // Class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // sensible toString
  // println instance = println instance.toString // syntactic sugar
  println(jim)

  // equals and hashCode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true

  // case classes have handy copy methods
  val jim3 = jim.copy(age = 45) // keep name and override age
  println(jim3)
  println(jim3 == jim2) // false

  // Case Classes have companion objects
  val thePerson = Person // companion object of the case class
  val mary = Person("Mary", 23)

  // Case Classes are serializable
  // Case Classes have extractor patterns = CCs can be use in PATTERN MATCHING

  // like a case class but is an object lol
  // difference: case objects cant get companion objects bc they are the companion objects
  case object UnitedKingdom {
    def name: String = "The United Kingdom of Great Britain and Northern Ireland"
  }




}
