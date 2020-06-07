package exercises

abstract class MyList[+A] {
  def head : A
  def tail : MyList[A]
  def add[B >: A] (element: B) : MyList[B]
  def isEmpty : Boolean
  def printElements : String
  override def toString : String = s"[ $printElements ]"
}

object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def isEmpty: Boolean = true
  override def printElements: String = s""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
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

  val list3 = new Cons("Hello", Empty)
  println(list3.head)
  println(list3.tail)
  println(list3.toString)
  val list5 = list3.add("Scala").add("Java")
  println(list5.head)
  println(list5.tail)
  print(list5.toString)
}