package algorithm

class UCB1(var counts:Array[Int],var values:Array[Double]) extends BanditAlgorithm {


  def initialize(n_arms:Int): Unit = {
    counts = new Array[Int](n_arms)
    values = new Array[Double](n_arms)
  }

  def selectArm():Int = {
    val zeroIndex = counts.indexOf(0)

    zeroIndex match {
      case index if index >=0 => index
      case _ =>
        val totalCount = math.sqrt( 2*math.log(counts.sum))
        var maxIndex = 0
        var maxValue = 0.0
        var arm =0
        values.foreach{ value =>
          val bonus = totalCount/counts(arm)
          val newValue = bonus + values(arm)
          if( newValue> maxValue){
            maxIndex = arm
            maxValue = newValue
          }
          arm +=1
        }
        maxIndex
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
