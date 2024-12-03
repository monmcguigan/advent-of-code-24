import scala.io.Source

object AOC extends App {
  val source = Source.fromFile("input.txt")

  // Process each line, split by spaces and convert to integers
  val data = source.getLines().map { line =>
    line.split("\\s+").map(_.toInt).toList
  }.toList

  // Close the file source
  source.close()

  private def checkOrdering(nums: List[Int]): Boolean = {
    val distinct = nums.distinct.sorted
    distinct == nums || distinct.reverse == nums
  }
//  private def checkSpacing(nums: List[Int]): Boolean = {
//    nums.sliding(2).map {
//      case List(lhs, rhs) =>
//        val diff = Math.abs(lhs - rhs)
//        val check = diff >= 1 && diff <= 3
//        if (!check) {
//          val noR = nums.filter(i => i != rhs)
//          val noL = nums.filter(i => i != lhs)
//          println(noR)
//          println(noL)
//          testIssue(noR) || testIssue(noL)
//        }
//        else check
//
//    }.reduce(_ && _)
//  }
  def checkSpacing(nums: List[Int]) = {
    nums.sliding(2).map{
      case List(lhs, rhs) =>
        val diff = Math.abs(lhs - rhs)
        diff >= 1 && diff <= 3
    }.reduce(_ && _)
  }

  val safeReports = data.map(l => checkSpacing(l) || checkOrdering(l))
  val srCount = safeReports.count(i => i)

  println(s"safe reports ${srCount}")
}