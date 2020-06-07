package lessons.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String) {

    //CAN USE FOR INFIX
    def likes(movie: String) = this.favouriteMovie == movie
    def hangout(person: Person) = s"${this.name} Hang outs with ${person.name}"
    def +(person: Person) = s"${this.name} + ${person.name}"

    //CAN USE FOR PREFIX
    def unary_! = s"Hey $name, What the HECK!"

    //CAN USE FOR POSTFIX
    def isAlive = true

    //apply methods
    def apply(): String = s"This is Scala Feature!!!"

    def lives(city: String, country: String) = s"$name lives in $country of $city"

  }
  val raja = new Person("Raja", "Troy")
  val aj = new Person("AJ", "Troy")

  println(raja.likes("Troy"))
  println(raja.hangout(aj))

  // INFIX NOTATION (OR) OPERATOR NOTATION ==> NOTE: WORKS WITH ONLY SINGLE PARAMETER METHODS
  println(raja likes "Troy")
  println(raja hangout aj)

  println(raja.lives("Tampanies", "Singapore"))
  //println(raja lives "Tampanies" "Singapore") // THROWS COMPILATION ERROR

  // "operators" in SCALA
  println(raja + aj);      println(1 + 3)
  println(raja.+(aj));     println(1.+(3))

  // ALL OPERATORS ARE METHODS

  // PREFIX NOTATION
  val x = -1  // EQUIVALENT with unary_-
  val y = 1.unary_-
  println(x, y)
  println(!raja, raja.unary_!)
  // unary_PREFIX only works with - + ~ !


  //POSTFIX NOTATION
  /*
      TO ENABLE THIS EITHER import scala.language.postfixOps
                     OR set the COMPILER option -language:postfixOps
   */
  println(raja.isAlive)
  println(raja isAlive)

  //apply method NOTATION
  println(raja.apply())
  println(raja())
}