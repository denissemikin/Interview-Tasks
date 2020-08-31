package Task2


/**
 * Free user class. Override `everydayOperation` method for checking if user actions list contains more than 3 actions or not.
 * If it contains less than 3 action we extend it to 3.
 * @param username username
 * @param userActionsLimitPerDay how many actions user could perform per day
 */
class FreeUser(username: String, userActionsLimitPerDay: Int = 10) extends BaseUser(username) {
  require(userActionsLimitPerDay > 0, "Actions limit should be greater than 0")

  override def everydayOperation(userActions: List[UserAction]): Unit = {
    if (userActions.length > this.userActionsLimitPerDay) throw new IllegalArgumentException("This user could not perform so many actions")
    else {
      val padUserActionsTo3 = if(userActions.length < 3) userActions.padTo(3, userActions.head) else userActions
      super.everydayOperation(padUserActionsTo3)
    }
  }

}
