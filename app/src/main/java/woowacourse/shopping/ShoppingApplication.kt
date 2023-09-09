package woowacourse.shopping

import android.app.Application
import woowacourse.shopping.data.DefaultCartRepository
import woowacourse.shopping.data.DefaultProductRepository
import woowacourse.shopping.data.ShoppingDatabase
import woowacourse.shopping.di.Dependency
import woowacourse.shopping.di.DependencyInjectionContainer
import woowacourse.shopping.di.DependencyInjector
import woowacourse.shopping.model.CartRepository
import woowacourse.shopping.model.ProductRepository

class ShoppingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupDependencyInjector()
    }

    private fun setupDependencyInjector() {
        val container = DependencyInjectionContainer(
            listOf(
                Dependency<CartRepository>(
                    DefaultCartRepository(
                        ShoppingDatabase.getDatabase(this).cartProductDao()
                    )
                ),
                Dependency<ProductRepository>(DefaultProductRepository())
            )
        )

        injector = DependencyInjector(container)
    }

    companion object {

        lateinit var injector: DependencyInjector
    }
}
