package example.myapp

/*abstract class AquariumFish {
    abstract val color: String

}*/

class Shark: FishColor,FishAction {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

class Plecostomus(fishColor: FishColor = GoldColor):  FishAction,
        FishColor by fishColor  {
    override fun eat() {
        println("eat algae")
    }
}

interface FishAction  {
    fun eat()
}

interface FishColor {
    val color: String
}

object GoldColor : FishColor {
    override val color = "gold"
}