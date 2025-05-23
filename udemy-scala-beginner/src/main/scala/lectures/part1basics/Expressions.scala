package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // EXPRESSION
  println(x)

  println(2 + 3 * 4) // MATH EXPRESSION
  // OPERATORS: + - * / & | ^ % << >> >>> (right shift with zero extension)

  println(1 == x) // RELATIONAL EXPRESSION
  // == != > >= < <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // works with -= *= /= ONLY WORKS WITH VARIABLES (side effects)
  println(aVariable)

  // Instructions vs Expressions
  // Instruction is a DO to the computer (hey do something)
  // Expression is something that has a value and or a type (hey evaluate something)

  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) "true" else 3 // IF in Scala is an EXPRESSION, not an instruction
  println(aConditionedValue)

  var i = 0
  while (i < 10){
    println(i)
    i += 1
  }
  // NEVER DO THIS AGAIN.

  // EVERYTHING IN SCALA IS AN EXPRESSION

  val aWeirdValue = ({ aVariable = 3 }) // In the video this doesn't have braces, but this version needs the braces
  // The type of aWeirdValue is unit, is equivalent to void. So the value printed is a void ()
  // Reassign a variable is a side effect. Side effects are expressions that return unit value
  // SIDE EFFECTS: println(), while, reassigns
  println(aWeirdValue)

  // Code blocks
  // The value of a code block is the value of its last expression

  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hiii" else "byee" // z just exists inside the code block, isn't visible outside here
  }
  println(aCodeBlock) // value is "hiii"

  // EXERCISESSSSSS
  // what's the diff between "hi" and println("hi")?
  // Answer: "hi" is a string, println("hi") is an expression side effect, so is a unit type

  val someValue = { // someValue is a Boolean which value is true
    2 < 3
  }
  println(someValue)
  
  val someOtherValue = { // someOtherValue is an Int, its value is 42 because that's its last expression, the if is irrelevant
    if (someValue) 239 else 986
    42
  }
  println(someOtherValue)

}
