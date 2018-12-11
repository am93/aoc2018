

private var grid: MutableMap<Pair<Int,Int>, Int> = mutableMapOf()
private var memo: MutableMap<Pair<Pair<Int,Int>,Int>, Int> = mutableMapOf()
private var maxPower: Int = Int.MIN_VALUE
private var bestCord: Pair<Int,Int> = Pair(-1,-1)

private var extPower: Int = Int.MIN_VALUE
private var bestSize: Int = -1
private var extCord: Pair<Int,Int> = Pair(-1,-1)



fun main(args: Array<String>)
{
    // part 1
    preprocess(4842)
    iterateGrid(1)
    iterateGrid(2)
    iterateGrid(3)
    println(bestCord)
    memo.clear()

    // part 2
    for(size in 1..300)
    {
        println("Size "+size.toString())
        iterateGrid(size)
        if(maxPower > extPower)
        {
            extPower = maxPower
            bestSize = size
            extCord = bestCord
        }
    }
    println(bestSize)
    println(extCord)
}

private fun preprocess(serialNum: Int)
{
    for(x in 1..300)
    {
        for(y in 1..300)
        {
            val cords = Pair(x,y)
            val rackId = x + 10
            var powerLvl = rackId * y
            powerLvl += serialNum
            powerLvl *= rackId
            powerLvl = (powerLvl / 100) % 10
            powerLvl -= 5
            grid.put(cords, powerLvl)
        }
    }
}

private fun iterateGrid(size: Int)
{
    maxPower = Int.MIN_VALUE

    for(x in 1..(300-size+1))
    {
        for(y in 1..(300-size+1))
        {
            val crrPower = computePower(Pair(x,y), size)

            if(crrPower > maxPower)
            {
                maxPower = crrPower
                bestCord = Pair(x,y)
            }
        }
    }
}

private fun computePower(cord:Pair<Int,Int>, size:Int) : Int
{
    var crrPower = 0

    crrPower += memo.getOrDefault(Pair(cord,size-1),0)!!

    for(x1 in cord.first..cord.first+size-1)
    {
        crrPower += grid[Pair(x1, cord.second+size-1)]!!
    }
    for(y1 in cord.second..cord.second+size-1)
    {
        crrPower += grid[Pair(cord.first+size-1, y1)]!!
    }

    memo.put(Pair(cord,size), crrPower)

    return crrPower
}




