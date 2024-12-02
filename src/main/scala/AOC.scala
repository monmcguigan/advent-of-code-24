import scala.io.Source

object AOC extends App {
  val filename = "input.txt"
  var list1: List[Int] = List()
  var list2: List[Int] = List()
  for (line <- Source.fromFile(filename).getLines()) {
    val numbers = line.split("\\s+").map(_.toInt)
    if (numbers.length == 2) {
      // Add the first number to list1 and the second to list2
      list1 = numbers(0) :: list1
      list2 = numbers(1) :: list2
    }
  }

  // Reverse the lists to maintain the original order from the file
  list1 = list1.sorted
  list2 = list2.sorted

  val listDiff = (list1 zip list2).map {
    case (a, b) =>
      val diff = a - b
      val abs = Math.abs(diff)
      abs
  }.sum
  println(listDiff)
  val listSimilarity = (list1 zip list2).map {
    case (a, b) =>
      val countOfA = list2.count(_ == a)

      a * countOfA
  }.sum
  println(listSimilarity)
}