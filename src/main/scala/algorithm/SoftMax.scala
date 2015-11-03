package algorithm

import util._

class SoftMax(val annealFunction:Function[Any,Double],var counts:Array[Int],var values:Array[Double])  extends BanditAlgorithm {

  def initialize(n_arms:Int): Unit = {
    counts = new Array[Int](n_arms)
    values = new Array[Double](n_arms)
  }

  def selectArm():Int = {

    val temperature:Double = annealFunction(counts.sum + 1)
    val sum = values.foldLeft(0.0){
      (A,B) =>
        A + math.exp(B/temperature)
    }
    categoricalDraw(values.map{A =>math.exp(A/temperature)/sum
    })
  }

  def update(chosenArm:Int,reward:Double): Unit ={
    counts(chosenArm) += 1
    val n = counts(chosenArm)
    val value = values(chosenArm)
    val newValue = value * (n-1) / n + reward/n
    values(chosenArm) = newValue
  }

}