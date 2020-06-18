package lessons.part3fp

import scala.annotation.tailrec
import scala.util.Random

object PatternMatchings extends App {

  @tailrec
  def infinite: Unit = {
    val random = new Random().nextInt(10)

    val description = random match {
      case 1 => "This is No 1"
      case 2 => "This is NO 2"
      case 3 => "This is NO 3"
      case 4 => "This is NO 4"
      case 5 => "This is NO 5"
      case _ => "This is above 5"  // _  ==> WILDCARD
    }
    println(random, description)
    infinite
  }

  //infinite

  // decompose values

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 20)

  bob match {
    case Person(n, x) => println(s"I am $n, $x years old")
    case _ => "No MORE!!!"
  }

  val jammy = Person("Jammy", 25)

  // PATTERN MATCHING WITH GUARD (IF CONDITION)
  jammy match {
    case Person(n, x) if x < 20 => println(s"I am $n, $x years old so can't drink Alcohol")
    case Person(n, x) if x > 20 => println(s"I am $n, $x years old so can drink Alcohol")
    case _ => "No YOU ARE NOT!!!"
  }

  /*
     1. cases are matched in order
     2. what if no case match ? throws scala.MatchError
     3. type of Pattern matching expression? Unified type of all the types in all the cases
     4. Pattern Matching works really well with case classes
   */

  //pattern matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(breed: String) extends Animal

  val animal = Dog("WILD")

  animal match {
    case Dog(somebreed) => println(s"This dog belongs to $somebreed")
    case _ => "No Animal LIST"
  }

  val x = 10
  // match
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  }
  val isEvenNumber = if (x % 2 == 0) true else false // why
  val isEvenNbr = x % 2 == 0

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  val sum = Sum(Sum(Number(3), Number(4)), Number(5))

  def show(e: Expr) : String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"(${show(e1)} + ${show(e2)})"
    case Prod(e1, e2) => s"(${show(e1)} * ${show(e2)})"
    case _ => "NO PATTERNS MATCHING"
  }
  println(show(Prod(Sum(Number(3), Number(4)), Number(6))))
}
