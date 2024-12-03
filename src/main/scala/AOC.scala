import scala.io.Source
import scala.util.matching.Regex

object AOC extends App {
  val source = Source.fromFile("input.txt")
  val data = source.mkString
  val regex: Regex = """mul\((\d{1,3}),(\d{1,3})\)""".r
  val matchingMuls = regex.findAllIn(data).toList
  val total = matchingMuls.map {
    str =>
      regex.unapplySeq(str) match {
        case Some(a :: b :: Nil) => a.toInt * b.toInt
        case None => 0
      }
  }.sum
  println(total)
}
