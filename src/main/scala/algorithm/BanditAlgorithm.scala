package algorithm

trait BanditAlgorithm {

  def selectArm():Int
  def update(chosenArm:Int,reward:Double)
  def initialize(n_arms:Int)

}
