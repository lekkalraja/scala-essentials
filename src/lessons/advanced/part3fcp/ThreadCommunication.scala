package lessons.advanced.part3fcp

import scala.collection.mutable
import scala.util.Random

object ThreadCommunication extends App {

  val thread = new Thread(() => {
    Thread.sleep(1000)
    println("I am "+ Thread.currentThread().getName)
  })

  thread.start()
  thread.join() // will wait till it's (thread) execution completes
  println("I am "+ Thread.currentThread().getName)


  class SimpleContainer {
    private var container: Int = 0

    def isEmpty: Boolean = container == 0

    def set(newValue: Int) : Unit = container += newValue

    def get : Int = {
      if (container == 0) return -1
      val item = container
      container = container - 1
      item
    }

  }
  def naiveProdCons(): Unit = {

    val container = new SimpleContainer
    val consumer = new Thread(() => {
      println("Going to Consume Item ")

      while(container.isEmpty) {
        println("Waiting for the ITEM")
      }

      println("Got Item from Container " + container.get)
    })

    val producer = new Thread(() => {
      println("Started Producing Item")
      Thread.sleep(100)
      container.set(1)
    })

    consumer.start()
    producer.start()
  }

  //naiveProdCons()

  // SYNCHRONIZED WILL BE AVAILABLE ON ANYREF's OBJECTS
  def smartProdCons(): Unit = {
    val container = new SimpleContainer

    val consumer = new Thread(() => {
      println("Going to Consume ITEM")
      container.synchronized(
        container.wait()
      )
      println("Consumed Item from Container " + container.get)
    })

    val producer = new Thread(() => {
      println("Producing Item")
      container.synchronized{
        Thread.sleep(4000)
        container.set(1)
        container.notify()
      }
    })

    consumer.start()
    producer.start()
  }

  //smartProdCons()

  def bulkProdCons(): Unit = {
    val buffer : mutable.Queue[Int] = new mutable.Queue[Int]()
    val capacity : Int = 3

    val consumer = new Thread(() => {
      val random = new Random()
      while (true) {
        buffer.synchronized{
          if( buffer.isEmpty ) {
            println("[CONSUMER] Buffer is EMPTY, Hey Producers what you guys doing???")
            buffer.wait()
          }
          println("[CONSUMER] Consuming ITEM "+buffer.dequeue())
          buffer.notify() // to notify to producers to producer, otherwise producers will keep on wait
        }
        Thread.sleep(random.nextInt(500))
      }
    })

    val producer = new Thread(() => {
      val random = new Random()
      var i = 0
      while(true) {
        buffer.synchronized{
          if(buffer.size == capacity) {
            println("[PRODUCER] Buffer is FULL, Hey Consumers please be fast!!")
            buffer.wait()
          }
          i += 1
          println("[PRODUCER] Producing ITEM "+i)
          buffer.enqueue(i)
          buffer.notify()  // to notify consumer to consume other wise consumers will keep on wait
        }
        Thread.sleep(random.nextInt(500))
      }
    })

    // IF WE DON'T HAVE buffer.notify in producer and consumer, we will be end up with DEADLOCK
    consumer.start()
    producer.start()
  }

  bulkProdCons()

}
