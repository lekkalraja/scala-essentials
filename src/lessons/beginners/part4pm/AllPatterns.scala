package lessons.beginners.part4pm

import exercises.{Cons, Empty, MyList}

object AllPatterns extends App {

  //1. Constants
  object Object
  def matchConstants(x: Any) = x match {
    case 1 => "I am Integer"
    case "Scala" => "I am String"
    case true => "I am Boolean!"
    case Object => "I am Object"
    case _ => "I am Nothing"
  }
  println(matchConstants(1))  // I am Integer
  println(matchConstants("Scala")) // I am String
  println(matchConstants(true)) // I am Boolean!
  println(matchConstants(Object)) // I am Object
  println(matchConstants(2)) // I am Nothing
  println(matchConstants(false)) // I am Nothing

  //2. MATCH ANYTHING
  ////2.1 WILD_CARD's
  def matchEveryThing_1(x: Any) = x match {
    case _ => "I Will Match Everything"
  }
  println(matchEveryThing_1(1))  // I Will Match Everything
  println(matchEveryThing_1("Scala")) // I Will Match Everything
  println(matchEveryThing_1(true)) // I Will Match Everything
  println(matchEveryThing_1(Object)) // I Will Match Everything

  ////2.2 Variable
  def matchEveryThing_2(x: Any) = x match {
    case variable => s"I am $variable"
  }
  println(matchEveryThing_2(1))  // I am 1
  println(matchEveryThing_2("Scala")) // I am Scala
  println(matchEveryThing_2(true)) // I am true
  println(matchEveryThing_2(Object)) // I am lessons.beginners.part4pm.AllPatterns$Object$@11758f2a

  //3. TUPLES

  def matchTuples(x: Any) = x match {
    case (1, 1) => "I am containing all 1's"
    case (something, 2) => s"My First Element is $something and second is 2"
    case _ => "I am Not matching tuple"
  }

  println(matchTuples((1,1))) // I am containing all 1's
  println(matchTuples((1,2))) // My First Element is 1 and second is 2
  println(matchTuples((2,1))) // I am Not matching tuple
  println(matchTuples((2,2))) // My First Element is 2 and second is 2
  println(matchTuples((3,4))) // I am Not matching tuple

  // Pattern Matchings Can be nested

  def nestedTuples(x: Any) = x match {
    case (_, (1, 1)) => "Nested Elements are 1's"
    case (_, (1, v)) => s"Nested Elements are 1 and $v"
    case (_, (v1, v2)) => s"Nested Elements are $v1 and $v2"
  }

  println(nestedTuples(5,(1,1))) // Nested Elements are 1's
  println(nestedTuples(4,(1,1))) // Nested Elements are 1's
  println(nestedTuples(3,(1,9))) // Nested Elements are 1 and 9
  println(nestedTuples(5,(4,3))) // Nested Elements are 4 and 3

  //4.  Case Classes -> Constructor Pattern (Extracted Pattern)
  // Pattern matches can be nested with case classes as well

  def matchCaseClass(myList: MyList[Any]) : String = myList match {
    case Empty => "Got Empty Collection"
    case Cons(2, Cons(v, Empty)) => s"Got Cons with head as 2, $v and tail as Empty with another collection"
    case Cons(head, Cons(subhead, tail)) => s"Got $head and sub head $subhead and ${matchCaseClass(tail)}"
    case _ => "Got Different One"
  }

  println(matchCaseClass(Empty)) // Got Empty Collection
  println(matchCaseClass(Cons(2, Empty))) // Got Different One
  println(matchCaseClass(Cons(2, Cons(1, Empty)))) // Got Cons with head as 2, 1 and tail as Empty with another collection
  println(matchCaseClass(Cons(2, Cons(1, Cons(0, Empty))))) // Got 2 and sub head 1 and Got Different One

  //5 - LIST PATTERNS

  def matchList(list: List[Any]) = list match {
    case List(1, _, _, _) => "List start's with 1 and size is 4"  //extractor pattern -- advanced
    case List(2, _*) => "List start's wth 2 and don't care about remaining elements and size (arbitrary length)"
    case 3 :: List(_*) => "head is 4 and remaining elements extracted" //infix pattern => tail is list of arbitrary length
    case 4 :: List(_) => "head is 4 and tail is not cared of list with size 2" //infix pattern => tail is list one ele
    case List(4,5,6) :+ 8 => "List of 1, 2, 3 and last element is 8"  // infix pattern
    case _ => "Pattern Doesn't exist"
  }

