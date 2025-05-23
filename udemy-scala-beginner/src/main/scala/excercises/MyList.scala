package excercises

abstract class MyList[+A] {

  /*
  * methods:
  * head = first element ot the list
  * tail = remainder of the list
  * isEmpty = is this list empty
  * add(int) -> new list with this element added
  * toString => a string representation of the list
  * */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A] (element: B): MyList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = s"[$printElements]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A](list: MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipwith[B, C](list: MyList[B], zip:(A, B) => C): MyList[C]
  def fold[B] (start: B) (operator: (B, A) => B): B

}

case object Empty extends MyList[Nothing] {

  def head: Nothing = throw new NoSuchElementException  // ??? // expression that returns nothing
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipwith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists dont have the same length")
    else Empty
    
  def fold[B] (start: B) (operator: (B, Nothing) => B): B = start


}

case class Cons[+A] (h: A, t: MyList[A]) extends MyList[A] {

  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String = {
    if(t.isEmpty) "" + h
    else s"$h ${t.printElements}"
  }

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter((predicate)))
    else t.filter(predicate)

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  // hofs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {

    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) < 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipwith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists dont have the same length")
    else new Cons(zip(h, list.head), t.zipwith(list.tail, zip))

  def fold[B] (start: B) (operator: (B, A) => B): B =
    t.fold(operator(start, h)) (operator)
    
}

object ListTest extends App {

  val listOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneListOfInts: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val otherListOfInt: MyList[Int] = new Cons(4, new Cons(5,  Empty))
  val listOfStr: MyList[String] = new Cons("hiii", new Cons("scala", Empty))

  println(listOfInt.toString)
  println(listOfStr.toString)

  println(listOfInt.map(_ * 2).toString)

  println(listOfInt.filter(_ % 2 == 0).toString)

  println((listOfInt ++ otherListOfInt).toString)

  println(listOfInt.flatMap(elem => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(cloneListOfInts == listOfInt)

  // hofs
  listOfInt.foreach(println)
  println(listOfInt.sort((x, y) => y - x))
  println(otherListOfInt.zipwith[String, String] (listOfStr, _ + "-" + _ )) 
  println(listOfInt.fold(0)(_ + _))

}
