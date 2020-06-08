package lessons.part2oop

object AnonymousClasses extends App {


  abstract class Calculation {
    def add(num1: Int, num2: Int) : Int
  }

  trait Greet {
    def greet(name: String) : String
  }

  val calc = new Calculation {
    override def add(num1: Int, num2: Int): Int = num1 + num2
  }

  val greeter = new Greet {
    override def greet(name: String): String = s"Hello, Good Morning $name"
  }

  println(calc.add(1, 2))
  println(greeter.greet("Raja"))
}
