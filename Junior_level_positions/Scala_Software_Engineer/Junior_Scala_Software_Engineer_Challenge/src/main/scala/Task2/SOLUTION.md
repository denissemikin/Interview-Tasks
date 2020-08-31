Program contains 3 classes:
1) `BaseUser` which is abstract class and contains all logic
2) `FreeUser` which implement logic for Free users
3) `PaidUser` which implement logic for Paid users

# `BaseUser`
This file contains abstract class `BaseUser` and its companion object.
Class and its companion object implements default logic for all operations:
- evaluate actions per day
- evaluate some action at the end of the day
- check username using regexp
- check if user with such username has already exists
- calculate current user's level and experience.

I moved some fields into companions objects because these values are const and 
could be shared through all class objects. Also, I  moved user register to it.

# `FreeUser`
This file contains `FreeUser` class which implement Free user strategy. It overrides `everydayOperation`
method and add to all objects `userActionsLimitPerDay` field.

# `PaidUser`
This file contains `PaidUser` class which implement Paid user strategy. It overrides `everydayOperation`
method and add to all objects `paidDaysCounter` field.

# One remark
I am not implement  separate entity for "system" with its "days". I could implement it
if needed. Now the "cron" functions evaluate by classes itself. I understand that my kind
of implementation break "single responsibility" principle of SOLID, by for implement it more
"correctly" I need more information about the purpose of this test task.

