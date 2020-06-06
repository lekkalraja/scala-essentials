package lessons.part1basics

object CallByNameVsCallByValue extends App {

  def callByValue(x: Long) = {  // x = 36271852599919
    println(s"Value of X is : $x")  //36271852599919
    println(s"Value of X is : $x")  //36271852599919
  }

  def callByName(x: => Long) = {     // x = System.nanoTime()
    println(s"Value of X is : $x")  //36272032180026  // executes System.nanoTime()
    println(s"Value of X is : $x")  //36272032323234  // executes System.nanoTime()
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34)  //java.lang.StackOverflowError
  printFirst(34, infinite())

}

/*
 TAKEWAY:

 Call  By Value :
     1. Value is computed before call
     2. same value used everywhere

 Call By Name :
     1. Expression is passed literally
     2. Expression is evaluated at every use within
 */