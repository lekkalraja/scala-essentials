package lessons.part4pm

object PatternsEverywhere extends App {

  // BIG IDEA# 1

  try {
    println("RISK CODE")
  }catch {
    case e: RuntimeException => s"Runtime Exception $e"
    case npe: NullPointerException => s"NullPointer Exception $npe"
    case _ => "Something else"
  }

  // CATCHES ARE ACTUALLY MATCHES
  /*
      try {
        // CODE
      } catch (e) {
        e matches {
          case e: RuntimeException => s"Runtime Exception $e"
          case npe: NullPointerException => s"NullPointer Exception $npe"
          case _ => "Something else"
        }
      }
   */

  // BIG IDEA# 2

  val list = List(1,2,3,4)

  val evenNumbers = for {
    even <- list if even % 2 == 0
  } yield even

  println(evenNumbers) // List(2,4)

  // GENERATORS ARE ALSO BASED ON PATTERN MATCHING

  val tuples = List((1,2),(3,4), (6,5))
  val filtered = for {
    (first, second) <- tuples if second % 2 == 0
  } yield  first * second

  println(filtered) // List(2, 12)

  //CASE CLASSES, :: OPERATORS, ...

  // BIG IDEA# 3
  // MULTIPLE VALUE DEFINITIONS ARE BASED ON PATTERN MATCHING
  val (a, b, c) = (1, 3, 4)
  println(a, b, c)  // (1, 3, 4)

  val head :: tail  = List(1,2,3,4,5,6)

  println(head)  // 1
  println(tail)  // List(2,3,4,5,6)

  // BIG IDEA# 4
  // PARTIAL FUNCTION

  val elements = List(1, 2, 3, 4)

  val mappedList = elements.map{
    case v if v % 2 == 0 => s"$v is even number"
    case 1 => "THE ONE"
    case _ => "Something else"
  }

  /*
        val mappedList = elements.map{ x => x match {
          case v if v % 2 == 0 => s"$v is even number"
          case 1 => "THE ONE"
          case _ => "Something else"
          }
        }
   */

  println(mappedList) // List(THE ONE, 2 is even number, Something else, 4 is even number)
}