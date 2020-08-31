package Task1

import scala.annotation.tailrec

class Task1FPRecursion{

  /** Iterate through list of characters and count for often character appear  **/
  @tailrec
  private def countCharInString(s: List[Char], c: Char, acc: Long = 0): Long = {
    s match {
      case Nil => acc // if Nil - we reach the end of the list
      case h :: t => // use pattern matching for getting head and tail
        if (h == c) countCharInString(t, c, acc + 1)
        else countCharInString(t, c, acc)
    }
  }

  def countChars(s: String): Set[(Char, Long)] = {
    val stringChars = s.toLowerCase.toList
    val uniqueChars = stringChars.toSet
    uniqueChars.map((c: Char) => (c, countCharInString(stringChars, c)))
  }
}
