import Task2.{FreeUser, PaidUser, UserAction}
import org.scalatest.flatspec._
import org.scalatest.matchers._

class Task2Spec extends AnyFlatSpec with should.Matchers {
  "Task 2" should "evaluate correct levels and experience values for free user" in {
    val freeUser = new FreeUser("free_user_1")

    val userActions = List(UserAction("action1"), UserAction("action2"))
    freeUser.currentLevel should be(0)
    freeUser.currentExp should be(0)

    for (day <- Range(1, 3)) {
      freeUser.everydayOperation(userActions)
      freeUser.endOfTheDay()
    }

    freeUser.currentLevel should be(1)
    freeUser.currentExp should be(100)
  }

  it should "evaluate correct values for paid user too" in {
    val paidUser = new PaidUser("paid_user_1")

    val userActions = List(UserAction("action1"), UserAction("action2"))
    paidUser.currentLevel should be(0)
    paidUser.currentExp should be(0)

    for (day <- Range(1, 3)) {
      paidUser.everydayOperation(userActions)
      paidUser.endOfTheDay()
    }

    paidUser.currentLevel should be(0)
    paidUser.currentExp should be(400)
  }

  it should "generate an exception if user with suck username has already exists" in {
    new FreeUser("test_user")

    a [IllegalArgumentException] should be thrownBy {
      new PaidUser("test_user")
    }
  }

  it should "generate an exception if user username is incorrect" in {
    a [IllegalArgumentException] should be thrownBy {
      new PaidUser("$$test")
    }

    a [IllegalArgumentException] should be thrownBy {
      new PaidUser("**test))")
    }
  }

  it should "paid users have limit of payed days" in {
    val paidUser = new PaidUser("paid_user_2", paidDaysCounter = 2)
    val userActions = List(UserAction("action1"), UserAction("action2"))

    for (day <- Range(1, 3)) {
      paidUser.everydayOperation(userActions)
      paidUser.endOfTheDay()
    }

    a [IllegalArgumentException] should be thrownBy {
      paidUser.everydayOperation(userActions)
      paidUser.endOfTheDay()
    }

  }

  it should "if free users actions list have less than 3 actions we should extend the number of actions to 3" in {
    val freeUser = new FreeUser("free_user_2")
    val userActions = List(UserAction("action1"))

    freeUser.everydayOperation(userActions)
    freeUser.endOfTheDay()

    freeUser.currentLevel should be(0)
    freeUser.currentExp should be(300)

  }

  it should "if free user perform more than his actions limit we raise an exception" in {
    val freeUser = new FreeUser("free_user_3", 1)
    val userActions = List(UserAction("action1"), UserAction("action2"), UserAction("action3"))

    a [IllegalArgumentException] should be thrownBy {
      freeUser.everydayOperation(userActions)
      freeUser.endOfTheDay()
    }

  }

}
