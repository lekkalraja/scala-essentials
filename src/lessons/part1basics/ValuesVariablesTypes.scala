package lessons.part1basics

object ValuesVariablesTypes extends App {

  //VAL's are Immutable, can't be reassigned (Like constants  => Java final)

  val age : Int = 10
  println(age)

  // age = 26  => Can't change value.. Compiler will throw error (Reassigned to val)

  val name = "Raja"  //Compiler can infer type.. no need to explicitly specify the type of variable

  val aInt: Int = 10
  val aString: String = "A String"
  val aChar: Char = 'a'
  val aShort: Short = 3234
  val aLong: Long = 23L      // specify Long value with 'L' at the end
  val aFloat: Float = 0.3F   // specify float value with 'F' at the end
  val aDouble: Double = 3.14
  val aBoolean: Boolean = true   // boolean values are keywords : true/false

  //VAR's are mutable, can change or reassign values to variable (like variables)
  var score: Int = 32
  score += 1
  println(score)


  // semicolon (;) are optional in scala
  var lang = "SCALA"; var version = "2.13.2"

}

/*
===============TakeAways ::

1. prefer vals over vars (for functional programming)
2. all vals and vars have types
3. compiler automatically infers types when omitted
4. learned how to use basic types
    * Boolean
    * Int, Long, Short
    * Float, Double
    * Char, String
 */