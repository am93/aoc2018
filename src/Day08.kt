import java.io.File
import java.io.InputStream

private var crrIdx : Int = 0

fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day08.txt").inputStream()
    val treeData = inputStream.bufferedReader().readLine().trim().split(' ').map { x -> x.toInt() }

    // part 1
    val part1 = checkTree(treeData)
    println("Part1: "+part1)

    // part 2
    crrIdx = 0
    val part2 = checkTreeComplex(treeData)
    println("Part2: "+part2)
}

private fun checkTree(treeData: List<Int>):Int {
    val numChilds = treeData[crrIdx++]
    val numMeta = treeData[crrIdx++]
    var metaDataSum = 0

    for (x in 1..numChilds)
    {
        metaDataSum += checkTree(treeData)
    }

    for (m in 1..numMeta)
    {
        metaDataSum += treeData[crrIdx++]
    }

    return metaDataSum
}

private fun checkTreeComplex(treeData: List<Int>):Int {
    val numChilds = treeData[crrIdx++]
    val numMeta = treeData[crrIdx++]
    val childSum : MutableList<Int> = mutableListOf()
    var metaDataSum = 0

    // get child data
    for (x in 1..numChilds)
    {
        childSum.add(checkTreeComplex(treeData))
    }


    for (m in 1..numMeta)
    {
        // normal sum as before
        if(numChilds == 0)
        {
            metaDataSum += treeData[crrIdx++]
        }
        // if we have childs, than metadata fields are pointers
        else
        {
            val cIdx = treeData[crrIdx++]
            if(cIdx > childSum.size) continue
            metaDataSum += childSum[cIdx-1]
        }
    }
    return metaDataSum
}








