import java.io.File
import java.io.InputStream

var sum = 0
val numbers:  MutableMap<Int, Int> = mutableMapOf()
var firstFound : Boolean = false

fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day01.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    numbers.put(0,0)

    // part 1
    lineList.forEach{doProcessing(it)}
    println(sum)

    // part 2
    while (!firstFound)
    {
        lineList.forEach{doProcessing(it)}
    }
}

fun doProcessing(crrVal: String) {
    sum += crrVal.toInt()
    if(!firstFound && numbers.containsKey(sum))
    {
        println("First duplicate: "+sum)
        firstFound = true
    }
    numbers.put(sum,0)
}
