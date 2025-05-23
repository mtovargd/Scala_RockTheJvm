package lectures.part1basics

object DefaultArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = // acc: Int = 1 means that default value is 1 if that parameter is not provided
    if (n <= 1) acc
    else trFact(n-1, n*acc)

  val fact10 = trFact(10) // if I pass another parameter, the function will take that parameter, not the default

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving")
  // savePicture(800)
  savePicture(width = 800)  // it works with every order of the parameters when you use the naming


}
