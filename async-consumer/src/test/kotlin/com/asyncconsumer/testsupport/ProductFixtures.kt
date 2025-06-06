package com.asyncconsumer.testsupport

import com.appmattus.kotlinfixture.kotlinFixture
import com.rds.product.Product

object ProductFixtures {
    private val fixture = kotlinFixture()

    fun createProduct() = fixture<Product> {
        property(Product::name) { "테스트 상품" }
    }

}