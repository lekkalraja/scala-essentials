package lessons.beginners.part2oop

object AbstractDataTypes extends  App {

  abstract  class Animal(val creatureType: String) {
    def eat : Unit;
  }

  trait Human {
    def canFly: Boolean
  }

  trait Student {
    def canLearn : Boolean
  }

  class Women(override val creatureType: String) extends Animal(creatureType) with Human with Student {
    override def eat: Unit = println("I will eat more!!")
    override def canFly: Boolean = true
    override def canLearn: Boolean = true
  }

  val mary : Student = new Women("Psycho")
  println(mary.canLearn)
 // println(mary.creatureType)  // can't access Animal Members
 // println(mary.eat)  // can't access Animal Members

  val aj: Animal = new Women("Good")
  println(aj.creatureType)
  aj.eat
  //println(aj.canLearn) // can't access Student Members

  val tris : Human = new Women("Too GOOD")
  println(tris.canFly)  // Can't access other type members

  val women = new Women("Women")  // Can access all members
  println(women.creatureType)
  println(women.canFly)
  println(women.canLearn)
  women.eat
}

/*
    traits vs abstract class
      1. traits do not have constructor parameter
      2. multiple traits can be extend by single class
      3. traits - behaviour... abstract class == "thing" + "behaviour"

 */