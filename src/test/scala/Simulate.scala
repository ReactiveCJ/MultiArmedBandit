import java.io.PrintWriter

import algorithm._
import breeze.stats.distributions.Beta
import util.{ArrayMax, orderingBeta}

import scala.collection.mutable.ArrayBuffer

object Simulate extends App{

  /**
   * run mcmc to simulate the algorithm
   * @param alg
   * @param bandit
   * @param numSim
   * @param horizon
   * @return
   */
  def testAlgorithm(alg:BanditAlgorithm,bandit:BernoulliBandit,numSim:Int,horizon:Int): (Array[Int],Array[Double],Array[Double]) ={

    val size = numSim * horizon
    val chosenArms = new Array[Int](size)
    val rewards = new Array[Double](size)
    val cumulativeRewards = new Array[Double](size)

    for( sim <- 0 until numSim){
      alg.initialize(bandit.probabilityArms.length)

      for(t <- 0 until horizon){
        val index = sim  * horizon + t

        val chosenArm = alg.selectArm()
        chosenArms(index) = chosenArm
        val reward = bandit.draw(chosenArm)

        alg.update(chosenArm,reward)

        rewards(index) = reward
        if(t == 0)
          cumulativeRewards(index) = reward
        else
          cumulativeRewards(index) = cumulativeRewards(index - 1) + reward
      }
    }
    (chosenArms,rewards,cumulativeRewards)
  }

  def evaluate(chosenArms:Array[Int],rewards:Array[Double],
               cumulativeRewards:Array[Double], bestIndex:Int,
               numSim:Int, horizon:Int): (Array[Double],Array[Double],Array[Double]) = {

    val accuracyPerformance = new Array[Double](horizon)
    val rewardsPerformance = new Array[Double](horizon)
    val cumulativeRewardsPerformance = new Array[Double](horizon)

    for(sim <- 0 until numSim){
      for(t <- 0 until horizon)
      {
        val index = sim  * horizon + t
        if(chosenArms(index) == bestIndex){
          accuracyPerformance(t) += 1.0/numSim
        }
        rewardsPerformance(t) += rewards(index)/numSim
        cumulativeRewardsPerformance(t) += cumulativeRewards(index)/numSim
      }
    }

    (accuracyPerformance,rewardsPerformance,cumulativeRewardsPerformance)
  }


  val means:Array[Double] = Array(0.4,0.1,0.2,0.3,0.9)
  val bestIndex = means.indexOf(means.max)
  val n_arms = means.length

  val numSim = 5000
  val horizon = 300
  val bernoulliBandit = BernoulliBandit(means:_*)

  val annealFunction:Function[Any,Double] = {
    case _ =>
      0.1
  }


  val epsilonAlg = new EpsilonGreedy(0.1,Array.empty[Int],ArrayMax.empty[Double])
  val softMaxAlg= new SoftMax(annealFunction,Array.empty[Int],Array.empty[Double])
  val ucbAlg = new UCB1(Array.empty[Int],Array.empty[Double])
  val tpAlg= new ThompsonSampling(Array.empty[Int],Array.empty[Int],ArrayMax.empty[Beta])

  epsilonAlg.initialize(n_arms)
  softMaxAlg.initialize(n_arms)
  ucbAlg.initialize(n_arms)
  tpAlg.initialize(n_arms)

  val algorithmSet = Array(epsilonAlg,softMaxAlg,ucbAlg,tpAlg)
  val resPrint = new PrintWriter("res.txt")
  val res = ArrayBuffer[Array[Double]]()
  algorithmSet.foreach {
    alg =>
      val Tuple3(chosenArms, rewards, cumulativeRewards) = testAlgorithm(alg, bernoulliBandit, numSim, horizon)
      val Tuple3(accuracyPerformance, rewardsPerformance, cumulativeRewardsPerformance) = evaluate(chosenArms, rewards, cumulativeRewards, bestIndex, numSim, horizon)
      res += cumulativeRewardsPerformance
  }


  for(i <- 0 until 300){
    val str = res.head(i) +"\t"+ res(1)(i) +"\t"+ res(2)(i)+"\t" + res(3)(i)
    resPrint.println(str)
  }
  resPrint.flush()
  resPrint.close()
}
