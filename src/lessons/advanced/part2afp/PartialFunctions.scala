package lessons.advanced.part2afp

import scala.io.Source
import scala.util.Try

object PartialFunctions extends App {

  val inc = (x: Int) => x + 1

  println(inc(5)) // 6

  val aFunction = (x: Int) => {
    if (x == 1) 24
    else if (x == 2) 35
    else if (x == 3) 54
    else throw new FunctionNotApplicableException
  }

  class FunctionNotApplicableException extends RuntimeException

  println(aFunction(1)) //24
  println(aFunction(2)) //35
  println(aFunction(3)) //54
  //println(aFunction(4)) // Exception in thread "main" lessons.advanced.part2afp.PartialFunctions$FunctionNotApplicableException

  val aFussyFunction = (x: Int) => x match {
    case 1 => 24
    case 2 => 35
    case 3 => 54
    case _ => throw new FunctionNotApplicableException
  }

  println(aFussyFunction(1)) //24
  println(aFussyFunction(2)) //35
  println(aFussyFunction(3)) //54
  //println(aFunction(4)) // Exception in thread "main" lessons.advanced.part2afp.PartialFunctions$FunctionNotApplicableException

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 24
    case 2 => 35
    case 3 => 54
  }

  println(aPartialFunction(1)) //24
  println(aPartialFunction(2)) //35
  println(aPartialFunction(3)) //54
  //println(aPartialFunction(4)) //Exception in thread "main" scala.MatchError: 4 (of class java.lang.Integer)

  // PARTIAL FUNCTIONS UTILITIES

  println(aPartialFunction.isDefinedAt(5)) // False

  // LIFTED PARTIAL FUNCTION => if it not matches it gives None
  private val lift: Int => Option[Int] = aPartialFunction.lift    // Int => Option[Int]

  println(lift(1)) // Some(24)
  println(lift(5)) // None

  // Chained Partial Function
  val pfChain = aPartialFunction.orElse[Int, Int]{
    case 5 => 65
  }

  println(pfChain(5)) //65

  // PF extends normal functions

  val normalFunction: Int => String = {
    case 1 => "I am one"
  }

  println(normalFunction(1)) // I am one
  //println(normalFunction(2)) // Exception in thread "main" scala.MatchError: 2 (of class java.lang.Integer)

  // HOFS accept Partial Functions as well

  List(1,2,3).map{
    case 1 => 23
    case 2 => 45
    case 3 => 56
  }.foreach(println) // 23 , 45, 56

  /*
      NOTE : Partial Functions Can have only one parameter type
   */




  // Construct a Partial Function yourself (anonymous class)
  val partialFunction = new PartialFunction[Int, Int] {
    override def isDefinedAt(x: Int): Boolean = Try(apply(x)).isSuccess

    override def apply(v1: Int): Int = v1 match {
      case 1 => 24
      case 2 => 43
      case 5 => 84
    }
  }

  println(partialFunction(1)) // 24
  println(partialFunction(2)) // 43
  println(partialFunction(5)) // 84
  println(partialFunction.isDefinedAt(5)) // true
  println(partialFunction.isDefinedAt(43)) // false
  //println(partialFunction(43)) // scala.MatchError

  // dumb chatbot as a Partial Function

 /* Source.stdin.getLines().foreach(line => line match {
    case "Hello" => println(s"You said: Hello \n Raja said : Hello, How are you ?")
    case "How are you" => println(s"You said: How are you \n Raja said : Good, What else ?")
    case _ => println("Raja went offline!")
  })*/

  // Convert match statement to pattern matching anonymous function
  /*Source.stdin.getLines().foreach {
    case "Hello" => println(s"You said: Hello \n Raja said : Hello, How are you ?")
    case "How are you" => println(s"You said: How are you \n Raja said : Good, What else ?")
    case _ => println("Raja went offline!")
  }*/

  /* Source.stdin.getLines().map{
   case "Hello" => s"Raja says : Hello, How are you ?"
   case "How are you" => s"Raja Says: Good, How about you ?"
   case "bye" | "exit" => System.exit(0)
   case _ => "INFO::::: Raja went offline!"
 }.foreach(println)*/


  // Define Partial Function as utility and use with foreach
  val chatbot : PartialFunction[String, Any] = {
    case "Hello" => s"Raja says : Hello, How are you ?"
    case "How are you" => s"Raja Says: Good, How about you ?"
    case "bye" | "exit" => System.exit(0)
    case _ => "INFO::::: Raja went offline!"
  }

  Source.stdin.getLines().map(chatbot).foreach(println)
}
