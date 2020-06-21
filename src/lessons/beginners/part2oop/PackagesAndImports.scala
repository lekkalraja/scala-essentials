package lessons.beginners.part2oop

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagesAndImports extends App {
  sayHello
  println(PI)

  var utilDate = new Date
  var sqlDate = new java.sql.Date(2002,2,2) // using fully qualified name
  var sqlDate1 = new SqlDate(2002, 2, 2) // Using alias naming

  println(utilDate)
  println(sqlDate)
  println(sqlDate1)
}


/*
    package = a group of definitions under the same name

    To use a definition
        . be in the same package
        . or import the package
    Best practice - mirror the file structure

    can import
        . Fully qualified name
        . Name aliasing at imports
 */
