package lessons.part1basics

object Functions extends  App {

  def aFunction(a: Int, b: Int): Int = a + b
  println(aFunction(2,3))

  def aFunctionWithoutReturnType(a: Int, b: Int) = a + b  // Compiller can infer return type of function as well
  println(aFunctionWithoutReturnType(2,3))

  def aFunctionWithoutParamerters() : String = "I am Parameter Less"
  println(aFunctionWithoutParamerters())
  println(aFunctionWithoutParamerters)  // can call without parenthesis


  //Should specify return type for Recursive Functions
  def aRepeatedFunction(aString: String, times: Int) : String = {
    if (times == 1) aString
    else
      aString + " " + aRepeatedFunction(aString, times - 1)
  }

  println(aRepeatedFunction("Raja", 4))

  // WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEFFECT() : Unit = println("I am useless")


  def aBigFunction(n: Int) : Int = {
    def aSmallFunction(a: Int, b : Int): Int = a + b  //Auxiliary Function

    aSmallFunction(n, n-1)
  }

  println(aBigFunction(10))

  def greet(name: String, age: Int) = s"I am $name and $age years old"

  println(greet("Raja", 26))

  def factorial(n: Int) : Int = {
    if (n == 1) 1
    else n * factorial(n-1)
  }
  println(factorial(5))

  def fibnocacci(n : Int) : Int = {
    if (n <= 2) 1
    else fibnocacci(n - 1) + fibnocacci(n - 2)
  }
  println(fibnocacci(6))

  def isPrime(n: Int) : Boolean = {
    def isPrimeUntil(t: Int) : Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t -1)
    }
    isPrimeUntil(n / 2)
  }

  println(isPrime(4))
  println(isPrime(41))
  println(isPrime(37))
}
