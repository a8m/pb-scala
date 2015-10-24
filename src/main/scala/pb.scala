import com.github.nscala_time.time.Imports._

object ProgressBar {
  private val Format = "[=>-]"
}

class ProgressBar(_total: Int) {
  private val total = _total
  private var current = 0
  private var startTime = DateTime.now
  private var (barStart, barCurrent, barCurrentN, barRemain, barEnd) = 
    ("", "", "", "", "")
  var (isFinish, showBar, showSpeed, showPercent, showCounter, showTimeLeft) =
    (false, true, true, true, true, true)

  format(ProgressBar.Format)

  def add(i: Int): Int = {
    current += i
    current
  }

  def format(fmt: String) {
    if (fmt.length >= 5) {
      val v = fmt.split("").toList
      barStart = v(0)
      barCurrent = v(1)
      barCurrentN = v(2)
      barRemain = v(3)
      barEnd = v(4)
    }
  }
}
