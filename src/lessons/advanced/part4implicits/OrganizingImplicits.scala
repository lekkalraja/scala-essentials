package lessons.advanced.part4implicits

object OrganizingImplicits extends App {

  /**
   * Implicits (used as parameters)
   *   -- val/var
   *   -- object
   *   -- accessor methods = defs with no parentheses
   */

  //implicit val ordering(): Ordering[Int] = Ordering.fromLessThan(_ > _) // ==> WONT WORK
  implicit val ordering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  println(List(1,4,3,2,6).sorted)

  case class Person(name: String, age: Int)

  val persons = List(Person("Raja", 27),
    Person("Achillies", 112129),
    Person("Hector", 45))

  implicit  val personOrdering : Ordering[Person] = Ordering.fromLessThan(_.name < _.name)
  println(persons.sorted)

  case class Purchase(nUnits: Int, nPrice: Double)

  object Purchase {
    implicit val totalPriceOrdering: Ordering[Purchase] = Ordering.fromLessThan((p1,p2) => p1.nPrice*p1.nUnits < p2.nPrice * p2.nUnits)
  }

  object UnitCountOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nUnits < _.nUnits)
  }

  object UnitPriceOrdering {
    implicit val unitCountOrdering: Ordering[Purchase] = Ordering.fromLessThan(_.nPrice < _.nPrice)
  }

  private val purchases = List(Purchase(20, 20.0),
    Purchase(20, 90.0),
    Purchase(30, 23.0),
    Purchase(10, 21.0),
    Purchase(40, 20.0),
  )


  //  import UnitCountOrdering._
  //import UnitPriceOrdering._
  println(purchases.sorted)


}
