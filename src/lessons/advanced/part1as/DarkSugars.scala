package lessons.advanced.part1as

import scala.util.Try

object DarkSugars extends App {

  // SYNTAX SUGAR #1 : METHODS WITH SINGLE PARAM
  def singleArg(name: String) = s"My Name is $name"

  println(singleArg("Raja")) // My Name is Raja
  println(singleArg{
    val name = "Raja"
    //WRITE COMPLEX CODE HERE
    s"$name Lekkala"
  }) // My Name is Raja Lekkala

  val aTryInstance = Try {  // LIKE JAVA's TRY  ==> def apply[T](r: => T): Try[T] (Try's Signature)
    // LOT OF RISKY CODE
    throw new RuntimeException
  }

  println(aTryInstance)  // Failure(java.lang.RuntimeException)

  val list = List(1,2,3,4)
  println(list.map(x => x + 1)) // List(2, 3, 4, 5)

  println(list.map{ x => {
      val item = x + 1
      //CODE HERE
      item*4
    }
  }) // List(8, 12, 16, 20)

  // SYNTAX SUGAR #2 : SINGLE ABSTRACT METHOD  (LAMBDA's)

  trait Action {
    def act(x : Int): Int
  }

  val actionImpl: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  println(actionImpl.act(4))  // 5

  val actionImpl1: Action = (x : Int) => x + 1

  println(actionImpl1.act(4)) // 5

  abstract class AnAbstractType {
    def implemented: Int = 123
    def f(a : Int) : Unit
  }

  val anAbstractInstance: AnAbstractType = (a: Int) => println(s"Value is $a")
  println(anAbstractInstance.implemented)  // 123
  anAbstractInstance.f(432) // Value is 432

  // SYNTAX SUGAR #3: THE :: and #:: methods are special
  // (If the last character of method is ':' then it will take right associativity)

  val prependedList = 2 :: List(3,4) // List(3,4).::(2) => It will take Right Associativity (Evaluates from RIGHT)
  println(prependedList) // List(2, 3, 4)
  println(prependedList.::(1)) // List(1, 2, 3, 4)

  println(1 :: 2 :: 3 :: 4 :: List(5)) // List(1, 2, 3, 4, 5)
  List(5).::(4).::(3).::(2).::(1) // List(1, 2, 3, 4, 5) ==> EQUIVALENT WITH ABOVE

  class MyStream[T] {
    def -->: (x: T) : MyStream[T] = this // ACTUAL IMPLEMENTATION HERE
  }

  val newStream = 1 -->: 2 -->: 4 -->: 3 -->: new MyStream[Int]

  // SYNTAX SUGAR #4 : MULTI-WORD METHOD NAMING

  class TeenBoy(name: String) {
    def `and then said` (gossip : String) = s"$name said $gossip"
  }
  val raja = new TeenBoy("Raja")
  println(raja `and then said` "He is good boy!!") // Raja said He is good boy!!

  // SYNTAX SUGAR #5: INFIX TYPES

  class Composite[A, B]
  val aComposite: Int Composite Float = new Composite

  class -->[A, B]
  val intToString: Int --> String = new -->

  //SYNTAX SUGAR #6: update() is very special, much like apply()
  val anArray = Array(1,2,3,4)
  anArray(2) = 7 // rewritten as anArray.update(2, 7) ==> Used in mutable collection

  //SYNTAX SUGAR #7 : SETTERS FOR MUTABLE CONTAINERS
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation

    def member: Int = internalMember // GETTER
    def member_=(value: Int): Unit = internalMember = value // SETTER
  }

  val mutableContainer = new Mutable
  println(mutableContainer.member) // 0
  mutableContainer.member = 49 // REWRITTEN AS mutableContainer.member_=(49)
  println(mutableContainer.member) // 49


}
