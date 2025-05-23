package lectures.part2oop

object OOBasics extends App {

  val person = new Person("furcio", 20)
  /*println(person.age)
  println(person.x)*/
  // the value of x+3 will be printed first because at the instantiation the body of the class is evaluated and executed
  /*person.greet("donpe")
  person.greet()*/

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(s"Author age: ${novel.authorAge}")
  println(s"${novel.isWrittenBy(author)}")
  println(novel.isWrittenBy(imposter))

  val counter = new Counter()
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print


}

// class parameters are not fields, you must add the val keyword
class Person(name: String, val age: Int = 0) {    // Constructor

  val x = 2

  println(x + 3)

  def greet(name: String): Unit = println(s"${this.name} is saying: Hi $name")
  def greet(): Unit = println(s"hiii im $name")

  // multiple constructors
  def this(name: String) = this(name, 0) // is the same as adding default parameter at the base constructor
  def this() = this("furcio")

}

/*
Novel and writer
Writer: first name, surname, year
- method fullname

Novel: name, year of release, author
- authorAge (age at the year of release)
- isWrittenBy (author)
- copy (new year of release) = new instance of novel
 */
class Writer(firstName: String, surname: String, val year: Int) {
  def fullName: String = firstName + " " + surname
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge = year - author.year
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}
/*
Counter class
- receives an int value
- method current count
- method to increment/decrement => new Counter
- overload inc/dec to receive an amount
*/

class Counter(val count: Int = 0) {
  def inc = {
    println("incrementing...")
    new Counter(count + 1) // immutability
  }
  def dec = {
    println("decrementing...")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }
  def dec(n: Int): Counter =
    if (n <= 0) this
    else dec.dec(n - 1)

  def print = println(count)
}

