import breeze.stats.distributions.Beta

import scala.util.Random

package object util {

  def categoricalDraw(proSeq:Seq[Double]):Int = {

    val r = Random.nextDouble()

    var index = 0
    var cum_prob = proSeq(index)

    while(cum_prob < r ) {
      index += 1
      cum_prob += proSeq(index)
    }
    index
  }

  implicit val orderingBeta = new Ordering[Beta]{
    def compare(b1:Beta,b2:Beta):Int={
      val u1 = b1.sample()
      val u2 = b2.sample()
      if(u1 < u2)
        -1
      else if(u1 > u2)
        1
      else
        0
    }
  }

}
