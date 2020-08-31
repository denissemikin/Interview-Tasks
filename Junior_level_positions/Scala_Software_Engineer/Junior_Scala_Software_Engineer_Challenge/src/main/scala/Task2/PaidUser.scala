package Task2

/**
 * Paid user class. Override `everydayOperation` method for checking if user could not perform actions because its paid days counter indicate 0.
 * If his paid days are over he could not perform any actions.
 * @param username username
 * @param paidDaysCounter how many days he could perform actions
 */
class PaidUser(username: String, var paidDaysCounter:Int = 10) extends BaseUser(username) {
  require(paidDaysCounter > 0, "Paid days counter should be greater than 0")

  override def everydayOperation(userActions: List[UserAction]): Unit = {
    if (this.paidDaysCounter == 0) {
      throw new IllegalArgumentException("This user has spent all payed days")
    } else {
      this.paidDaysCounter -= 1
      super.everydayOperation(userActions)
    }
  }

}
