package lessons.part2oop

object CaseClasses extends App {


  /* equlas, hascode and tostring are implemented based on fields*/
  case class Person(name: String, age: Int)

  /*
      1. case classes parameters are class fields
   */
  val raja = new Person("Raja", 26)

  println(s" ${raja.name}   ${raja.age}") //  Raja   26

  /*
      2. Sensible To String
      println(instance) == println(instance.toString) => syntactic sugar
   */
  println(raja.toString)  //Person(Raja,26)

  /*
      3. equals and hashcode implemented out of the box
   */
  val raja1 = new Person("Raja", 26)
  println(raja == raja1)  // true

  /*
     4. case classes have handy copy methods
   */

  val raja3 = raja.copy()
  println(raja3)  // Person(Raja,26)
  val raja4 = raja.copy("Rajasekhar")
  println(raja4) // Person(Rajasekhar,26)
  val raja5 = raja.copy(age= 25)
  println(raja5) //Person(Raja,25)

  /*
     5. case classes have companion objects so it will have factory (apply) methods
   */
  val person = Person
  println(person)  //Person

  val raja6 = Person("Raja", 26)
  println(raja6) // Person(Raja,26)

  /*
     6. Case classes are Serializable (can travel through network between jvms)
   */

  /*
    7.  cases classes have extractor patterns => case classes can be used in PATTERN MATCHING
   */

  /*
     8. case objects
   */

  case object India {
    def name: String = "India is the wonderful country"
  }
  println(India.name) //India is the wonderful country
}
