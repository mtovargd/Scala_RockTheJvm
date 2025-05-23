package lectures.part3fp

import scala.util.Random

object Options extends App { // options is a data structure that handles null pointer

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)
  println(noOption)

  // how to work with unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(null) // WRONG because you may get null
  val result = Option(unsafeMethod()) // you get a some or none
  println(result)

  // chained methods
  def backupMethod(): String = "valid"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe API
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("valid")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DONT USE

  // map, flatmap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10)) // returns None if is false and the value if is true
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions
  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connected to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }
  /*
    Exercise
    Try to establish a connection, if so - print the connect method
  */
  println("CONNECTIONS")
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)

    return null
  */
  // IMPORTANT COMPARISON
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h, p))) // this is equivalent to the function above
  /*
    if (c != null)
      return c.connect

    return null
  */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print (Some(connectionStatus.get))

  println(connectionStatus)
  /*
    if (status != null)
      println(status)
  */
  connectionStatus.foreach(println)

  // ANOTHER SOLUTION (chained calls solution)
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // ANOTHER SOLUTION (for-comprehensions)
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)




}
