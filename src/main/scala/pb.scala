import com.github.nscala_time.time.Imports._

class ProgressBar(_total: Int) {
  private val total = _total
  private var current = 0
  private var startTime = DateTime.now
  private var (barStart, barCurrent, barCurrentN, barRemain, barEnd) = 
    ("", "", "", "", "")
  var (isFinish, showBar, showSpeed, showPercent, showCounter, showTimeLeft) =
    (false, true, true, true, true, true)

  def add(i: Int): Int = {
    current += i
    current
  }
}
