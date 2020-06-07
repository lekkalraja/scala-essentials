package lessons.part2oop

object OOBasicsExercises extends  App {
  
  val writer = new Writer("Raja", "Lekkala", 1993)
  println(writer.fullName())

  val novel = new Novel("LifeOfFailures", 2010, writer)
  println(novel.authorAge())
  println(novel.isWrittenBy(writer))
  println(novel.copy(2012).authorAge())

  val counter = new Counter(1)
  println(counter.currentCount())
  println(counter.inc().currentCount())
  println(counter.dec().currentCount())
  println(counter.inc(5).currentCount())
  println(counter.dec(3).currentCount())

}

/*
   Classes : Novel and Writer
   Writer : firstName, surName, Year
      - method - fullName

   Novel: name, year of release, author
     - Methods - authorAge
               - isWrittenBy(author)
               - copy(new year of release) = new instance of Novel
 */

class Writer(firstName: String, surName: String, val year: Int) {
  def fullName() = s"$firstName $surName"
}

class Novel(name: String, yearOfRelease:Int, author:Writer) {
  def apply(name: String, year: Int, author: Writer) = new Novel(name, year, author)

  def authorAge() = yearOfRelease - author.year
  def isWrittenBy(author: Writer) = this.author.fullName().equals(author.fullName())
  def copy(year: Int) = this(name, year, author)
}

/*
   Counter class
        - receives an int value
        - method current count
        - method to increment/decrement => new counter
        - overload inc/dec to receive an amount
 */

class Counter(count: Int) {
  def apply(i: Int) = new Counter(i)

  def currentCount() = count

  def inc() = {
    println("Incrementing..")
    this(count + 1)
  }
  /*def inc(amount: Int) = {
    this(count + amount)
  }*/


  def inc(amount: Int): Counter = {
    if (amount <= 1) this
    else inc.inc(amount - 1)
  }

  def dec() = {
    println("Decrementing...")
    this(count - 1)
  }

  /*def dec(amount: Int) = {
    this(count - amount)
  }*/

  def dec(amount: Int): Counter = {
    if (amount <= 1) this
    else dec.dec(amount - 1)
  }
}