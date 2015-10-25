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
    val width = 80
    var prefix, base, suffix = ""
    // percent box
    if (showPercent) {
      var percent = current.toFloat / (total.toFloat / 100)
      suffix += " %.2f %% ".format(percent)
    }
    // speed box
    if (showSpeed) {
      val fromStart = (startTime to DateTime.now).millis.toFloat
      val speed = current / (fromStart / 1.seconds.millis)
      // Set bytes condition
      suffix +=  "%.0f/s ".format(speed)
    }
    // time left box
    if (showTimeLeft) {
      val fromStart = (startTime to DateTime.now).millis.toFloat
      val left = (fromStart / current) * (total - current)
      val dur = Duration.millis(Math.ceil(left).toLong)
      if (dur.seconds >= 0) {
        if (dur.seconds < 1.minutes.seconds) suffix += "%ds".format(dur.seconds)
        else suffix += "%dm".format(dur.minutes)
      }
      println(suffix)
    }
    // out
    var out = prefix + base + suffix
    if (out.length < width) {
      out += " " * (width - out.length)
    }
    // print
    //print("\r" + out)
  }
}
