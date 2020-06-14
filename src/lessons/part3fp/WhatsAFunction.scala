package lessons.part3fp

object WhatsAFunction extends App{

  // DREAM : Use functions as first class elements
  // PROBLEM :: OOP Thinking

  val doubler : Int => Int = _ * 2

  println(doubler(3))  // 6

  //function types => Function1.....Function22

  val stringToIntConverter: String => Int = _.toInt

  println(stringToIntConverter("3") + 3) // 6

  val adder:  (Int, Int) => Int = _ + _

  println(adder(10,20)) // 30


  val concatenate: (String, String) => String = _ + _

  println(concatenate("This is ", "SCALA Functional Programming"))

  // function types Function2[A, B, R] === ((A, B) => R)

  // ALL FUNCTIONS IN SCALA ARE OBJECTS

  // Higher Order Functions => Are functions which takes either function as a param or return function as a result

  val superAdder : Int => (Int => Int) = (v1: Int) =>(v2: Int) =>  v1 + v2

  val add3 = superAdder(10)
  println(add3(20)) // 30

  println(superAdder(10)(30)) // 40  // curried function
}

/*
  TakeAways :
    1. we want to work with functions
         . pass functions as parameters
         . use functions as values (return types)
    2. Problem : Scala works on top of JVM
      . designed for java
      . first-class elements : objects (instances of classes)
    3. Solution : ALL SCALA FUNCTIONS are OBJECTS !
      . function traits, upto 22 params
 */