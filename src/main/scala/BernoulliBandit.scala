import scala.util.Random

class BernoulliBandit(val probabilityArms:Array[Double]) {

  def draw(i:Int): Double ={
    require( i < probabilityArms.length )
    if(Random.nextDouble() > probabilityArms(i))
      0.0
    else
      1.0
  }
}

object BernoulliBandit {

  def apply(prob:Double*): BernoulliBandit ={
    new BernoulliBandit(prob.toArray)
  }

}
