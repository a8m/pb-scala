package pb
import org.scalatest._

class ProgressBarTest extends FlatSpec with Matchers {

  "Call add()" should "increment `current` in n" in {
    var pb = new ProgressBar(100)
    pb.add(10)
    pb.current should be (10)
  }

  "Using += operator" should "increment `current` in n" in {
    var pb = new ProgressBar(100)
    pb+= 10
    pb.current should be (10)
  }

  "Call finish" should "set `current` to `total`" in {
    var pb = new ProgressBar(1)
    pb.finish()
    pb.current should be (pb.total)
    pb.isFinish should be (true)
  }
}
