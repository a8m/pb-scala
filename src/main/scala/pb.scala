import com.github.nscala_time.time.Imports._
import jline.{TerminalFactory}

object Units extends Enumeration {
  type Units = Value
  val Default, Bytes = Value
}
import Units._

object ProgressBar {
  private val Format = "[=>-]"

  def kbFmt(n: Double): String = {
    var kb = 1024
    n match {
      case x if x >= Math.pow(kb, 4) => "%.2f TB".format(x / Math.pow(kb, 4))
      case x if x >= Math.pow(kb, 3) => "%.2f GB".format(x / Math.pow(kb, 3))
      case x if x >= Math.pow(kb, 2) => "%.2f MB".format(x / Math.pow(kb, 2))
      case x if x >= kb => "%.2f KB".format(x / kb)
      case _ => "%.0f B".format(n)
    }
  }
}

class ProgressBar(_total: Int) {
  private val total = _total
  private var current = 0
  private var startTime = DateTime.now
  private var units = Units.Default
  private var barStart, barCurrent, barCurrentN, barRemain, barEnd = ""
  var isFinish = false
  var showBar, showSpeed, showPercent, showCounter, showTimeLeft = true

  format(ProgressBar.Format)

  def add(i: Int): Int = {
    current += i
    if (current <= total) draw()
    current
  }

  def +=(i: Int): Int = add(i)

  def setUnits(u: Units) {
    units = u
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
    val width = TerminalFactory.get().getWidth()
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
      suffix += (units match {
        case Default => "%.0f/s ".format(speed)
        case Bytes => "%s/s ".format(ProgressBar.kbFmt(speed))
      })
    }
    // time left box
    if (showTimeLeft) {
      val fromStart = (startTime to DateTime.now).millis.toFloat
      val left = (fromStart / current) * (total - current)
      val dur = Duration.millis(Math.ceil(left).toLong)
      if (dur.seconds > 0) {
        if (dur.seconds < 1.minutes.seconds) suffix += "%ds".format(dur.seconds)
        else suffix += "%dm".format(dur.minutes)
      }
    }
    // counter box
    if (showCounter) {
      prefix += (units match {
        case Default => "%d / %d ".format(current, total)
        case Bytes => "%s / %s ".format(ProgressBar.kbFmt(current), ProgressBar.kbFmt(total))
      })
    }
    // bar box
    if (showBar) {
      val size = width - (prefix + suffix).length - 3
      if (size > 0) {
        val curCount = Math.ceil((current.toFloat / total) * size).toInt
        val remCount = size - curCount
        base = barStart
        if (remCount > 0) {
          base += barCurrent * (curCount - 1) + barCurrentN
        } else {
          base += barCurrent * curCount
        }
        base += barRemain * remCount + barEnd
      }
    }
    // out
    var out = prefix + base + suffix
    if (out.length < width) {
      out += " " * (width - out.length)
    }
    // print
    print("\r" + out)
  }

  def finish() {
    if (current < total) add(total - current)
    println()
    isFinish = true
  }
}
