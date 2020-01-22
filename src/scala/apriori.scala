package scala

import scala.io.Source
import Array._
import sun.security.util.Length
object apriori {
  def main(args:Array[String])
  {
    var RawTriplets = Source.fromFile("data1.txt").getLines.toList    // Get file from directory and store in String List
    var support=RawTriplets(0).toDouble
    println("Support value is :"+support)
    var confidence=RawTriplets(1)
    println("Confidence value is :"+confidence)
    var Triplets:List[List[String]]=List()
    for(i <-2 to RawTriplets.length-1)      // Convert Raw Strings to Triplets
    {
      var temp= RawTriplets(i).split(",").toList
      //var triplet= List(temp(0).trim(),temp(1).trim(),temp(2).trim())
      Triplets=temp ::Triplets
    }
    Triplets.foreach(println)
    var no_of_trans=Triplets.length
    println("Total Number of transactions are :"+no_of_trans)
    support=(support*no_of_trans)
    println("Support value in terms of number of transactions is :"+support)
    var count=Triplets.flatten.groupBy(identity).map(x=>(x._1,x._2.size)).toList
    println("After first DB scan, the items with their occurances are :")
    count.foreach(println)
    var filteredsorted=count.sortBy(_._1).filter(_._2>=support)
    println("frequent 1 itemset with their occurances are")
    filteredsorted.foreach(println)
  }
}