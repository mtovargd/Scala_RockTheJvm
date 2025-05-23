package lectures.part2oop

object Generics extends App {

  class MyList[+A] { // generic class
    // use the type A

  }

  val listOfInt = new MyList[Int]
  val listOfStr = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfInt =  MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  // 1. yes, List[Cat] extends List[Animal]. This is called covariance
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add.Dog ??? HARD QUESTION

  // 2. NO = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  //3. Hell, NO! CONTRAVARIANCE
  //class ContravariantList[-A]
  //val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  class Trainer[-A]
  val contravariantList: Trainer[Cat] = new Trainer[Animal]

  //bounded types
  class Cage[A <: Animal] (animal: A)  // only accepts any parameter that are subtypes of Animal
  val cage = new Cage(new Dog)

  // expand MyList to be generic

}
