package Task2

import scala.collection.immutable.HashSet
import scala.collection.mutable

case class UserAction(description: String) // user action case class. We could even use case object for this purpose

abstract class BaseUser(username: String) {
  /**
   * Base class for all users. Contains user expirience and level fields
   * and implement all logic. I use require for checking if user username is valid.
   * Also this class implement method for every day actions and method for perform some logic when the end of the day will come.
   */
  require(isValidUsername(username) && BaseUser.notRegisteredYet(username),
    "User username is incorrect or has already registered")

  private var exp: Int = 0
  private var level: Int = 0

  def currentLevel: Int = this.level
  def currentExp: Int = this.exp


  private def evaluateActions(actions: List[UserAction]): Unit = {
    this.exp += actions.length * BaseUser.expPerAction
  }

  private def transferExpToLevel(): Unit = {
    val addToLevel = this.exp / BaseUser.expPerLevel
    if (addToLevel > 0) {
      this.level += addToLevel
      this.exp -= BaseUser.expPerLevel * addToLevel
    }
  }

  override def toString: String = s"User username: $username Experience: $exp Level: $level"

  def endOfTheDay(): Unit = {
    this.transferExpToLevel()
  }

  def everydayOperation(userActions: List[UserAction]): Unit = {
    this.evaluateActions(userActions)
  }

  private def isValidUsername(username: String): Boolean = BaseUser.usernameRegexp.matches(username)
}

object BaseUser {
  /**
   * Base user companion object. Contains regexp for checking username, how many experience user will achieve per action and
   * how many experience should convert to increase user level. Also this companion object contains HashSet for checking
   * if username is unique or not.
   */
  private val usernameRegexp = """[a-zA-Z0-9-._]+""".r
  private val expPerAction = 100
  private val expPerLevel = 500
  private[this] var alreadyRegisteredUsernames = new HashSet[String]()

  private def notRegisteredYet(username: String): Boolean = {
    if (this.alreadyRegisteredUsernames.contains(username)) {
      false
    }
    else {
      alreadyRegisteredUsernames += username
      true
    }
  }
}
