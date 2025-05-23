package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tuples = finite order "lists"
  val aTuple = new Tuple2(2, "hiiiii") // syntax sugar for Tuple2[Int, String] = (Int, String)
  // simpliet way:
  val aSimplierTuple = (2, "hiiiii")
  // at most 22 elements of different types

  println(aTuple._1) // _1 is first element = 2
  println(aTuple.copy(_2 = "byeeee"))
  println(aTuple.swap) // "hiii", 2

  // Maps - keys -> values
  // used to associate things with other things (dictionary)
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555), "Daniel" -> 7809).withDefaultValue(-1) // method to set a default and avoid errors
  // a -> b is sugar for tuple (a,b)
  println(phoneBook)
  println(phoneBook.contains("Jim")) // boolean if contains
  println(phoneBook("Jim")) // returns the value of that key
  println(phoneBook("mary")) // invalid key

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phoneBook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatmap, filter
  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2)) // transforms key to lowercase

  // filterKeys
  println(phoneBook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(phoneBook.view.mapValues(number => "+52 33" + number).toMap)

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Jim")
  println(names.groupBy(name => name.charAt(0))) // creates a map that groups names by their first char
  /*
    1. Check what if i have "Jim" -> 555 and "JIM" -> 900    // properly added but overlap at the lowercase and lose information
    2. Simplified social network
      - add person to the network
      - remove
      - friend (mutual)
      - unfriend (mutual)

      - number of friends of a person
      - person with most friends
      - how many people have NO friends
      - if there is a social connection between two people (direct or not)
  */

  def add(network: Map[String, Set[String]], person: String): Map[ String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove( network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network,"Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testnet = friend(jimBob, "Bob", "Mary")

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testnet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testnet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    // network.view.filterKeys(k => network(k).isEmpty).size this function equals:
    // network.count(pair => pair._2.isEmpty) and this function equals:
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testnet))

  def socialNetwork(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if(discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialNetwork(testnet, "Mary", "Jim"))





}


