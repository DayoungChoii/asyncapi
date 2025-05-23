package com.asyncconsumer.product.service

import com.appmattus.kotlinfixture.kotlinFixture
import com.asyncconsumer.AsyncConsumerApplication
import com.asyncconsumer.partner.constant.PartnerCreationStatus
import com.asyncconsumer.product.constant.ProductCreationStatus
import com.asyncconsumer.product.dto.ProductCreationRequest
import com.rds.partner.Partner
import com.rds.partner.PartnerRepository
import com.rds.product.ProductRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@ActiveProfiles("test")
@SpringBootTest(classes = [AsyncConsumerApplication::class])
class ProductServiceTest (
    @Autowired private val productService: ProductService,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val partnerRepository: PartnerRepository,
) {
    private val fixture = kotlinFixture()

    @Test
    fun `Product 생성 테스트`() {
        //given
        val partner: Partner = fixture()
        val savedPartner = partnerRepository.save(partner)
        val request: ProductCreationRequest = fixture<ProductCreationRequest>() {
            property(ProductCreationRequest::partnerId) {savedPartner.id}
            property(ProductCreationRequest::price) { BigDecimal.valueOf(10000)}
        }

        //when
        val result = productService.createProduct(request)

        //then
        val savedProduct = productRepository.findById(result.data).get()

        assertThat(result.status).isEqualTo(ProductCreationStatus.SUCCESS)
        assertThat(result.data).isEqualTo(savedProduct.id)
    }
}