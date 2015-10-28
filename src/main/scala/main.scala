import com.github.nscala_time.time.Imports._
import jline.{TerminalFactory}

object Main {
  def main(args: Array[String]) {
    println(TerminalFactory.get().getWidth())
    var pb = new ProgressBar(120)
    for (i <- 1 to 120) {
      pb.add(1)
      Thread.sleep(100)
    }
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
