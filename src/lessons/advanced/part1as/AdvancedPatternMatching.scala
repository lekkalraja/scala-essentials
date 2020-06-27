package lessons.advanced.part1as

object AdvancedPatternMatching extends App {

  val list = List(1)

  val des = list match {
    case head :: Nil => s"List contains only $head"
    case _ => "Doesn't Contain Anything!!"
  }

  // CAN IMPLEMENT ABOVE ONE
  val desc = list match {
    case ::(head, Nil) => s"List contains only $head"
    case _ => "Doesn't Contain Anything!!"
  }

  println(des) // List contains only 1
  println(desc) // List contains only 1

  /*
      For some reason if we don't have case classes and still
      if you want to apply pattern matching on normal class
      then create singleton object (companion object) and
      implement unapply method
   */

  class Employee(val name: String, val age: Int)

  object Employee {
    def unapply(emp: Employee): Option[(String, Int)] = Some(emp.name, emp.age)

    def unapply(age: Int): Option[String] = if(age < 20)  Some("minor") else Some("major")
  }

  val employee = new Employee("Raja", 25)
  val employeeDesc = employee match {
    case Employee(name, age) => s"Employee Name $name and age $age"
  }
  println(employeeDesc) // Employee Name Raja and age 25

  val ageDesc = employee.age match {
    case Employee(desc) => s"Employee is $desc"
  }

  println(ageDesc) // Employee is major

  def evenOrSingle(number : Int) = number match {
    case num if num < 10 => s"$num is single Digit"
    case even if even % 2 == 0 => s"$even is Even"
    case _ => s"$number is neither Even Nor Single Digit"
  }

  println(evenOrSingleDigit(10)) // 10 is Even
  println(evenOrSingleDigit(2))  // 2 is single Digit
  println(evenOrSingleDigit(21)) // 21 is neither Even Nor Single Digit
  println(evenOrSingleDigit(11)) // 11 is neither Even Nor Single Digit

  /*
    ABOVE APPROACH (HAVING `IF` GUARDS) IS MORE CUMBERSOME TO OVERCOME THAT
    CREATE REUSABLE SINGLE TON CONDITIONS WITH `UNAPPLY` and USE THEM like below
   */


  object singleDigit1 {
    def unapply(arg: Int): Option[Boolean] = if (arg < 10) Some(true) else Some(false)
  }

  def numberDesc1(number: Int) = number match {
    case singleDigit1(status) => s"The $number is $status"
  }

  println(numberDesc1(3)) // The 3 is true
  println(numberDesc1(33)) //The 33 is false

  object singleDigit2 {
    def unapply(arg: Int): Boolean = arg < 10
  }

  object even {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }

  def numberDesc2(number: Int) = number match {
    case singleDigit2() => s"$number is Single DIGIT"
  }

  println(numberDesc2(3)) // 3 is Single DIGIT
  //println(numberDesc2(33)) // scala.MatchError: 33 => Becuase we directly returning true or false

  def evenOrSingleDigit(number: Int) = number match {
    case singleDigit2() => s"$number is single Digit"
    case even() => s"$number is Even"
    case _ => s"$number is neither Even Nor Single Digit"
  }

  println(evenOrSingleDigit(10)) // 10 is Even
  println(evenOrSingleDigit(2))  // 2 is single Digit
  println(evenOrSingleDigit(21)) // 21 is neither Even Nor Single Digit
  println(evenOrSingleDigit(11)) // 11 is neither Even Nor Single Digit

  // INFIX PATTERNS

  case class Or[A, B](a : A, b: B)

  val or = Or(2, "two")

  val intToString = or match {
    case Or(number, string) => s"$number is said to be $string"
  }

  println(intToString) // 2 is said to be two

  // ABOVE MATCHER CAN BE WRITTEN AS

  val intToString1 = or match {
    case number Or string => s"$number is said to be $string"
  }

  println(intToString1) // 2 is said to be two

  // DECOMPOSING SEQUENCES

  val numbers = List(1,2,3,4,5,6)

  val numbersList = numbers match {
    case List(1, _*) => "List with 1 or more elements start with 1"
  }

  println(numbersList) // List with 1 or more elements start with 1

  abstract class MySet[+A] {
    def head: A = ???
    def tail: MySet[A] = ???
  }

  case object Empty extends MySet[Nothing]
  case class Cons[+A](override val head: A, override  val tail: MySet[A]) extends MySet[A]

  object MySet {
    def unapplySeq[A](set: MySet[A]): Option[Seq[A]] =
      if (set == Empty) Some(Seq.empty)
      else unapplySeq(set.tail).map(set.head +: _)
  }

  val myset = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))

  val mysetDesc = myset match {
    case MySet(1, 2, _*) => "My Set starts with 1, 2, ...."
    case _ => "Set didn't start with 1, 2"
  }

  println(mysetDesc) //My Set starts with 1, 2, ....

  // CUSTOM RETURN TYPES FOR UNAPPLY
  // implement isEmpty: Boolean, get: something

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get : T
  }

  object EmployeeWrapper {
    def unapply(arg: Employee): Wrapper[String] = new Wrapper[String] {
      override def isEmpty: Boolean = false
      override def get: String = arg.name
    }
  }

  val empName = employee match {
    case EmployeeWrapper(name) => s"Employee name is $name"
  }

  println(empName) // Employee name is Raja
}