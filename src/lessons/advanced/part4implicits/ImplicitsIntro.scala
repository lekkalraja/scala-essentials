package lessons.advanced.part4implicits

object ImplicitsIntro extends App {

  case class Person(name: String) {
    def greet = s"Hi, My name is $name"
  }

  /*class A {
    def greet =10
  }*/

  println(Person("Raja").greet)

  implicit def fromStringToPerson(name: String) : Person = Person(name)
  //implicit def fromStringToA(name: String) : A = new A // ==> CONFLICT

  println("Raja".greet) // greet is not Method of String it's DUE TO IMPLICIT

  def add(x: Int)(implicit amount: Int) : Int = x + amount

  println(add(10)(20)) // 30

  implicit val defaultAmount : Int = 50

  println(add(30)) // 30 + 50 (Implicit value) ==> Implicit is not same as Default

}