package Task1

class Task1Imper {

  private def countCharInString(s: List[Char], c: Char): Long = {
      var counter = 0L
      for(nextChar <- s) {
        if (nextChar.equals(c)) counter += 1
      }
      counter
  }

  def countChars(s: String): Set[(Char, Long)] = {
    val stringChars = s.toLowerCase.toList
    val uniqueChars = stringChars.toSet
    uniqueChars.map((c: Char) => (c, countCharInString(stringChars, c)))
  }
}
