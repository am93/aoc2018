import java.io.File
import java.io.InputStream
import kotlin.math.max

private var points: MutableList<Pair<Int,Int>> = mutableListOf()
private var velocs: MutableList<Pair<Int,Int>> = mutableListOf()


fun main(args: Array<String>)
{
    // read file
    val inputStream: InputStream = File("inputs/day10.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it)} }

    // part 1
    lineList.forEach{preprocess(it)}
    for(i in 1..10100)
        doMoveAndPrint()
}

private fun preprocess(crrVal: String)
{
    val splitted = crrVal.split('x' )
    val point = splitted[0].trim('<','>').split(',').map { it -> it.trim().toInt() }
    val velocity = splitted[1].trim('<','>').split(',').map { it -> it.trim().toInt() }
    points.add(Pair(point.first(), point.last()))
    velocs.add(Pair(velocity.first(), velocity.last()))
}

private fun doMoveAndPrint()
{
    points = points.zip(velocs).map { it -> Pair(it.first.first + it.second.first, it.first.second + it.second.second)}.toMutableList()
    val minX = points.minBy { it -> it.first }!!.first
    val maxX = points.maxBy { it -> it.first }!!.first
    val minY = points.minBy { it -> it.second }!!.second
    val maxY = points.maxBy { it -> it.second }!!.second

    if(Math.abs(Math.abs(minX) - Math.abs(maxX)) > 70) return
    if(Math.abs(Math.abs(minY) - Math.abs(maxY)) > 70) return


    for( y in minY..maxY)
    {
        for( x in minX..maxX)
        {
            if(points.contains(Pair(x,y)))
            {
                print('#')
            }
            else
            {
                print('.')
            }
        }
        println()
    }

    println()
}


