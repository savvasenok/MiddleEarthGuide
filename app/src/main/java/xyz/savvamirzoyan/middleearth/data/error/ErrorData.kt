package xyz.savvamirzoyan.middleearth.data.error

sealed class ErrorData : Error() {
    class ApiError : ErrorData()
    class OtherError : ErrorData()
}
