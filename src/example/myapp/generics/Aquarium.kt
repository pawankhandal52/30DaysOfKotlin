package example.myapp.generics

open class WaterSupply(var needsProcessing:Boolean)

class TapWater():WaterSupply(true){
    fun addChemicalCleaners(){
        needsProcessing = false;
    }
}

class FishStoreWater : WaterSupply(false)

class LakeWater : WaterSupply(true) {
    fun filter() {
        needsProcessing = false
    }
}

class Aquarium<out T: WaterSupply>(val waterSupply: T) {
    fun addWater() {
        //check(!waterSupply.needsProcessing) { "water supply needs processing first" }
        println("adding water from $waterSupply")
    }

    fun addWater(cleaner: Cleaner<T>) {
        if (waterSupply.needsProcessing) {
            cleaner.clean(waterSupply)
        }
        println("water added")
    }

inline fun <reified R: WaterSupply> hasWaterSupplyOfType() = waterSupply is R

}

/*fun isWaterClean(aquarium: Aquarium<WaterSupply>) {
    println("aquarium water is clean: ${aquarium.waterSupply.needsProcessing}")
}*/
//Genric function
fun <T: WaterSupply> isWaterClean(aquarium: Aquarium<T>) {
    println("aquarium water is clean: ${!aquarium.waterSupply.needsProcessing}")
}
interface Cleaner<in T: WaterSupply> {
    fun clean(waterSupply: T)
}

class TapWaterCleaner : Cleaner<TapWater> {
    override fun clean(waterSupply: TapWater) =   waterSupply.addChemicalCleaners()
}
fun genericsExample() {
    val aquarium = Aquarium(TapWater())
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")
    aquarium.waterSupply.addChemicalCleaners()
    println("water needs processing: ${aquarium.waterSupply.needsProcessing}")

    val aquarium4 = Aquarium(LakeWater())
    aquarium4.addWater()
    addItemTo(aquarium4)

    val cleaner = TapWaterCleaner()
    val aquarium5 = Aquarium(TapWater())
    aquarium5.addWater(cleaner)
    isWaterClean<TapWater>(aquarium5)
    println(aquarium5.hasWaterSupplyOfType<TapWater>())
    println(aquarium5.waterSupply.isOfType<TapWater>())
    println(aquarium5.hasWaterSupplyOfType<TapWater>())


}
fun addItemTo(aquarium: Aquarium<WaterSupply>) = println("item added")
inline fun <reified T: WaterSupply> WaterSupply.isOfType() = this is T
inline fun <reified R: WaterSupply> Aquarium<*>.hasWaterSupplyOfType() = waterSupply is R

fun main() {
    genericsExample()
}