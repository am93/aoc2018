import java.io.File
import java.io.InputStream


fun main(args: Array<String>) {

    // read file
    val inputStream: InputStream = File("inputs/day05.txt").inputStream()
    val polymer = inputStream.bufferedReader().readLine().trim()

    // part 1
    val part1 = doReduction(polymer)
    println("Part1: "+part1.length)

    // part 2
    var bestLength = polymer.length
    for(x in 'a' .. 'z')
    {
        var tmp = polymer
        tmp = tmp.filterNot { it -> it == x || it == x.toUpperCase() }
        val tmpLen = doReduction(tmp).length
        if(tmpLen < bestLength)
            bestLength = tmpLen
    }
    println("Part2: "+bestLength)


}

private fun doReduction(input: String):String
{
    var polymer = input

    do {
        var count = 0
        var removed = false
        for((x1,x2) in polymer.zip(polymer.substring(1)))
        {
            if(x1 != x2 && x1.toUpperCase() == x2.toUpperCase())
            {
                polymer = polymer.removeRange(count,count+2)
                removed = true
                break
            }
            count++
        }
    }while (removed)

    return polymer
}