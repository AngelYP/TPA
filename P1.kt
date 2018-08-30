class Queue <T>(list:MutableList<T>): Iterator<T>{

    var itCounter: Int = 0

    var items:MutableList<T> = list

    fun isEmpty():Boolean = this.items.isEmpty()

    fun count():Int = this.items.count()

    override  fun toString() = this.items.toString()

    fun enqueue(element: T){
        this.items.add(element)
    }

    fun dequeue():T?{
        if (this.isEmpty()){
            return null
        } else {
            return this.items.removeAt(0)
        }
    }

    fun peek():T?{
        return this.items[0]
    }

    override fun hasNext(): Boolean {
        val hasNext = itCounter < count()

        // As soon as condition fails, reset the counter
        if (!hasNext) itCounter = 0

        return hasNext
    }

    override fun next(): T {
        if (hasNext()) {
            val topPos: Int = (count() - 1) - itCounter
            itCounter++
            return this.items[topPos]
        } else {
            throw NoSuchElementException("No such element")
        }
    }
}


fun main(args: Array<String>) {
    compraAcciones()
}

fun compraAcciones(){
    var initialValue =  mutableListOf<Int>(10)
    var queue = Queue<Int>(initialValue)
    queue.dequeue()
    var ca: Int
    var pua: Int
    var op: Int
    var gan=0
    op=0
    
    while (op != 5) {
      println("1.- Comprar acciones.\n2.- Vender acciones.\n3.- Ver ganancias actuales.\n4.- Ver cola\n5.- Salir")
      op=readLine()!!.toInt()
      
      if(op==1){
        println("Inserte cantidad de acciones a comprar...")
        ca=readLine()!!.toInt()
        println("Inserte precio unitario de acciones compradas...")
        pua=readLine()!!.toInt()
        println("Compra de "+ca+" acciones a $"+pua+" cada una.")
        while (ca > 0) {
            ca--
            queue.enqueue(pua) 
        }
      }
      if(op==2){
        println("Inserte cantidad de acciones a vender...")
        ca=readLine()!!.toInt()
        println("Inserte precio unitario de acciones vendidas...")
        pua=readLine()!!.toInt()
        println("Venta de "+ca+" acciones a $"+pua+" cada una.")
        while (ca > 0) {
            ca--
            gan+=(pua-queue.peek()!!.toInt())
            queue.dequeue(); 
        }
      }
      if(op==3)
        println("Ganancias: $"+gan+".")
      if(op==4){
        println("La cima es "+queue.peek()+".")
        println(queue)
      }
    }
    println(queue)
}