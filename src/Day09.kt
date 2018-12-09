import java.io.File
import java.io.InputStream

private var crrIdx : Int = 0

fun main(args: Array<String>) {

    // read file
    val numPlayers : Int = 411
    val lastMarble : Int = 71058
    var circle : MutableList<Int> = mutableListOf()
    circle.add(0)

    // part 1
    //val part1 = putMarbles(numPlayers, lastMarble, circle)
    //println("Part1: "+part1)

    // part 2
    val part2 = putMarbles(numPlayers, lastMarble*100, circle)
    println("Part2: "+part2)

}

private fun putMarbles(numPlayers: Int, lastMarble: Int, circle: MutableList<Int>):Long {
    var crrMarble = 1
    var players : MutableList<Long> = MutableList(numPlayers){0.toLong()}
    var crrPlayer : Int = 0
    while(crrMarble < lastMarble)
    {
        if(crrMarble % 23 == 0)
        {
            for(i in 1..7)
            {
                val x = circle.removeAt(circle.size-1)
                circle.add(0, x)
            }
            players.set(crrPlayer, players.get(crrPlayer) + crrMarble + circle.removeAt(circle.size - 1))
            val x = circle.removeAt(0)
            circle.add(x)
            crrMarble++
        }
        else
        {
            val x = circle.removeAt(0)
            circle.add(x)
            circle.add(crrMarble++)
            //println(crrMarble.toString()+": "+circle)
        }

        if(crrMarble % 100000 == 0)println(crrMarble)
        crrPlayer = (crrPlayer + 1) % numPlayers
    }

    return players.max()!!
}









