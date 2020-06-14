package lessons.part3fp

object AnonymousFunction extends  App {

  val doubler = new Function[Int, Int] {
    override def apply(v1: Int): Int = v1 * 2
  }  // Anonymous Function

  println(doubler(2))

  val niceDoubler = (v1: Int) => v1 * 2 // LAMBDA's

  println(niceDoubler(2))

  val superDoubler : Int => Int = _ * 2  // More Syntactive Suger

  println(superDoubler(2))

  // More than one parameter
  val adder = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(2, 3))

  val niceAdder = (v1: Int, v2: Int) => v1 + v2

  println(niceAdder(2, 3))

  val superAdder: (Int, Int) => Int = _ + _

  println(superAdder(2, 3))

  // no parameters

  val justPrint = () => println("I Don't have any parameters")
  println(justPrint)  // Prints definition  =>lessons.part3fp.AnonymousFunction$$$Lambda$22/0x00000001000c3840@43bd930a
  println(justPrint()) // I Don't have any parameters

  val superPrint: () =>  Unit = () => println("I am super Printer")
}