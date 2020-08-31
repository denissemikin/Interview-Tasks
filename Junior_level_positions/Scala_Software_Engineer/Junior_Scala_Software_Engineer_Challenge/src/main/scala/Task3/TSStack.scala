package Task3

class TSStack[T]{

  /**
   * This class implement simple stack. I pick List as storing structure because add and delete operations on this
   * data structure could be done in O(1) operations.
   * Also i added `synchronized` block for all method which allow to work with list concurrently.
   */

  private[this] var l = List[T]() //underlying list which will store all elems

  def push(elem: T): Unit = this.synchronized {
    l = elem :: l // push element to stack is like add new element into list
  }

  def pop(): Option[T] = this.synchronized{
    // pop elements is like getting head element from list
    if(size > 0) {
      val headElem = l.headOption // Or use our method `topElem`
      l = l.tail
      headElem
    } else {
      None
    }
  }

  def containElem(elem: T): Boolean = this.synchronized(l.contains(elem))

  def size: Long = this.synchronized(l.length)

  def topElem: Option[T] = this.synchronized(l.headOption)

  override def toString: String = this.synchronized(l.toString())
}
