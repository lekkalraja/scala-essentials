package lessons.part2oop

import scala.language.postfixOps

/*
    1. overload the + operator to add given name as nick name to the actual name

    2. Add a unary + operator to increment the age of person

    3. Add learnScala method

    4. overload apply method which will take how many times watched movie
 */
object MethodNotationsExercises extends App {

  val raja = new Person("Raja", 26, "Troy")

  println((raja + "Raj").name)  // INFIX
  println(-raja.age) // PREFIX  => calls unary_- implementation
  println(raja learnsScala)  // POSTFIX
  println(raja(2)) // apply

  class Person(val name: String, val age: Int, favouriteMovie: String) {
    def apply(str: String, age: Int, favouriteMovie: String) = new Person(str, age, favouriteMovie)
    def apply(number: Int) = s"I watched $favouriteMovie, $number times"

    def +(nickName: String) = this(s"$name ($nickName)", this.age, this.favouriteMovie)

    def unary_-  = this(this.name, this.age+1, this.favouriteMovie)

    def learnsScala = "I am learning SCALA!"
  }

}