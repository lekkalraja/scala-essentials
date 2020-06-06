package lessons.part1basics


// In Scala Everything is Expressions....
object Expressions extends  App {

  val x = 1 + 3  // +, -, *, /, %, &, |,  <<, >>, >>> (right shift with zero extension)
  println( x )

  var y = 1 + 2 * 3
  y += 1 // +=, -=, *=, /=,...  // side effects (modifying or changing the value)
  println( y )

  val con = 1 == x // ==, !=, <=, >=, <, >, &&, ||,
  println(con)

  // Instructions (DO)  VS Expressions (Evaluates Value along with the type)

  // IF Expression
  val aCondition = true
  val anotherCondition = if (aCondition) 3 else 5  // IF EXPRESSION Evaluates to 3 with Int type
  println(anotherCondition)


  // NEVER WRITE THIS AGAIN (INSTRUCTIONS)
  var i = 0
  while(i < 10) {
    println(i)
    i += 1
  }

  var aWeirdValue: Unit = i = 10   // evaluates to () value of Type Unit (Similar as Void in Java)
  println(aWeirdValue) // Prints ()
  var aWeirdWhile: Unit = while(i < 10) {
    println(i)
    i += 1
  }
  println(aWeirdWhile) // prints () of type Unit

  //SIDE EFFECTS println(), whiles, reassigning... everything yields () of Unit Type

  //Code Blocks

  val aBlockValue = {
    val a = 10
    val b = 20
    if (a < b) "small" else "big"
  }

  println(aBlockValue)  // Prints "small" -> Code block evaluates and returns last line of execution value

  // The value of the block is the value of its last expression

}
