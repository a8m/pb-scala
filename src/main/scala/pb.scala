import com.github.nscala_time.time.Imports._

object ProgressBar {
  private val Format = "[=>-]"
}

class ProgressBar(_total: Int) {
  private val total = _total
  private var current = 0
  private var startTime = DateTime.now
  private var barStart, barCurrent, barCurrentN, barRemain, barEnd = ""
  var isFinish = false
  var showBar, showSpeed, showPercent, showCounter, showTimeLeft = true

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

  def draw() {
    var prefix, base, suffix = ""
    // percent box
    if (showPercent) {
      var percent = current.toFloat / (total.toFloat / 100)
      suffix += "%.2f %% ".format(percent)
    }
    // speed box
    if (showSpeed) {
      
    }
  }
}