  println(matchList(List(1,2,3,4))) // List start's with 1 and size is 4
  println(matchList(List(2,3,4,5, 6, 7))) // List start's wth 2 and don't care about remaining elements and size (arbitrary length)
  println(matchList(List(3,4,1,2,3,4,5,6))) // head is 4 and remaining elements extracted
  println(matchList(List(4,5,6,8))) // List of 1, 2, 3 and last element is 8
  println(matchList(List(4,5))) // head is 4 and tail is not cared of list with size 2
  println(matchList(List(9,5,6,8))) // Pattern Doesn't exist

  //6 -- TYPE SPECIFIERS

  def matchType(element: Any) = element match {
    case int: Int => s"Passed Int with value $int"
    case string: String => s"Passed String with value $string"
    case bool: Boolean => s"Passed Boolean with value $bool"
    case float: Float => s"Passed Float with value $float"
    case list: List[Any] => s"Passed List with value $list"
    case _ => "No Matched Typed"
  }

  println(matchType(1)) // Passed Int with value 1
  println(matchType("String")) // Passed String with value String
  println(matchType(true)) // Passed Boolean with value true
  println(matchType(3.14F)) // Passed Float with value 3.14
  println(matchType(List(1,2,3))) // Passed List with value List(1, 2, 3)
  println(matchType(Set(1,2,3))) // No Matched Typed
  println(matchType(1235L)) // No Matched Typed

  // 7 - NAME BINDING

  def nameBinding(myList: MyList[Any]) = myList match {
    case nonEmptyList @ Cons(1, _) => s"Non Empty List $nonEmptyList"
    case Cons(_, rest @ Cons(h, Empty)) => s"Nested Name Binding with $rest and $h"
    case _ => "Didn't match Anything!!"
  }

  println(nameBinding(Cons(1,Empty))) //Non Empty List [ 1 ]
  println(nameBinding(Cons(2,Cons(3, Empty))))  // Nested Name Binding with [ 3 ] and 3
  println(nameBinding(Cons(2,Cons(3, Cons(4, Empty))))) // Didn't match Anything!!
  println(nameBinding(Cons(3,Empty))) // Didn't match Anything!!

  // 8 - MULTI PATTERNS

  def multiPattern(myList: MyList[Any]) = myList match {
    case Empty | Cons(1, _) => "Got Empty list or List with 2 elements and starts with 1"
    case  _ => "Didn't Match Anything!!!"
  }

  println(multiPattern(Empty))  // Got Empty list or List with 2 elements and starts with 1
  println(multiPattern(Cons(1, Empty))) // Got Empty list or List with 2 elements and starts with 1
  println(multiPattern(Cons(1, Cons(3, Empty)))) // Got Empty list or List with 2 elements and starts with 1
  println(multiPattern( Cons(3, Empty))) // Didn't Match Anything!!!

  // 9 - IF GUARDS

  def ifGuardPattern(list: List[Int]) = list match {
    case List(_, element, _) if element % 2 == 0=> "Second element is Even"
    case _ => "Didn't get second element as even"
  }

  println(ifGuardPattern(List(1,4,5))) // Second element is Even
  println(ifGuardPattern(List(1,2,5))) // Second element is Even
  println(ifGuardPattern(List(1,3,5))) // Didn't get second element as even
  println(ifGuardPattern(List(1,7,5))) // Didn't get second element as even

  /////// ISSUE WITH JVM GENERICS

  def listTypeCheck(list: List[Any]) = list match {
    case listOfStrings : List[String] => s"Got List of Strings $listOfStrings"
    case listOfIntegers : List[Int] => s"Got List of Strings $listOfIntegers"
    case _ => "Didn't Match Anything"
  }

  println(listTypeCheck(List(1,2,3,4))) //Got List of Strings List(1, 2, 3, 4)  ==> JVM will not care about generics at runtime.
  println(listTypeCheck(List("1","2","3","4"))) //Got List of Strings List(1, 2, 3, 4)
  println(listTypeCheck(List("Java", "Scala"))) // Got List of Strings List(Java, Scala)

}