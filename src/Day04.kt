import java.io.File
import java.io.InputStream

private val guards : MutableMap<Int,MutableMap<Int,Int>> = mutableMapOf()
private val times : MutableMap<Int,Int> = mutableMapOf()
private var crrGuard : Int = -1
private var sleepTime : Int = -1

fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day04.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    // part 1
    lineList.sort()
    lineList.forEach{doProcessing1(it)}
    val maxG  = times.maxBy { it -> it.value }!!.key
    val maxMin = guards.getOrDefault(maxG, mutableMapOf()).maxBy { it -> it.value }!!.key
    println(maxG * maxMin)

    // ugly part 2
    val maxMins  = guards.mapValues { it.value.maxBy{ x -> x.value } }
    println(maxMins)
}

private fun doProcessing1(crrVal: String) {
    if(crrVal.contains('#'))
    {
        crrGuard = crrVal.split('#')[1].split(' ')[0].toInt()
        return
    }
    val mins = crrVal.split(' ')[1].trim(']').split(':')[1].toInt()
    if(crrVal.contains("asleep"))
    {
        sleepTime = mins
        return
    }
    val sleepy : MutableMap<Int,Int> = guards.getOrDefault(crrGuard, mutableMapOf())
    times.put(crrGuard, times.getOrDefault(crrGuard, 0) + (mins-sleepTime))

    for(m in sleepTime .. mins)
    {
        sleepy.put(m, sleepy.getOrDefault(m,0) + 1)
    }
    guards.put(crrGuard,sleepy)
}


