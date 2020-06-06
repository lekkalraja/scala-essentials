package lessons.part1basics

import scala.annotation.tailrec

object Recursion extends  App {

  // FACTORIAL RECURSION
  def factorialRecursion(n: BigInt) : BigInt =
    if (n == 1) 1
    else n * factorialRecursion(n -1)

  println(factorialRecursion(10))
  //println(factorialRecursion(5000))  //java.lang.StackOverflowError  => To Overcome this use _TAL_RECURSION_

  // FACTORIAL _TAIL_RECURSION
  def factorialTailRecursion(n: BigInt) : BigInt = {
    @tailrec
    def factHelper(t: BigInt, accumulator: BigInt): BigInt =
      if (t == 1) accumulator
      else factHelper(t - 1, t * accumulator)

    factHelper(n, 1)
  }
  println(factorialTailRecursion(500))

  // String Concatenation RECURSION
  def stringConcatRecursion(item: String, times: BigInt) : String =
    if (times == 1) item
    else item + " " + stringConcatRecursion(item, times - 1)

  println(stringConcatRecursion("Hello", 353))
 // println(stringConcatRecursion("Hello", 353453))  //java.lang.StackOverflowError  ==> To Resolve this _TAIL_RECURSION

  // String Concatenation _TAIL_RECURSION
  def stringConcatTailRecursion(item: String, times: BigInt) : String = {

    @tailrec
    def stringConcatHelper(n: BigInt, accumulator: String) : String =
      if (n == 1) accumulator
      else stringConcatHelper((n - 1), item + " " + accumulator )

    stringConcatHelper(times, "")
  }

  println(stringConcatTailRecursion("Hello", 3000))

  // PRIME RECURSION
  def isPrimeTailRecursion(n : Long) : Boolean = {

    @tailrec
    def primeHelper(t: Long, isStillPrime: Boolean) : Boolean =
      if(!isStillPrime) false
      else if (t == 1) true
      else primeHelper(t - 1, n % t != 0)

    primeHelper(n / 2, true)
  }

  println(isPrimeTailRecursion(23242342424L))
  println(isPrimeTailRecursion(629L))
  println(isPrimeTailRecursion(2003L))

  def fibonacciRecursive(n : Int) : Int =
    if (n <= 2) 1
    else fibonacciRecursive(n - 2) + fibonacciRecursive(n - 1)

  println(fibonacciRecursive(5))
  println(fibonacciRecursive(8))

  def fibonacciTailRecursive(n: Int) : Int = {

    @tailrec
    def fibonacciHelper(t: Int, last: Int, nextToLast: Int) : Int =
      if (t == n) last
      else fibonacciHelper(t+1, last+nextToLast, last)

    fibonacciHelper(1, 1, 0)
  }

  println(fibonacciTailRecursive(5))
  println(fibonacciTailRecursive(8))

}