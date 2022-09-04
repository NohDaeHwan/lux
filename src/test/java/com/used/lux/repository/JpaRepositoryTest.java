package com.used.lux.repository;

import com.used.lux.config.JpaConfig;
import com.used.lux.domain.Image;
import com.used.lux.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final UsedluxRepository usedluxRepository;

    private final ImageRepository imageRepository;

    JpaRepositoryTest(
            @Autowired UsedluxRepository usedluxRepository,
            @Autowired ImageRepository imageRepository
    ) {
        this.usedluxRepository = usedluxRepository;
        this.imageRepository = imageRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<Product> products = usedluxRepository.findAll();

        // Then
        assertThat(products).isNotNull().hasSize(100);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousArticleCount = usedluxRepository.count();
        Image image = imageRepository.save(createImage());
        Product product = createProduct(image);

        // When
        usedluxRepository.save(product);

        // Then
        assertThat(usedluxRepository.count()).isEqualTo(previousArticleCount);
    }

    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Product product = usedluxRepository.findById(1L).orElseThrow();
        String updatedCategory = "New Category";
        product.setBigCategory(updatedCategory);

        // When
        Product savedProduct = usedluxRepository.saveAndFlush(product);

        // Then
        assertThat(savedProduct).hasFieldOrPropertyWithValue("bigCategory", updatedCategory);
    }

    @DisplayName("delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Product product = usedluxRepository.findById(1L).orElseThrow();
        long previousProductCount = usedluxRepository.count();

        // When
        usedluxRepository.delete(product);

        // Then
        assertThat(usedluxRepository.count()).isEqualTo(previousProductCount - 1);
    }

    private Image createImage() {
       Image image = Image.of(
                "/static/imgex1.jpg",
                "/static/imgex2.jpg",
                "/static/imgex3.jpg"
        );
        ReflectionTestUtils.setField(image, "id", 1L);
        return image;
    }

    private Product createProduct(Image image) {
        Product product = Product.of(
                "productName",
                "brandName",
                "bigCategory",
                "smallCategory",
                250,
                "M",
                "A+",
                3500000,
                0,
                image
        );
        ReflectionTestUtils.setField(product, "id", 1L);
        return product;
    }

}
