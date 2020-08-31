import Task3.TSStack
import org.scalatest.flatspec._
import org.scalatest.matchers._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class Task3Spec extends AnyFlatSpec with should.Matchers {
  "Task 3" should "implement concurrent push with pop operations" in {
    val s1 = new TSStack[Int]()
    s1.push(10)
    s1.push(20)
    s1.push(30)

    (1 to 1000).foreach {_ =>

      val futurePush = Future {
        s1.push(1)
        s1.push(2)
        s1.push(3)
      }

      val futurePop = Future {
        s1.pop()
        s1.pop()
        s1.pop()
      }

      val futures = (1 to 10).flatMap(_ => List(futurePush, futurePop))

      Await.result(Future.sequence(futures), Duration.Inf)
    }
    s1.size should be(3)
  }

  it should "implement concurrent pop" in {
    val pushOnlyStack = new TSStack[Int]()

    (1 to 1000).foreach {_ =>

      val futurePush = List(Future(pushOnlyStack.push(1)),
        Future(pushOnlyStack.push(1)),
        Future(pushOnlyStack.push(1)))

      Await.result(Future.sequence(futurePush), Duration.Inf)
    }

    pushOnlyStack.size should be(3 * 1000)
  }

  it should "allow to store different types" in {
    val intStack = new TSStack[Int]()
    intStack.push(10)
    intStack.push(20)
    intStack.pop() should be(Some(20))
    intStack.pop() should be(Some(10))

    val stringStack = new TSStack[String]()
    stringStack.push("10")
    stringStack.push("20")
    stringStack.pop() should be(Some("20"))
    stringStack.pop() should be(Some("10"))
  }

  it should "return None if stack is empty" in {
    val intStack = new TSStack[Int]()
    intStack.push(10)
    intStack.pop() should be(Some(10))
    intStack.pop() should be(None)
    intStack.pop() should be(None)
  }

}
