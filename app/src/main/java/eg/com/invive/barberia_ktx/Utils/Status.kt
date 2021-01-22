package eg.com.invive.barberia_ktx.Utils

sealed  class Status<out T> {

    data class Success<T>(val info:T): Status<T>()
    class Loading<T>(): Status<T>()
    class Failed<T>(val message:String): Status<T>()

}