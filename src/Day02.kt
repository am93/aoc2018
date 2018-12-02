import java.io.File
import java.io.InputStream

private var sum2 = 0
private var sum3 = 0
private val numbers:  MutableMap<Char, Int> = mutableMapOf()
private var firstFound : Boolean = false

fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day02.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    // part 1
    lineList.forEach{doProcessing1(it)}
    println(sum2 * sum3)

    // part 2
    lineList.forEach{doProcessing2(it, lineList)}

}

private fun doProcessing1(crrVal: String) {
    numbers.clear()
    crrVal.forEach {
        numbers[it] = numbers.getOrDefault(it, 0) + 1
    }
    if (numbers.containsValue(2))sum2++
    if (numbers.containsValue(3))sum3++
}

private fun doProcessing2(crrVal: String, lineList: MutableList<String>) {
    if (firstFound) return
    for (s in lineList)
    {
        if(s.zip(crrVal).filter { it.first != it.second }.count() == 1)
        {
            println(s.zip(crrVal).filter { it.first == it.second }.map { it.first }.joinToString(""))
            firstFound = true
            return
        }
    }
}
