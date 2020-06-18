package lessons.part3fp

import scala.util.{Random, Try}

object MapsAndTuples extends App {


  val tuple = Tuple2("1", "Raja")
  val tuple1 = ("2", "Sekhar")

  println(tuple._2, tuple1._2)

  val names = Map(("Raja", 1234), "sekhar" -> 456, "RAJA" -> 5343).withDefaultValue(-1);
  println(names("Raja"))
  println(names.get("Raja"))
  println(names.get("RAJA"))
  println(names("RAJA"))

  case class Person(val name: String, val age: Int, val address: Address)

  case class Address(val city: String)

  private val persons = Map("Test1" -> Person("Test1", 24, Address("Singapore")),
    "Test2" -> Person("Test2", 20, Address("Texas")),
    "Test3" -> Person("Test3", 25, Address("New YORK")),
    "Test4" -> Person("Test4", 21, Address("Washington")),
    "Test5" -> Person("Test5", 23, Address("Switzerland")),
  )

  persons.map(pair => (pair._1, Person(pair._2.name, pair._2.age+20, pair._2.address)))
    .toSeq.sortBy(_._2.age)
    .foreach(entry => println(entry._2))

  val host = "10.20.20.10"
  val port = 8080

  class Connection(val url: String) {
    def getConnection() : String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "<Html>....</Html"
      else throw new RuntimeException("I will not give it to you")
    }

    def getConnectionSafe() = Try(getConnection())
  }

  object HttpService {
    def get(url: String) : Connection = {
     val random = new Random(System.nanoTime())
      if(random.nextBoolean()) new Connection(url)
      else throw  new RuntimeException("No Connection Object For you!!!")
    }

    def getSafe(url: String) = Try(get(url))
  }

  def backConnection() : Try[String] = Try("I have backup... Take It!!!")

  val result = Try(HttpService.get(s"$host:$port"))

  result.map(_.getConnection()).orElse(backConnection()).foreach(println)

  // for comprehension
 val data = for {
    conn <- HttpService.getSafe(s"$host:$port")
    html <- conn.getConnectionSafe()
  } yield html

  data.orElse(backConnection()).foreach(println)

}
