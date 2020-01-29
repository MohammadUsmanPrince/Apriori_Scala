package scala
import java.io.File
import scala.io.Source
import java.io.PrintWriter
import Array._
import sun.security.util.Length
object test {
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
    var count=Triplets.flatten.groupBy(identity).map(x=>(x._1,x._2.size)).toList.sortBy(_._1)
    println("After first DB scan, the items with their occurances are :")
    count.foreach(println)
   
   
    var filteredsorted=count.sortBy(_._1).filter(_._2>=support)
     println("frequent 1 itemset with their occurances are")
    filteredsorted.foreach(println)
    println("/////////////////////////////")
     //var vrt=filteredsorted.map(X=>filteredsorted.takeRight(filteredsorted.length-(filteredsorted.indexOf(X)+1))
         //.map(y=>List(X._1,y._1))).flatten
      //vrt.foreach(println)
      //var v1=filteredsorted.map(x=>filteredsorted.takeRight(filteredsorted.length-(filteredsorted.indexOf(x)+1)).map(y=>x:::y)).flatten
  // sorted.map(x=>sorted.takeRight(n)) 
   var tt=filteredsorted.combinations(2).toList//.toList.flatten
    //println("v")
 tt.foreach(println)
   var v1=tt.map(x=>Triplets.map(y=>if(x.intersect(y)==x) 1 else 0)).map(x=>x.count(_==1)).zip(tt).filter(_._1>=support).map(_._2)
  println("...................k=2 itemsets.....................")
 // var t=v1.flatten
  v1.foreach(println)
   tt= v1.map(x=>v1.dropWhile(_==x).map(y=>if(x(x.length-1)==y(1))(x:::y).distinct else x )).map(x=>x.filter(_.length==3)).flatten
   tt.foreach(println)
  v1=tt.map(x=>Triplets.map(y=>if(x.intersect(y)==x) 1 else 0)).map(x=>x.count(_==1)).zip(tt).filter(_._1>=support).map(_._2)
  println("...................k=3 itemsets.....................")
   v1.foreach(println)
  var i=2
  var k=4
  while(v1.length>1)
  {
  tt=v1.map(x=>v1.dropWhile(_==x).map(y=>if(x.take(i)==y.take(i)) (x.take(i):::y.take(i)).distinct else x)).map(x=>x.filter(_.length==k)).flatten  
    k=k+1
    i=i+1
    v1=tt.map(x=>Triplets.map(y=>if(x.intersect(y)==x) 1 else 0)).map(x=>x.count(_==1)).zip(tt).filter(_._1>=support).map(_._2)
  println("...................k="+k+" itemsets.....................")
   v1.foreach(println)
  }
  }
}