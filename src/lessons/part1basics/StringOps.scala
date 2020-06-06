package lessons.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala!"

  println(str.take(2))
  println(str.reverse)

  var alphabets: String = "2"
  println(alphabets.toInt)
  println('a' +: alphabets :+ 'z') // +: prepending    :+ appending  ==> output a2z

  /* STRING INTERPOLATIONS  */

  // 1. s- INTERPOLATION

  val name = "Raja"
  val age = 26

  println(s"I am $name, $age years old... going to become ${age + 1}")

  // 2. f- INTERPOLATION

  val quantity = 2.2f

  println(f"There are $quantity%2.2f of things")

  // 3. raw - INTERPOLATION

  println(raw"Escape Character will not work \n new line")

  val escape = "Escape Character will work \n new line"

  println(raw"$escape")

}