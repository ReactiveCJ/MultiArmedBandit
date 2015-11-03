package algorithm

import scala.util.Random
import util._

class EpsilonGreedy(val epsilon:Double,var counts:Array[Int],var values:ArrayMax[Double]) extends BanditAlgorithm {


  def initialize(n_arms:Int): Unit = {
    counts = new Array[Int](n_arms)
    values = ArrayMax.initialize(n_arms,0.0)
  }

  def selectArm():Int = {
    if(Random.nextDouble() > epsilon){
      values.maxIndex
    }else{
        Random.nextInt(values.length)
    }
  }

  def update(chosenArm:Int,reward:Double): Unit ={
    counts(chosenArm) += 1
    val n = counts(chosenArm)
    val value = values(chosenArm)
    val newValue = value * (n-1) / n + reward/n
    values(chosenArm) = newValue
  }

}
