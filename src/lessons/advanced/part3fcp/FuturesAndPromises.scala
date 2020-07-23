package lessons.advanced.part3fcp

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.global
import scala.util.{Failure, Success, Try}

object FuturesAndPromises extends App {

  def computeLongCalculation : Int = {
    Thread.sleep(2000)
    43
  }

  val aFuture = Future {
    computeLongCalculation
  }(global) // IMPLICIT Execution Context

  println(aFuture.value)  // WILL RETURN NONE => BECAUSE IT'S STILL PROCESSING

  aFuture.onComplete(t => t match {
    case Success(value) => println(s"Calculated Value $value")
    case Failure(exception) => println(s"Got Exception $exception")
  })(global) // THIS CALLBACK FUNCTION MAY CALL BY SOME OTHER THREAD

  Thread.sleep(3000) // WAIT TILL CHILD THREADS COMPLETES

  // fulfill a future immediatly with a value

  val slow = Future {
    Thread.sleep(1000)
    43
  }(global)

  val fast = Future {
    Thread.sleep(500)
    44
  }(global)

  def inSequence[T](fa: Future[T], fb: Future[T]) = fa.map(item => fb.map(item1 => println(item , item1))(global))(global)

  inSequence(slow, fast)


  def first[A](fa: Future[A], fb: Future[A]) : Future[A] = {
    val promise = Promise[A]
    fa.onComplete(promise.tryComplete)(global)
    fb.onComplete(promise.tryComplete)(global)
    promise.future
  }
  first(fast, slow).foreach(println)(global)

  Thread.sleep(1000)

}