import java.io.File
import java.io.InputStream

private val grid:  MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
private val claims : MutableMap<Pair<Int,Int>, MutableList<Int>> = mutableMapOf()
private val uncovered : MutableList<Int> = mutableListOf();

fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day03.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    // part 1
    lineList.forEach{doProcessing1(it)}
    println(grid.filterValues { it > 1 }.count())
    println(uncovered)
}

private fun doProcessing1(crrVal: String) {
    val splitted = crrVal.split(':')
    val gridSize = splitted[1].trim().split('x')
    val startCord = splitted[0].split(' ').last().split(',')
    val claimId = splitted[0].split('@')[0].trim('#').trim().toInt()
    uncovered.add(claimId)

    for(x in startCord[0].toInt() until (startCord[0].toInt()+gridSize[0].toInt()))
    {
        for(y in startCord[1].toInt() until (startCord[1].toInt()+gridSize[1].toInt()))
        {
            val key = Pair(x,y)
            grid[key] = grid.getOrDefault(key, 0) + 1
            val lst : MutableList<Int>  = claims.getOrDefault(key, mutableListOf())
            lst.add(claimId)
            claims[key] = lst
            if(lst.size > 1) uncovered.removeAll(lst)
        }
    }
}


