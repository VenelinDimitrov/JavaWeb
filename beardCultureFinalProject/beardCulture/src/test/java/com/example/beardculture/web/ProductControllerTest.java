package com.example.beardculture.web;

import com.example.beardculture.model.entity.Product;
import com.example.beardculture.model.entity.enums.CategoryNameEnum;
import com.example.beardculture.repository.ProductRepository;
import com.example.beardculture.service.ProductService;
import com.example.beardculture.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static final String PRODUCT_NAME = "TestName";
    private static final String PRODUCT_DESCRIPTION = "Test Description";
    private static final CategoryNameEnum PRODUCT_CATEGORY = CategoryNameEnum.BALM;
    private static final Integer PRODUCT_QUANTITY = 4;
    private static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(9.8);
    private static final String MANUFACTURER = "TestManufacturer";
    private static final String IMAGE_URL = "TestImageURL";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }

    @Test
    void testOpenOils() throws Exception {
        mockMvc.perform(get("/products/oils")).andExpect(status().isOk())
                .andExpect(view().name("oils"));
    }

    @Test
    void testOpenBalms() throws Exception {
        mockMvc.perform(get("/products/balms")).andExpect(status().isOk())
                .andExpect(view().name("balms"));
    }

    @Test
    void testOpenGear() throws Exception {
        mockMvc.perform(get("/products/gear")).andExpect(status().isOk())
                .andExpect(view().name("gear"));
    }

    @Test
    @WithMockUser(username = "pesho", roles = {"USER", "MODERATOR"})
    void testOpenAddProductPage() throws Exception {
        mockMvc.perform(get("/products/add")).andExpect(status().isOk())
                .andExpect(view().name("add-product"));
    }

    @Test
    @WithMockUser(username = "pesho", roles = {"USER", "MODERATOR"})
    void testOpenProductDetails() throws Exception {

        mockMvc.perform(post("/products/add")
                        .param("productName", "Name")
                        .param("description", "Description")
                        .param("category", "OIL")
                        .param("quantity", "7")
                        .param("price", "10.30")
                        .param("manufacturer", "Beard Culture")
                        .param("imageUrl", "ImageTestUsrl")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


        mockMvc.perform(get("/products/details/1")).andExpect(status().isOk())
                .andExpect(view().name("product-details"));
    }

    @Test
    @WithMockUser(username = "pesho", roles = {"USER", "MODERATOR"})
    void testAddingProduct() throws Exception {
        mockMvc.perform(post("/products/add")
                        .param("productName", PRODUCT_NAME)
                        .param("description", PRODUCT_DESCRIPTION)
                        .param("category", String.valueOf(PRODUCT_CATEGORY))
                        .param("quantity", String.valueOf(PRODUCT_QUANTITY))
                        .param("price", String.valueOf(PRODUCT_PRICE))
                        .param("manufacturer", MANUFACTURER)
                        .param("imageUrl", IMAGE_URL)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, productRepository.count());

        Optional<Product> optionalProd = productRepository.getByName(PRODUCT_NAME);

        Assertions.assertTrue(optionalProd.isPresent());

        Product newlyAddedProduct = optionalProd.get();

        Assertions.assertEquals(newlyAddedProduct.getName(), PRODUCT_NAME);
        Assertions.assertEquals(newlyAddedProduct.getQuantity(), PRODUCT_QUANTITY);
        Assertions.assertEquals(newlyAddedProduct.getCategory().getName(), PRODUCT_CATEGORY);
        Assertions.assertEquals(newlyAddedProduct.getDescription(), PRODUCT_DESCRIPTION);
        Assertions.assertEquals(newlyAddedProduct.getImageUrl(),"/images/balms/" + IMAGE_URL + ".jpg");
        Assertions.assertEquals(newlyAddedProduct.getManufacturer().getName(), MANUFACTURER);
    }
}
