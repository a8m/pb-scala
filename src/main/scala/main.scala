package pb
import com.github.nscala_time.time.Imports._
import jline.{TerminalFactory}

object Main {
  def main(args: Array[String]) {
    var pb = new ProgressBar(100000000)
    pb.setUnits(Units.Bytes)
    for (i <- 1 to 10000) {
      pb+=10000
      Thread.sleep(1)
    }
    pb.finish()
    println("\ndone")
    /*var start = DateTime.now
    Thread.sleep(340)
    var end = DateTime.now
    println((start to end).millis)
    println(((start to end).millis.toFloat / 1.seconds.millis))
    println(1320.toFloat / ((start to end).millis.toFloat / 1.seconds.millis))
    println(1320 / ((start to end).millis.toFloat / 1.seconds.millis))*/
    // println(Period.millis(33123))
    // println(Duration.millis(Math.ceil(3923.21).toLong).seconds)
  }
}
