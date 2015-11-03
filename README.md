# MultiArmedBandit
Introduction and implementation of the strategies(include Thompson Sampling) for multi-armed bandit Problem

# Definition
This problem is also called Exploitation/Exploration problem.

Exploitation solves the Multi-armed Bandit Problem by exploiting the arm with the highest estimated value with respect to success times and rewards of previous plays. 

Exploration solves the Multi-armed Bandit Problem by exploring any arm that does not have the highest estimated value based on previous plays.

The trade-off between exploitation and exploration is also faced in reinforcement learning.

# Application
Real Time Bidding of online advertisement
Website/app optimization problem instead of A/B Test

#Algorithm
##Epsilon-Greedy
![image](https://github.com/ReactiveCJ/MultiArmedBandit/blob/master/image/eg.png)
##SoftMax
![image](https://github.com/ReactiveCJ/MultiArmedBandit/blob/master/image/sm.png)
##Upper Confidence Bound 
![image](https://github.com/ReactiveCJ/MultiArmedBandit/blob/master/image/ucb1.png)
##Thompson Sampling
![image](https://github.com/ReactiveCJ/MultiArmedBandit/blob/master/image/tp.png)

#Simulate 
Using MCMC and Beroulli Bandit to test the different performance between four algorithms
```scala
class BernoulliBandit(val probabilityArms:Array[Double]) {
  def draw(i:Int): Double ={
    require( i < probabilityArms.length )
    if(Random.nextDouble() > probabilityArms(i))
      0.0
    else
      1.0
  }
}
```
#Experiment
We can compare the performance of accuracy, average reward and cumulative reward.

Following, I only show the chart on the performance of cumulative rewards.
###Cumulative Reward
![image](https://github.com/ReactiveCJ/MultiArmedBandit/blob/master/image/AccR.png)
#Implementation
*Build it yourself
*This project can be built with sbt 0.13. And the breeze package is used for implementing beta
distribution.
*For SBT, Add these lines to your SBT project definition:
```scala
libraryDependencies ++= Seq(
"org.scalanlp" %% "breeze" % "0.11.2"
)
```
#Reference
[Wiki of MultiArmedBandit ](https://en.wikipedia.org/wiki/Multi-armed_bandit)

*Bandit Algorithms for Website Optimization* by John Myles White

*Analysis of Thompson Sampling for the Multi-armed Bandit Problem* by Shipra and Navin

*An Information-Theoretic Analysis of Thompson Sampling* by Daniel And Benjamin
#To Do
* Contextural Bandit
* Stochastic Bandit
* implementing on spark to support parallel and online learning
