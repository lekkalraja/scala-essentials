package exercises

abstract class MyList {
  def head : Int
  def tail : MyList
  def add(element: Int) : MyList
  def isEmpty : Boolean
  def printElements : String
  override def toString : String = s"[ ${printElements} ]"
}

object Empty extends MyList {
  override def head: Int = throw new NoSuchElementException
  override def tail: MyList = throw new NoSuchElementException
  override def add(element: Int): MyList = new Cons(element, Empty)
  override def isEmpty: Boolean = true
  override def printElements: String = s""
}

class Cons(h: Int, t: MyList) extends MyList {
  override def head: Int = h
  override def tail: MyList = t
  override def add(element: Int): MyList = new Cons(element, this)
  override def isEmpty: Boolean = false
  override def printElements: String = {
    if (t.isEmpty) s"$h"
    else s"$h  ${t.printElements}"
  }
}

object ListTest extends App {
  val list = new Cons(1, Empty)
  println(list.head)
  println(list.tail)
  println(list.toString)
  val list1 = list.add(10).add(20)
  println(list1.head)
  println(list1.tail)
  print(list1.toString)
}