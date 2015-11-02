package pb
import org.scalatest._

trait MockOutput extends Output {
  var messages: Seq[String] = Seq()

  override def print(s: String) = messages = messages :+ s
}

class ProgressBarTest extends FlatSpec with Matchers {

  "Call add()" should "increment `current` in n" in {
    var pb = new ProgressBar(100) with MockOutput
    pb.add(10)
    pb.current should be (10)
    pb.messages.last should (
      startWith ("\r10 / 100") and
      endWith regex "10.00 % (\\d+)/s (\\d+)s *".r)
  }

  "Using += operator" should "increment `current` in n" in {
    var pb = new ProgressBar(100) with MockOutput
    pb+= 10
    pb.current should be (10)
  }

  "Call finish" should "set `current` to `total`" in {
    var pb = new ProgressBar(1) with MockOutput
    pb.finish()
    pb.current should be (pb.total)
    pb.isFinish should be (true)
    pb.messages.size should be (1)
  }
}
