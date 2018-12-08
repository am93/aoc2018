import java.io.File
import java.io.InputStream
import java.util.*

private val relations:  MutableMap<String, MutableList<String>> = mutableMapOf()
private var sortedRelations : SortedMap<String, MutableList<String>> = sortedMapOf()
private var workers : MutableList<Int> = mutableListOf(0,0,0,0,0)
private var load : MutableList<String> = mutableListOf("","","","","")

fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day07.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    // part 1
    lineList.forEach{doProcessing(it)} // create map
    sortedRelations = relations.toSortedMap(compareBy<String>{ relations[it]!!.size }.thenBy { it })
    println(doWalk())


}

private fun doProcessing(crrVal: String) {
    val n1 = crrVal.split(' ')[1]
    val n2 = crrVal.split(' ')[7]

    val tmp = relations.getOrDefault(n2, mutableListOf())
    tmp.add(n1)
    relations.put(n2, tmp)
    relations.put(n1, relations.getOrDefault(n1, mutableListOf()))
}

private fun doWalk() : Pair<String, Int>
{
    var solution = ""
    var time = 0

    do {
        if(workers.contains(0))
        {
            val wId = workers.indexOf(0)
            val crrNode = sortedRelations.keys.first()
            if(sortedRelations.get(crrNode)!!.size == 0)
            {
                sortedRelations.remove(crrNode)
                solution += crrNode
                load[wId] = crrNode
                workers[wId] = crrNode.hashCode() + 1
            }
        }
        for(i in 0..4)
        {
            if(workers[i] == 1)
            {
                sortedRelations.mapValues { it -> it.value.remove(load[i]) }
            }
        }
        sortedRelations = sortedRelations.toSortedMap(compareBy<String>{ relations[it]!!.size }.thenBy { it })
        workers = workers.map { it -> Math.max(it - 1, 0) }.toMutableList()
        time++

    }while (sortedRelations.keys.size > 0)

    return Pair(solution, time)
}

