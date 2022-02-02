package xyz.savvamirzoyan.middleearth.domain.error

import xyz.savvamirzoyan.middleearth.core.Error

sealed class ErrorDomain : Error() {
    class Network : ErrorDomain()
    class Other : ErrorDomain()
}