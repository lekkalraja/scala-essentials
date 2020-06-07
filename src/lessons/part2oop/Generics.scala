package lessons.part2oop

object Generics extends App{

  class MyList[A] {
    def add(element: A) : MyList[A] = new MyList[A]
  }

  val intList = new MyList[Int]
  val stringList = new MyList[String]
  val floatList = new MyList[Float]


  /**************** VARIANCE PROBLEMS  ***********/

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. COVARIANCE  (List[Cat] extends List[Animal]
    class CovarianceList[+A] { // Can fill A and it's sub classes
      // Add super type of element and give super type of list  [B :> A] B is the super type of A
      def add[B >: A](element: B) : CovarianceList[B] = new CovarianceList[B]
    }
    val animal : Animal = new Cat
    val animalList : CovarianceList[Animal] = new CovarianceList[Cat]
    //??? HARD QUESTION (Adding Dog Animal in the List of CAT's) => Resolved By add[B >: A](element: B) : CovarianceList[B]
    val list : CovarianceList[Animal] = animalList.add(new Dog)


  // 2. INVARIANCE
      class InvariantsList[A]
      val invariantsList : InvariantsList[Animal] = new InvariantsList[Animal]

  // 3. CONTRAVARIANT
      class Trainer[-A]   /***** Sub Classes of A  *********/
      val trainers : Trainer[Cat] = new Trainer[Animal]


  /********** Bounded Types **********/
     class Cage[A <: Animal]
     val catsCage : Cage[Cat] = new Cage[Cat]
     val dogsCage : Cage[Dog] = new Cage[Dog]

     class Car
     //val carsCage : Cage[Car] = new Cage[Car]  // Throws ERROR because Car is not sub type of Animal
}