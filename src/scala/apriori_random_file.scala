package scala
import java.io.File
import scala.io.Source
import java.io.PrintWriter
import Array._
import sun.security.util.Length
object apriori_random_file {
  def main(args:Array[String])
  {
    var r = scala.util.Random
    var Char:Array[String]=Array("A","B","C","D","E","F","G","H","I",
        "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")
        var Binary:Array[Int]=Array()
    val writer = new PrintWriter(new File("Write.txt"))
    var s:String=""
    println("Enter the min_Support value from the range 0-1      e.g->0.7 ")
    s=s+Console.readLine()
    s=s+"\n"
    println("Enter the min_confidence value from the range 0-1      e.g->0.7 ")
    s=s+Console.readLine()
    s=s+"\n"
    println("Enter the number of transactions : ")
    var n:Int=Console.readInt()
    for(i <-0 to n-1)
    {
    for(j <-0 to 25)
    {
       var bit=r.nextInt(2)
      if(bit==1)
      {
        s=s+Char(j)+","
      }
    }
    s=s+"\n"
    }
    writer.write(s)
    writer.close
    var RawTriplets = Source.fromFile("write.txt").getLines.toList    // Get file from directory and store in String List
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