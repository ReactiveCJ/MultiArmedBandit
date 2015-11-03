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

#Experiment
###Cumulative Reward
![image](https://github.com/ReactiveCJ/MultiArmedBandit/blob/master/image/AccR.png)

