package com.iti.java.day1.Dependencies

interface ServiceLocator {
    fun <T> getDependency(dependency: Dependency): T
}

enum class Dependency {
    PRODUCT_DAO,
    PRODUCT_SERVICE,
    LOCAL_SOURCE,
    REMOTE_SOURCE,
    REPOSITORY,
    VIEWMODEL_FACTORY
}