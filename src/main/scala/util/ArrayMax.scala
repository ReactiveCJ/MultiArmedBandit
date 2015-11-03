package util

import breeze.stats.distributions.Beta

import scala.reflect.ClassTag


@SerialVersionUID(1L)
class ArrayMax[T:Ordering](var data:Array[T],private var maxPos:Int) extends Serializable {

  def apply(i:Int):T = {
    data.apply(i)
  }

  def update(i:Int,t:T) = {
    import Ordered._
    if(i!=maxPos ) {
      val maxValue = data(maxPos)
      data(i) = t
      if (t > maxValue) {
        maxPos = i
      }
    }else if (i==maxPos && t >= data(maxPos)){
      data(i) = t
    } else {
      data(i) = t
      var pos = 0
      var maxValue = t
      data.foreach{
        v =>
          if(v > maxValue ){
            maxValue = v
            maxPos = pos
          }
          pos += 1
      }
    }
  }

  def maxIndex = maxPos
  def length = data.length

}

object ArrayMax {


  def empty[T: ClassTag:Ordering]: ArrayMax[T] = new ArrayMax[T](Array.empty[T],0)

  def initialize[T: ClassTag:Ordering](size:Int,v:T): ArrayMax[T] = {
        new ArrayMax[T](Array.fill(size)(v), 0)
  }

  def apply[T: ClassTag:Ordering](xs: T*): ArrayMax[T] = {
    import Ordered._
    val array = new Array[T](xs.length)
    var i = 0
    var maxIndex = 0
    var maxValue = xs.head
    for (x <- xs.iterator) {
      if(maxValue < x)
      {
        maxIndex = i
        maxValue = x
      }
      array(i) = x
      i += 1
    }
    new ArrayMax[T](array,maxIndex)
  }
}
object test extends  App {

  val x = new Beta(1,1)
  val mean = x.mean
  val mode = x.mode
  val a = (mean - 2*mean*mode) / (mean -mode)
  val b = (1 -mean) * a / mean
  println(a+"\t"+b)
}