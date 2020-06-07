package lessons.part2oop

object OOBasics extends App {
 val person: Person = new Person("Raja", 26)

 // println(person.name)   // NOT A CLASS FIELD
  println(person.age)

  // Can access FIELDS
  println(person.education)
  println(person.address)

  val person1 = new Person    // AUXILIARY CONSTRUCTOR of NO Params
  println(s"${person1.age}")

  val person2 = new Person("Raja")  // AUXILIARY CONSTRUCTOR Of name parameter


  person2.greet("Achillies")
  person1.greet()

}
       //CONSTRUCTOR
class Person(name: String = "Raja", val age: Int = 26) { // CONSTRUCTOR PARAMS are not FIELDS Unless adding val/var
        //FIELDS
         val education = "B-Tech"
         var address = "Singapore"

        // AUXILIARY CONSTRUCTORS (MULTIPLE CONSTRUCTORS)
        def this(name: String) = this(name, 26)
        def this() = this("Raja")

        // NOTE : AUXILIARY CONSTRUCTORS Can remove by using default args at constructor

         //METHOD
         def greet(name: String) = println(s"${this.name} says, Hi, $name")
         //OVERLOADED METHOD (same method name with signature(different method parameters) )
         def greet() = println(s"$name says, Hello, Everyone!")


}