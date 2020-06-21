package lessons.beginners.part2oop

object Objects {

  //SCALA DOESN'T HAVE CLASS-LEVEL Functionality ("static")
  //SCALA OBJECT ==> SINGLETON INSTANCE (type + it's only instance)
  // NO need to have more boilerplate code to create SINGLETON
  object Person {
    // "static"/"class" level functionality
    val N_EYES = 2
    def canFly = false

    // FACTORY METHOD
    def apply(name: String) = new Person(name)
  }

  class Person(val name : String) {
    // instance level functionality
  }

  // COMPANIONS (object person + class person)

  // NO NEED TO extends App. either you can have main(..) (like java public void static main(args..)
  def main(args: Array[String]) : Unit = {
    println(Person.N_EYES)  // can access like static members
    println(Person.canFly)

    val mary = Person
    val john = Person
    println(mary == john) // true => both mary and john are type Person and they are equal because SINGLETON

    val raja = Person("Raja")
    val rani = Person("Rani")

    println(raja == rani) // false => becuase raja and rani are instances of Person companion class

  }
}


/*
    TAKEAWAYS:
      1. Scala doesn't have "static" values/methods
      2. Scala Objects :
          * scala objects are in their own class
          * scala objects are the only instance
          * scala objects are singleton pattern in one line
      3. Scala Companions
         * can access each other's private members
 */