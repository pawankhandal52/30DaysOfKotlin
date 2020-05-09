package example.myapp

import java.lang.Math.PI

open class Aquarium(open var length: Int = 100, open val width: Int = 20, open var height: Int = 40) {
    init {
        println("aquarium initializing")
    }
   /* init {
        // 1 liter = 1000 cm^3
        println("Volume: ${width * length * height / 1000} l")
    }*/
   open var volume: Int
        get() = width * height * length
       set(value) {
           height = (value * 1000) / (width * length)
       }

    open val shape = "rectangle"

    open var water: Double = 0.0
        get() = volume * 0.9

    constructor(numberOfFish: Int) : this() {
        // 2,000 cm^3 per fish + extra room so water doesn't spill
        val tank = numberOfFish * 2000 * 1.1
        height = (tank / (length * width)).toInt()

    }
    fun printSize() {
        println(shape)

        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
        println("Volume: $volume l")

    }
}
class TowerTank(override var height: Int,var diameter:Int): Aquarium(height = height,length = diameter,width = diameter){
    override var volume: Int
        // ellipse area = π * r1 * r2
        get() = (width/2 * length/2 * height / 1000 * PI).toInt()
        set(value) {
            height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
        }
    override var water = volume * 0.8

}