package lectures.part2oop

import playground.{PrinceCharming, Princess} // or playground._ to import all

// if you need two classes from different packages that share the same name, you can give a nickname:
import java.util.Date
import java.sql.{Date => SQLDate} // SQLDate is a nickname

val jDate = Date(2000, 5, 4)
val sDate = SQLDate(2000, 5, 4)

object PackagingAndImports extends App {

  // package members are visible by their simple name
  val writer = new Writer("Daniel", "el profe", 2025)

  // if we need a member of another package, we need to import
  val cinderella = new Princess // playground.Princess imported
  // another way is using the full name
  val belle = new playground.Princess

  // packages are in hierarchy, denoted by .
  // matching folder structure

  // package object
  sayHello // visible from the package object to the rest of the package
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming // added to the import section
 
  // default imports:
  // java.lang => String, Object, Exception
  // scala => Int, Nothing, Function
  // scala.Predef => println, ???

}
