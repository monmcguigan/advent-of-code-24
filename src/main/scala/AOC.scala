import scala.io.Source
import scala.util.matching.Regex

object AOC extends App {
  // we have a 140x140 square of characters
  // 140 horizontal lists - get lines
  // 140 vertical lists

  val filePath = "input.txt"

  val lines: List[String] = Source.fromFile(filePath).getLines().toList

  val characterLines: List[List[Char]] = lines.map(_.toList)

  val horizontalLines = characterLines.map(_.mkString)

  val numberOfColumns = 140
  val numberOfRows = 140

  val verticalLines: List[String] = (0 until numberOfColumns).map { col =>
    lines.map(line => line(col)) .mkString
  }.toList

  def getTopLeftToBottomRightDiagonals: List[String] = {
    val diagonals = collection.mutable.ListBuffer[String]()

    // Collect diagonals starting from each possible point in the first row and first column
    // From (0,0) to (139,139)
    for (startRow <- 0 until numberOfRows) {
      var diagonal = ""
      var r = startRow
      var c = 0
      while (r < numberOfRows && c < numberOfColumns) {
        diagonal += lines(r)(c)
        r += 1
        c += 1
      }
      diagonals += diagonal
    }

    for (startCol <- 1 until numberOfColumns) {
      var diagonal = ""
      var r = 0
      var c = startCol
      while (r < numberOfRows && c < numberOfColumns) {
        diagonal += lines(r)(c)
        r += 1
        c += 1
      }
      diagonals += diagonal
    }

    diagonals.toList
  }
  def getTopRightToBottomLeftDiagonals: List[String] = {
    val diagonals = collection.mutable.ListBuffer[String]()

    // Collect diagonals starting from each possible point in the first row and last column
    // From (0,139) to (139,0)
    for (startRow <- 0 until numberOfRows) {
      var diagonal = ""
      var r = startRow
      var c = numberOfColumns - 1
      while (r < numberOfRows && c >= 0) {
        diagonal += lines(r)(c)
        r += 1
        c -= 1
      }
      diagonals += diagonal
    }

    for (startCol <- (numberOfColumns - 2) to 0 by -1) {
      var diagonal = ""
      var r = 0
      var c = startCol
      while (r < numberOfRows && c >= 0) {
        diagonal += lines(r)(c)
        r += 1
        c -= 1
      }
      diagonals += diagonal
    }

    diagonals.toList
  }

  val topLeftToBottomRightDiagonals = getTopLeftToBottomRightDiagonals
  val topRightToBottomLeftDiagonals = getTopRightToBottomLeftDiagonals

  val searchableStrings = horizontalLines ++ verticalLines ++ topRightToBottomLeftDiagonals ++ topLeftToBottomRightDiagonals

  println(searchableStrings)
  val xmasRegex = """XMAS""".r
  val smaxRegex = """SAMX""".r

  val xmas = searchableStrings.flatMap(s => xmasRegex.findAllIn(s).toList).size
  val smax = searchableStrings.flatMap(s => smaxRegex.findAllIn(s).toList).size
  val total = xmas + smax
  println(total)
}
