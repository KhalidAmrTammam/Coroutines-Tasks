package com.iti.java.day1.Dependencies

/*class ServiceLocatorImpl(private val context: Context) : ServiceLocator {
    override fun <T> getDependency(dependency: Dependency): T {
        return when (dependency) {
            Dependency.PRODUCT_DAO -> ProductDatabase.getInstance(context).productDao() as T
            Dependency.PRODUCT_SERVICE ->RetrofitHelper.api as T
            Dependency.LOCAL_SOURCE -> LocalDataSource(this) as T
            Dependency.REMOTE_SOURCE -> RemoteDataSource(this) as T
            Dependency.REPOSITORY -> ProductRepository(this) as T
            Dependency.VIEWMODEL_FACTORY -> ProductViewModelFactory(this) as T
        }
    }
}*/
