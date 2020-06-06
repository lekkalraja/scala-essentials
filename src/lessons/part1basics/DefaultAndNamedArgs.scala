package lessons.part1basics

object DefaultAndNamedArgs extends App {

  def fact(n: Int, acc: Int = 1): Int =  // acc: Int = 1 ==> Default Argument
    if ( n == 1) acc
    else fact(n - 1, n * acc)

  println(fact(3))  // Above specified acc as Default Argument so passing acc is optional

  def savePicture(format: String = "jpeg", width: Int = 800, height: Int = 900) =
    println("Saving Picture")

  savePicture()  // Will take all arguments default values
  //savePicture(800, 900) // should give parameters in the LEFT TO RIGHT format other wise will throw error
  savePicture(width = 400)  // Can used NAMED PARAMETERS to give only specific argument
  savePicture(height = 800, format = "png", width = 300)  // can give parameters in any order with NAMED ARGS


}
