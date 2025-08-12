object ReverseString {
  def reverse(str: String): String = {
    str.split("").reverse.mkString
  }
}
