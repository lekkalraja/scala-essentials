package lessons.advanced.part4implicits

object PimpMyLibrary extends App {

  implicit class RichInt(val value: Int) extends AnyVal {
    def isEven: Boolean = value % 2 == 0
    def sqrt: Double = Math.sqrt(value)
    def times(f: () => Unit) = {
      def timesAux(n: Int): Unit = {
        if (n <= 0) ()
        else {
          f()
          timesAux(n - 1)
        }
      }
      timesAux(value)
    }
  }

  println(new RichInt(3).isEven) // False
  println(4.isEven) // True ==> Implicitly compiler converts as new RichInt(4).isEven

  /**
   * compiler doesn't do multiple implicit searches
   */

  implicit class RicherInt(richInt: RichInt) {
    def isOdd: Boolean = !richInt.isEven
  }

  // println(5.isOdd) // Cannot resolve symbol isOdd

  1 to 10 // to is the implicit from scala.runtime.RichInt class

  /**
   * Enrich the String class
   * - asInt
   * - encrypt
   */

  implicit class RichString(val value: String) extends AnyVal {
    def asInt: Int = value.toInt
    def encrypt(cypherDist: Int) = value.map(char => (char + cypherDist).asInstanceOf[Char])
  }

  println("3030".asInt) // 3030
  println("Raja".encrypt(2)) // Tclc

  4.times(() => println("Scala Rocks!!!"))
}