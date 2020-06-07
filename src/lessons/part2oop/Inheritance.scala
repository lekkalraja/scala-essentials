package lessons.part2oop

object Inheritance extends App{

  class Animal {
    val age = 30
    def eat = println(" I can EAT !")
    private def run = println(" I can't run")
    protected def walk = println(" I can Walk ")
  }

  class Dog extends Animal {
    override val age: Int = 12
    override def eat: Unit = println("I am dog, can eat!!")
    override protected def walk: Unit = println("I am dog, can Walk")
    //private methods can't override
    //protected memebers can override too along with public
  }

  val dog = new Dog
  dog.eat
 // println(dog.walk)  // protected members can access in sub classes but can't access from outside
  println(dog.age)

  //CONSTRUCTORS

  class Person(val name:String, val age: Int){
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, override val age : Int) extends Person(name, age)
  class Kid(name: String) extends  Person(name) // Can use Auxiliary Constructor to create object

  class Vehicle {
    final def canFly = false
  }

  class Car extends  Vehicle {
    // override def canFly = false; //Method 'canFly' cannot override final member
  }

  final class Wood

  //class Chair extends Wood //Illegal inheritance from final class 'Wood'

}

/*
   PREVENTING OVERRIDING
    1. Use final on member
    2. use final on entire class so that can't extend also
    3. sealed - extend classes in THIS FILE only, but prevents extension in other files
 */