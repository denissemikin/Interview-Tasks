import Task1.{Task1FP, Task1FPRecursion, Task1Imper}
import org.scalatest._
import flatspec._
import matchers._

class Task1Spec extends AnyFlatSpec with should.Matchers {
  "Task 1" should "return correct frequency of occurrence of each character of English alphabet in this string" in {
    val task1 = new Task1FP()
    task1.countChars("qweQWEqWE") should be (Set(('q', 3), ('w', 3), ('e', 3)))
    task1.countChars("qweQWEqWEee") should be (Set(('q', 3), ('w', 3), ('e', 5)))
    task1.countChars("zzzzzzzzz") should be (Set(('z', 9)))
    task1.countChars("AaA") should be (Set(('a', 3)))
  }

  it should "equals with all another implementations" in {
    val task1FP = new Task1FP()
    val task1FPRecursion = new Task1FPRecursion()
    val task1Imper = new Task1Imper()
    val s1 = "qweQWEqWE"
    assert(task1FP.countChars(s1) == task1FPRecursion.countChars(s1))
    assert(task1FP.countChars(s1) == task1Imper.countChars(s1))
    val s2 = "AaA"
    assert(task1FP.countChars(s2) == task1FPRecursion.countChars(s2))
    assert(task1FP.countChars(s2) == task1Imper.countChars(s2))
  }

}
