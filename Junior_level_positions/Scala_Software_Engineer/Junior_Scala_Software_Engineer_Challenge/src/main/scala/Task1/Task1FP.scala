package Task1

class Task1FP {

  def countChars(s: String): Set[(Char, Long)] = {
    val stringChars = s.toLowerCase.toList // convert to lowercase string and split it into characters
    val uniqueChars = stringChars.toSet // convert list of characters into set. On this way we extract only unique characters
    uniqueChars.map((c: Char) => (c, stringChars.count(_ == c))) /* iterate through unique characters and count how often some character
                                                                 appear in a list of characters */
  }
}
