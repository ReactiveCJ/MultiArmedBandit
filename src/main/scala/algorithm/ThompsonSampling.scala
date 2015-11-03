package algorithm

import breeze.stats.distributions.Beta
import util.ArrayMax
import util.orderingBeta

class ThompsonSampling(var pos:Array[Int],var neg:Array[Int],var betas:ArrayMax[Beta]) extends BanditAlgorithm {

  def initialize(n_arms:Int): Unit = {

    betas = ArrayMax.initialize[Beta](n_arms,null)
    for(i <- 0 until n_arms){
      betas.data(i) = Beta.distribution(1,1)
    }
    pos = new Array[Int](n_arms)
    neg = new Array[Int](n_arms)
  }

  def selectArm():Int = {
    betas.maxIndex
  }

  def update(chosenArm:Int,reward:Double): Unit = {

    val a = pos(chosenArm) + 1
    val b = neg(chosenArm) + 1
    if (reward > 0.0) {
      betas(chosenArm) = new Beta(a+1,b)
      pos(chosenArm) += 1
    } else {
      betas(chosenArm) = new Beta(a,b+1)
      neg(chosenArm) += 1
    }


  }

}

