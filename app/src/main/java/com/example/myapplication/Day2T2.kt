import com.example.myapplication.readFile

fun main()
{
    val lines = readFile("C:\\Users\\38099\\AndroidStudioProjects\\MyApplication\\app\\src\\com.example.myapplication.main\\java\\com\\example\\myapplication\\inputD2.txt")


    var counterOfSafe = 0
    for(line in lines)
    {
        val splitter2 = line.split(" ")
        for (k in 0.. splitter2.size - 1)
        {
            val splitter: MutableList<String> = mutableListOf()

            for(q in 0..splitter2.size - 2)
            {
                if (q < k) {
                    splitter.add(splitter2[q])}
                if (q >= k) {
                    splitter.add(splitter2[q + 1])
                }

            }

            val counter = checkLine(splitter)
            if (counter && (splitter2.size - splitter2.toSet().size <= 1))
            {
                counterOfSafe += 1
                break
            }
        }
    }

    print(counterOfSafe)
}

fun checkLine(splitter : List<String>) : Boolean
{
    var counter = 0

    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    val list3 = mutableListOf<Int>()

    var i = 0
    while (i < splitter.size )
    {
        list1.add(splitter[i].toInt())
        list2.add(splitter[i].toInt())
        list3.add(splitter[i].toInt())
        i += 1
    }
    var j = 0

    list1.sort()
    list2.sortDescending()
    if (list1 == list3 || list2 == list3)
    {
        var isSequred: Boolean = true
        while (j < list3.size - 1)
        {
            if (Math.abs(list3[j] - list3[j + 1]) in 1..3)
            {
                j += 1
            }
            else
            {
                isSequred = false
                break
            }
        }
        if (isSequred)
        {
            counter += 1
        }
    }
    return counter == 1
}