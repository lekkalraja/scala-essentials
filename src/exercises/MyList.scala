package exercises

import lessons.part2oop.CaseClasses.Person

abstract class MyList[+A]{
  def head : A
  def tail : MyList[A]
  def add[B >: A] (element: B) : MyList[B]
  def isEmpty : Boolean
  def printElements : String
  def filter(predicate: MyPredicate[A]) : MyList[A]
  def map[B](map: MyTransformer[A, B]) : MyList[B]
  def flatMap[B](map: MyTransformer[A, MyList[B]]) : MyList[B]
  def ++[B >: A](element: MyList[B]) : MyList[B]
  override def toString : String = s"[ $printElements ]"
}

trait MyPredicate[-T] {
  def test(element : T) : Boolean
}

trait MyTransformer[-A, B] {
  def transform(element : A) : B
}

case object Empty extends MyList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyList[Nothing] = throw new NoSuchElementException
  override def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def isEmpty: Boolean = true
  override def printElements: String = s""

  override def filter(predicate: MyPredicate[Nothing]): Empty.type = Empty
  override def map[B](map: MyTransformer[Nothing, B]): Empty.type = Empty
  override def flatMap[B](map: MyTransformer[Nothing, MyList[B]]): Empty.type = Empty

  override def ++[B >: Nothing](element: MyList[B]): MyList[B] = element
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  override def head: A = h
  override def tail: MyList[A] = t
  override def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  override def isEmpty: Boolean = false
  override def printElements: String = {
    if (t.isEmpty) s"$h"
    else s"$h  ${t.printElements}"
  }

  override def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(head)) new Cons(head, t.filter(predicate))
    else t.filter(predicate)

  override def map[B](mapper: MyTransformer[A, B]) = new Cons(mapper.transform(head), t.map(mapper))

  override def flatMap[B](map: MyTransformer[A, MyList[B]]): MyList[B] = map.transform(head) ++ tail.flatMap(map)

  override def ++[B >: A](element: MyList[B]): MyList[B] = new Cons[B](head, tail ++ element)
}

object ListTest extends App {
  val list = Cons(1, Empty)
  val list1 = list.add(10).add(20).add(31).add(34)

  println(list1.filter(_ % 2 != 0)) //[ 31  1 ]
  println(list1.map(_ * 2))  //[ 68  62  40  20  2 ]

  val list3 = Cons("Hello", Empty)
  val list5 = list3.add("Scala").add("Java")
  println(list5.filter(_ == "Scala")) // [ Scala ]
  println(list5.map(item =>s"$item G"))  //[ Java G  Scala G  Hello G ]
  println(list1 ++ list5) // [ 34  31  20  10  1  Java  Scala  Hello ]
  println(list1.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = Cons( element, Cons(element + 1, Empty))
  }))  //[ 34  35  31  32  20  21  10  11  1  2 ]

  // Use case classes

  val persons = Cons(Person("Raja", 26), Empty).add(Person("Sekhar", 25)).add(Person("Mary", 27))
  println(persons) // [ Person(Mary,27)  Person(Sekhar,25)  Person(Raja,26) ]
}