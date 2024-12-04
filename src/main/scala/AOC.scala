import scala.io.Source
import scala.util.matching.Regex

object AOC extends App {
  val source = Source.fromFile("input.txt")
  val data = source.mkString
  val regex: Regex = """mul\((\d{1,3}),(\d{1,3})\)""".r
  val regexDo = """do\(\).*do\(\)""".r
  val regexDont = """don't\(\).*""".r

//  val check = println(regexDo.findAllIn(data).toList)
  val betweenDos = regexDo.findAllIn(data).mkString
  println(betweenDos)
  val removedDont = regexDont.findAllIn(betweenDos).mkString
  println(removedDont)
  val muls = regex.findAllIn(betweenDos).toList
//  println(muls)
  val total = muls.map {
    str =>
      regex.unapplySeq(str) match {
        case Some(a :: b :: Nil) => a.toInt * b.toInt
        case None => 0
      }
  }.sum
  println(total)
}
