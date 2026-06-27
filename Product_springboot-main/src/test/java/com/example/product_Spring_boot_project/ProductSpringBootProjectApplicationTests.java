package com.example.product_Spring_boot_project;
//
////import org.junit.jupiter.api.Test;
////import org.springframework.boot.test.context.SpringBootTest;
////
////@SpringBootTest
////class ProductSpringBootProjectApplicationTests {
////
////	@Test
////	void contextLoads() {
////	}
////
////}
//
////package com.example.product_Spring_boot_project.model;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.math.BigDecimal;
//import java.util.Set;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import com.example.product_Spring_boot_project.model.Product;
//
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import jakarta.validation.ValidatorFactory;
//
///**
// * Unit tests for Product model:
// * - Custom validate() method behavior
// * - Bean Validation annotations on fields
// * - Basic constructor/getter/setter sanity
// */
//class ProductModelTest {
//
//    private final Validator validator;
//
//    ProductModelTest() {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        this.validator = factory.getValidator();
//    }
//
//    private Product validProduct() {
//        Product p = new Product();
//        p.setId(1L);
//        p.setProductName("iPhone 16");
//        p.setDescription("Best phone");
//        p.setPrice(new BigDecimal("749.00"));
//        p.setRetailCost(new BigDecimal("799.00"));
//        p.setSellingCost(new BigDecimal("899.00"));
//        p.setBrandName("Apple");
//        p.setProfitOnProduct(new BigDecimal("100.00"));
//        return p;
//    }
//
//    /* -------------------------------
//     * 1) validate() success on valid data
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should pass for a valid product")
//    void validate_shouldPass_forValidProduct() {
//        Product p = validProduct();
//        assertDoesNotThrow(p::validate);
//    }
//
//    /* -------------------------------
//     * 2) productName null -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when productName is null")
//    void validate_shouldThrow_whenProductNameNull() {
//        Product p = validProduct();
//        p.setProductName(null);
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, p::validate);
//        assertTrue(ex.getMessage().toLowerCase().contains("product name"), "Error should mention product name");
//    }
//
//    /* -------------------------------
//     * 3) productName blank -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when productName is blank")
//    void validate_shouldThrow_whenProductNameBlank() {
//        Product p = validProduct();
//        p.setProductName("   ");
//        assertThrows(IllegalArgumentException.class, p::validate);
//    }
//
//    /* -------------------------------
//     * 4) brandName null -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when brandName is null")
//    void validate_shouldThrow_whenBrandNameNull() {
//        Product p = validProduct();
//        p.setBrandName(null);
//        assertThrows(IllegalArgumentException.class, p::validate);
//    }
//
//    /* -------------------------------
//     * 5) brandName blank -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when brandName is blank")
//    void validate_shouldThrow_whenBrandNameBlank() {
//        Product p = validProduct();
//        p.setBrandName("  ");
//        assertThrows(IllegalArgumentException.class, p::validate);
//    }
//
//    /* -------------------------------
//     * 6) retailCost null -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when retailCost is null")
//    void validate_shouldThrow_whenRetailCostNull() {
//        Product p = validProduct();
//        p.setRetailCost(null);
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, p::validate);
//        assertTrue(ex.getMessage().toLowerCase().contains("retail cost"));
//    }
//
//    /* -------------------------------
//     * 7) retailCost negative -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when retailCost is negative")
//    void validate_shouldThrow_whenRetailCostNegative() {
//        Product p = validProduct();
//        p.setRetailCost(new BigDecimal("-1.00"));
//        assertThrows(IllegalArgumentException.class, p::validate);
//    }
//
//    /* -------------------------------
//     * 8) sellingCost null -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when sellingCost is null")
//    void validate_shouldThrow_whenSellingCostNull() {
//        Product p = validProduct();
//        p.setSellingCost(null);
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, p::validate);
//        assertTrue(ex.getMessage().toLowerCase().contains("selling cost"));
//    }
//
//    /* -------------------------------
//     * 9) sellingCost negative -> validate() throws
//     * ------------------------------- */
//    @Test
//    @DisplayName("validate(): should throw when sellingCost is negative")
//    void validate_shouldThrow_whenSellingCostNegative() {
//        Product p = validProduct();
//        p.setSellingCost(new BigDecimal("-0.01"));
//        assertThrows(IllegalArgumentException.class, p::validate);
//    }
//
//    /* -------------------------------
//     * 10) Bean Validation: productName @NotBlank and @Size(max=150)
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: productName must not be blank and <= 150 chars")
//    void beanValidation_productName_constraints() {
//        Product p = validProduct();
//
//        // Blank
//        p.setProductName(" ");
//        Set<ConstraintViolation<Product>> violations1 = validator.validate(p);
//        assertTrue(violations1.stream().anyMatch(v -> v.getPropertyPath().toString().equals("productName")));
//
//        // Too long
//        String longName = "x".repeat(151);
//        p.setProductName(longName);
//        Set<ConstraintViolation<Product>> violations2 = validator.validate(p);
//        assertTrue(violations2.stream().anyMatch(v -> v.getPropertyPath().toString().equals("productName")));
//    }
//
//    /* -------------------------------
//     * 11) Bean Validation: brandName @NotBlank and @Size(max=100)
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: brandName must not be blank and <= 100 chars")
//    void beanValidation_brandName_constraints() {
//        Product p = validProduct();
//
//        // Blank
//        p.setBrandName("");
//        Set<ConstraintViolation<Product>> violations1 = validator.validate(p);
//        assertTrue(violations1.stream().anyMatch(v -> v.getPropertyPath().toString().equals("brandName")));
//
//        // Too long
//        p.setBrandName("y".repeat(101));
//        Set<ConstraintViolation<Product>> violations2 = validator.validate(p);
//        assertTrue(violations2.stream().anyMatch(v -> v.getPropertyPath().toString().equals("brandName")));
//    }
//
//    /* -------------------------------
//     * 12) Bean Validation: price @DecimalMin(0.0) allows null or non-negative
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: price can be null or non-negative; negative should violate")
//    void beanValidation_price_constraints() {
//        Product p = validProduct();
//
//        // Null is allowed (no @NotNull on price)
//        p.setPrice(null);
//        Set<ConstraintViolation<Product>> violationsNull = validator.validate(p);
//        assertFalse(violationsNull.stream().anyMatch(v -> v.getPropertyPath().toString().equals("price")),
//                "Price null should not violate constraints");
//
//        // Non-negative ok
//        p.setPrice(new BigDecimal("0.00"));
//        Set<ConstraintViolation<Product>> violationsZero = validator.validate(p);
//        assertTrue(violationsZero.isEmpty());
//
//        // Negative invalid
//        p.setPrice(new BigDecimal("-0.01"));
//        Set<ConstraintViolation<Product>> violationsNeg = validator.validate(p);
//        assertTrue(violationsNeg.stream().anyMatch(v -> v.getPropertyPath().toString().equals("price")));
//    }
//
//    /* -------------------------------
//     * 13) Bean Validation: retailCost @NotNull and @DecimalMin(0.0)
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: retailCost must be non-null and non-negative")
//    void beanValidation_retailCost_constraints() {
//        Product p = validProduct();
//
//        p.setRetailCost(null);
//        Set<ConstraintViolation<Product>> violations1 = validator.validate(p);
//        assertTrue(violations1.stream().anyMatch(v -> v.getPropertyPath().toString().equals("retailCost")));
//
//        p.setRetailCost(new BigDecimal("-1.00"));
//        Set<ConstraintViolation<Product>> violations2 = validator.validate(p);
//        assertTrue(violations2.stream().anyMatch(v -> v.getPropertyPath().toString().equals("retailCost")));
//    }
//
//    /* -------------------------------
//     * 14) Bean Validation: sellingCost @NotNull and @DecimalMin(0.0)
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: sellingCost must be non-null and non-negative")
//    void beanValidation_sellingCost_constraints() {
//        Product p = validProduct();
//
//        p.setSellingCost(null);
//        Set<ConstraintViolation<Product>> violations1 = validator.validate(p);
//        assertTrue(violations1.stream().anyMatch(v -> v.getPropertyPath().toString().equals("sellingCost")));
//
//        p.setSellingCost(new BigDecimal("-0.01"));
//        Set<ConstraintViolation<Product>> violations2 = validator.validate(p);
//        assertTrue(violations2.stream().anyMatch(v -> v.getPropertyPath().toString().equals("sellingCost")));
//    }
//
//    /* -------------------------------
//     * 15) Bean Validation: description @Size(max=1000)
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: description must be <= 1000 characters")
//    void beanValidation_description_size() {
//        Product p = validProduct();
//
//        p.setDescription("a".repeat(1001));
//        Set<ConstraintViolation<Product>> violations = validator.validate(p);
//        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")));
//    }
//
//    /* -------------------------------
//     * 16) profitOnProduct @DecimalMin(0.0, inclusive=false) & @Digits
//     * ------------------------------- */
//    @Test
//    @DisplayName("Bean Validation: profitOnProduct must be > 0 and have up to 2 fraction digits")
//    void beanValidation_profit_constraints() {
//        Product p = validProduct();
//
//        // Zero is invalid because inclusive=false
//        p.setProfitOnProduct(new BigDecimal("0.00"));
//        Set<ConstraintViolation<Product>> violationsZero = validator.validate(p);
//        assertTrue(violationsZero.stream().anyMatch(v -> v.getPropertyPath().toString().equals("profitOnProduct")),
//                "Zero should violate DecimalMin(inclusive=false)");
//
//        // Valid profit: > 0 and 2 fraction digits
//        p.setProfitOnProduct(new BigDecimal("100.00"));
//        Set<ConstraintViolation<Product>> violationsValid = validator.validate(p);
//        assertTrue(violationsValid.isEmpty());
//
//        // Too many fraction digits -> violates @Digits(fraction=2)
//        p.setProfitOnProduct(new BigDecimal("100.123"));
//        Set<ConstraintViolation<Product>> violationsDigits = validator.validate(p);
//        assertTrue(violationsDigits.stream().anyMatch(v -> v.getPropertyPath().toString().equals("profitOnProduct")));
//    }
//
//    /* -------------------------------
//     * 17) Constructor sets all fields
//     * ------------------------------- */
//    @Test
//    @DisplayName("Constructor should set all fields correctly")
//    void constructor_setsAllFields() {
//        Product p = new Product(
//                99L,
//                "MacBook Pro",
//                "Laptop",
//                new BigDecimal("1999.99"),
//                new BigDecimal("1500.00"),
//                new BigDecimal("2100.00"),
//                "Apple",
//                new BigDecimal("600.00")
//        );
//
//        assertEquals(99L, p.getId());
//        assertEquals("MacBook Pro", p.getProductName());
//        assertEquals("Laptop", p.getDescription());
//        assertEquals(new BigDecimal("1999.99"), p.getPrice());
//        assertEquals(new BigDecimal("1500.00"), p.getRetailCost());
//        assertEquals(new BigDecimal("2100.00"), p.getSellingCost());
//        assertEquals("Apple", p.getBrandName());
//        assertEquals(new BigDecimal("600.00"), p.getProfitOnProduct());
//    }
//}


//package com.example.product_Spring_boot_project;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Combined integration tests:
 * - POST /products (create)
 * - GET /products (list all)
 * - GET /products/{id} (fetch one)
 * - GET /products/{id} (404 when not found)
 */
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper; // com.fasterxml.jackson.databind.ObjectMapper
//
//    /** Helper: create a product via POST and return its ID */
//    private long createSampleProduct(String name, String brand, BigDecimal price,
//                                    BigDecimal retailCost, BigDecimal sellingCost) throws Exception {
//
//        Map<String, Object> request = Map.of(
//                "productName", name,
//                "description", "Integration test product",
//                "price", price,
//                "retailCost", retailCost,
//                "sellingCost", sellingCost,
//                "brandName", brand,
//                // Optional — if your service computes profit, you can omit this:
//                "profitOnProduct", sellingCost.subtract(retailCost)
//        );
//
//        MvcResult result = mockMvc.perform(
//                    post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").exists())
//                .andReturn();
//
//        JsonNode root = objectMapper.readTree(result.getResponse().getContentAsString());
//        return root.get("id").asLong();
//    }
//
//    /* -----------------------
//     * POST /products (create)
//     * ----------------------- */
//    @Test
//    @DisplayName("POST /products should persist and return 201 with created ViewDto")
//    void create_shouldPersistAndReturn201_withRealContext() throws Exception {
//        Map<String, Object> request = Map.of(
//                "productName", "iPhone 16",
//                "description", "Best phone",
//                "price", new BigDecimal("749.00"),
//                "retailCost", new BigDecimal("799.00"),
//                "sellingCost", new BigDecimal("899.00"),
//                "brandName", "Apple",
//                "profitOnProduct", new BigDecimal("100.00") // optional; often computed server-side
//        );
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.productName").value("iPhone 16"))
//                .andExpect(jsonPath("$.brandName").value("Apple"))
//                .andExpect(jsonPath("$.price").value(749.00))
//                .andExpect(jsonPath("$.retailCost").value(799.00))
//                .andExpect(jsonPath("$.sellingCost").value(899.00))
//                .andExpect(jsonPath("$.profitOnProduct").value(100.00));
//    }
//
//    /* -----------------------
//     * GET /products (list all)
//     * ----------------------- */
//    @Test
//    @DisplayName("GET /products should return 200 and a JSON array with at least one element")
//    void get_all_products_shouldReturn200_andList() throws Exception {
//        // Arrange: create at least one product
//        createSampleProduct("Galaxy S25", "Samsung",
//                new BigDecimal("999.00"),
//                new BigDecimal("700.00"),
//                new BigDecimal("999.00"));
//
//        // Act + Assert
//        mockMvc.perform(get("/products"))
//               .andExpect(status().isOk())
//               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//               .andExpect(jsonPath("$").isArray())
//               .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.greaterThan(0)));
//    }
//
//    /* -----------------------------
//     * GET /products/{id} (fetch one)
//     * ----------------------------- */
//    @Test
//    @DisplayName("GET /products/{id} should return 200 and the specific product ViewDto")
//    void get_by_id_shouldReturn200_andProduct() throws Exception {
//        // Arrange: create one product and capture its id
//        long id = createSampleProduct("Pixel 10", "Google",
//                new BigDecimal("799.00"),
//                new BigDecimal("600.00"),
//                new BigDecimal("799.00"));
//
//        // Act + Assert: fetch by id
//        mockMvc.perform(get("/products/{id}", id))
//               .andExpect(status().isOk())
//               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//               .andExpect(jsonPath("$.id").value(id))
//               .andExpect(jsonPath("$.productName").value("Pixel 10"))
//               .andExpect(jsonPath("$.brandName").value("Google"))
//               .andExpect(jsonPath("$.price").value(799.00))
//                             .andExpect(jsonPath("$.retailCost").value(600.00))
//               .andExpect(jsonPath("$.sellingCost").value(799.00))
//               .andExpect(jsonPath("$.profitOnProduct").value(199.00)); // 799 - 600
//    }
//
//    /* -----------------------------------------
//     * GET /products/{id} (404 when not found)
//     * ----------------------------------------- */
//    @Test
//    @DisplayName("GET /products/{id} should return 404 Not Found for non-existing id")
//    void get_by_id_shouldReturn404_whenNotFound() throws Exception {
//        long nonexistentId = 999_999L;
//
//        mockMvc.perform(get("/products/{id}", nonexistentId))
//               .andExpect(status().isNotFound());
//    }
//
//}

//package com.example.product_Spring_boot_project.model;


//package com.example.product_Spring_boot_project;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * Integration tests for ProductController:
// * - POST /products (create)
// * - GET /Products (list all)
// * - GET /Products/{id} (fetch one)
// * - 404 for /products if mapping is capital P
// */
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    /** Helper: create a product via POST and return its ID */
//    private long createSampleProduct(String name, String brand, BigDecimal price,
//                                    BigDecimal retailCost, BigDecimal sellingCost) throws Exception {
//
//        Map<String, Object> request = Map.of(
//                "productName", name,
//                "description", "Integration test product",
//                "price", price,
//                "retailCost", retailCost,
//                "sellingCost", sellingCost,
//                "brandName", brand
//                // profitOnProduct intentionally omitted; service stores dto value if present,
//                // but toViewDto computes profit from costs for the response.
//        );
//
//        MvcResult result = mockMvc.perform(
//                    post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request))
//                )
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").exists())
//                .andReturn();
//
//        JsonNode root = objectMapper.readTree(result.getResponse().getContentAsString());
//        return root.get("id").asLong();
//    }
//
//    // 1
//    @Test
//    @DisplayName("POST /products should persist and return 201 with created ViewDto")
//    void create_shouldPersistAndReturn201() throws Exception {
//        Map<String, Object> request = Map.of(
//                "productName", "iPhone 16",
//                "description", "Best phone",
//                "price", new BigDecimal("749.00"),
//                "retailCost", new BigDecimal("799.00"),
//                "sellingCost", new BigDecimal("899.00"),
//                "brandName", "Apple",
//                "profitOnProduct", new BigDecimal("100.00") // optional
//        );
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.productName").value("iPhone 16"))
//                .andExpect(jsonPath("$.brandName").value("Apple"))
//                .andExpect(jsonPath("$.price").value(749.00))
//                .andExpect(jsonPath("$.retailCost").value(799.00))
//                .andExpect(jsonPath("$.sellingCost").value(899.00))
//                .andExpect(jsonPath("$.profitOnProduct").value(100.00)); // toViewDto might recompute too
//    }
//
//    // 2
//    @Test
//    @DisplayName("POST /products invalid payload (blank productName) yields 500 by default")
//    void create_invalidPayload_yieldsServerError() throws Exception {
//        // Since controller doesn't use @Valid and no @ControllerAdvice, IllegalArgumentException will bubble as 500
//        Map<String, Object> bad = Map.of(
//                "productName", "   ",
//                "brandName", "Brand",
//                "retailCost", new BigDecimal("100.00"),
//                "sellingCost", new BigDecimal("200.00")
//        );
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(bad)))
//                .andExpect(status().is5xxServerError());
//    }
//
//    // 3
//    @Test
//    @DisplayName("POST /products with profit omitted should still compute profit in response")
//    void create_withoutProfit_clientStillSeesComputedProfit() throws Exception {
//        Map<String, Object> req = Map.of(
//                "productName", "Mouse",
//                "brandName", "LogiTech",
//                "description", "Ergonomic",
//                "price", new BigDecimal("500.00"),
//                "retailCost", new BigDecimal("300.00"),
//                "sellingCost", new BigDecimal("500.00")
//        );
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.profitOnProduct").value(200.00)); // 500 - 300
//    }
//
//    // 4
//    @Test
//    @DisplayName("GET /Products returns 200 and a JSON array with at least one element")
//    void get_all_products_shouldReturn200_andList() throws Exception {
//        createSampleProduct("Galaxy S25", "Samsung",
//                new BigDecimal("999.00"),
//                new BigDecimal("700.00"),
//                new BigDecimal("999.00"));
//
//        mockMvc.perform(get("/Products"))
//               .andExpect(status().isOk())
//               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//               .andExpect(jsonPath("$").isArray())
//               .andExpect(jsonPath("$[0]").exists());
//    }
//
//    // 5
//    @Test
//    @DisplayName("GET /Products/{id} returns 200 and the product ViewDto")
//    void get_by_id_shouldReturn200_andProduct() throws Exception {
//        long id = createSampleProduct("Pixel 10", "Google",
//                new BigDecimal("799.00"),
//                new BigDecimal("600.00"),
//                new BigDecimal("799.00"));
//
//        mockMvc.perform(get("/Products/{id}", id))
//               .andExpect(status().isOk())
//               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//               .andExpect(jsonPath("$.id").value(id))
//               .andExpect(jsonPath("$.productName").value("Pixel 10"))
//               .andExpect(jsonPath("$.brandName").value("Google"))
//               .andExpect(jsonPath("$.price").value(799.00))
//               .andExpect(jsonPath("$.retailCost").value(600.00))
//               .andExpect(jsonPath("$.sellingCost").value(799.00))
//               .andExpect(jsonPath("$.profitOnProduct").value(199.00)); // 799 - 600
//    }
//
//    // 6
//    @Test
//    @DisplayName("GET /Products/{id} returns 404 Not Found for non-existing id")
//    void get_by_id_shouldReturn404_whenNotFound() throws Exception {
//        mockMvc.perform(get("/Products/{id}", 999_999L))
//               .andExpect(status().isNotFound());
//    }
//
//    // 7
//    @Test
//    @DisplayName("GET /products (lowercase) returns 404 because mapping uses capital P")
//    void get_lowercase_products_returns404() throws Exception {
//        mockMvc.perform(get("/products"))
//               .andExpect(status().isNotFound());
//    }
//
//    // 8
//    @Test
//    @DisplayName("POST response content-type should be application/json")
//    void create_responseContentTypeIsJson() throws Exception {
//        Map<String, Object> req = Map.of(
//                "productName", "Keyboard",
//                "brandName", "HyperX",
//                "price", new BigDecimal("1500.00"),
//                "retailCost", new BigDecimal("1000.00"),
//                "sellingCost", new BigDecimal("1500.00")
//        );
//
//        mockMvc.perform(post("/products")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    // 9
//    @Test
//    @DisplayName("Round-trip: create then get by id should return same fields")
//    void roundTrip_createThenGetById_matches() throws Exception {
//        long id = createSampleProduct("Earbuds", "Sony",
//                new BigDecimal("3000.00"),
//                new BigDecimal("2200.00"),
//                new BigDecimal("3000.00"));
//
//        mockMvc.perform(get("/Products/{id}", id))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.id").value(id))
//               .andExpect(jsonPath("$.productName").value("Earbuds"))
//               .andExpect(jsonPath("$.brandName").value("Sony"));
//    }
//
//    // 10
//    @Test
//    @DisplayName("GET all shows computed profit fields present")
//       void get_all_containsProfitField() throws Exception {
//        createSampleProduct("Charger", "Anker",
//                new BigDecimal("1000.00"),
//                new BigDecimal("700.00"),
//                new BigDecimal("1000.00"));
//
//        mockMvc.perform(get("/Products"))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$[0].profitOnProduct").exists());
//    }
//}

//package com.example.product_Spring_boot_project.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.example.product_Spring_boot_project.Repository.ProductRepo;
import com.example.product_Spring_boot_project.Service.IProductServiceImp;
import com.example.product_Spring_boot_project.dto.InputDto;
import com.example.product_Spring_boot_project.dto.ViewDto;
import com.example.product_Spring_boot_project.model.Product;

@ExtendWith(MockitoExtension.class)
class IProductServiceImpTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private IProductServiceImp service;

    private InputDto validInput() {
        InputDto dto = new InputDto();
        dto.setProductName("Wireless Mouse");
        dto.setBrandName("LogiTech");
        dto.setDescription("Ergonomic wireless mouse");
        dto.setPrice(new BigDecimal("500.00"));
        dto.setRetailCost(new BigDecimal("300.00"));
        dto.setSellingCost(new BigDecimal("500.00"));
        dto.setProfitOnProduct(new BigDecimal("200.00")); // client-provided (service keeps it)
        return dto;
    }

    // 1
    @Test
    @DisplayName("createProduct: should map DTO to entity, save, and return ViewDto")
    void createProduct_shouldSave_andReturnViewDto() {
        InputDto dto = validInput();

        Product saved = new Product();
        saved.setId(1L);
        saved.setProductName(dto.getProductName());
        saved.setBrandName(dto.getBrandName());
        saved.setDescription(dto.getDescription());
        saved.setPrice(dto.getPrice());
        saved.setRetailCost(dto.getRetailCost());
        saved.setSellingCost(dto.getSellingCost());
        saved.setProfitOnProduct(dto.getProfitOnProduct());

        when(productRepo.save(any(Product.class))).thenReturn(saved);

        ViewDto view = service.createProduct(dto);

        assertNotNull(view);
        assertEquals(1L, view.getId());
        assertEquals("Wireless Mouse", view.getProductName());
        assertEquals("LogiTech", view.getBrandName());
        assertEquals(new BigDecimal("500.00"), view.getPrice());
        assertEquals(new BigDecimal("300.00"), view.getRetailCost());
        assertEquals(new BigDecimal("500.00"), view.getSellingCost());
        assertEquals(new BigDecimal("200.00"), view.getProfitOnProduct()); // toViewDto computes (500-300) also 200
        verify(productRepo, times(1)).save(any(Product.class));
    }

    // 2
    @Test
    @DisplayName("createProduct: should throw IllegalArgumentException when validate fails")
    void createProduct_shouldThrow_whenValidationFails() {
        InputDto dto = validInput();
        dto.setProductName("   "); // invalid

        // No need to stub repo; validation fails before save
        assertThrows(IllegalArgumentException.class, () -> service.createProduct(dto));
        verify(productRepo, never()).save(any());
    }

    // 3
    @Test
    @DisplayName("GetAllProduct: returns mapped list")
    void getAllProduct_returnsMappedList() {
        Product a = new Product();
        a.setId(10L);
        a.setProductName("A");
        a.setBrandName("B");
        a.setRetailCost(new BigDecimal("100"));
        a.setSellingCost(new BigDecimal("150"));

        Product b = new Product();
        b.setId(20L);
        b.setProductName("C");
        b.setBrandName("D");
        b.setRetailCost(new BigDecimal("200"));
        b.setSellingCost(new BigDecimal("260"));

        when(productRepo.findAll()).thenReturn(Arrays.asList(a, b));

        var list = service.GetAllProduct();
        assertEquals(2, list.size());
        assertEquals(10L, list.get(0).getId());
        assertEquals(new BigDecimal("50"), list.get(0).getProfitOnProduct()); // 150-100
        assertEquals(new BigDecimal("60"), list.get(1).getProfitOnProduct()); // 260-200
        verify(productRepo, times(1)).findAll();
    }

    // 4
    @Test
    @DisplayName("GetAllProduct: empty repo returns empty list")
    void getAllProduct_returnsEmpty() {
        when(productRepo.findAll()).thenReturn(Collections.emptyList());
        var list = service.GetAllProduct();
        assertTrue(list.isEmpty());
        verify(productRepo, times(1)).findAll();
    }

    // 5
    @Test
    @DisplayName("GetProductById: found returns mapped ViewDto")
    void getProductById_foundReturnsViewDto() {
        Product p = new Product();
        p.setId(5L);
        p.setProductName("X");
        p.setBrandName("Y");
        p.setRetailCost(new BigDecimal("100"));
        p.setSellingCost(new BigDecimal("180"));
        when(productRepo.findById(5L)).thenReturn(Optional.of(p));

        ViewDto dto = service.GetProductById(5L);
        assertEquals(5L, dto.getId());
        assertEquals("X", dto.getProductName());
        assertEquals("Y", dto.getBrandName());
        assertEquals(new BigDecimal("80"), dto.getProfitOnProduct());
        verify(productRepo, times(1)).findById(5L);
    }

    // 6
    @Test
    @DisplayName("GetProductById: not found throws ResponseStatusException(404)")
    void getProductById_notFoundThrows404() {
        when(productRepo.findById(999L)).thenReturn(Optional.empty());
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> service.GetProductById(999L));
        assertEquals(404, ex.getStatusCode().value());
        verify(productRepo, times(1)).findById(999L);
    }

    // 7
    @Test
    @DisplayName("deleteProduct: calls repository deleteById")
    void deleteProduct_callsRepository() {
        doNothing().when(productRepo).deleteById(7L);
        service.deleteProduct(7L);
        verify(productRepo, times(1)).deleteById(7L);
    }

    // 8
    @Test
    @DisplayName("toViewDto: uses computed profit when both costs present")
    void toViewDto_computedProfitPreferred() {
        Product p = new Product();
        p.setId(1L);
        p.setProductName("P");
        p.setBrandName("B");
        p.setRetailCost(new BigDecimal("300"));
        p.setSellingCost(new BigDecimal("450"));
        p.setProfitOnProduct(new BigDecimal("999.99")); // should be ignored

        ViewDto dto = invokeToViewDto(p);
        assertEquals(new BigDecimal("150"), dto.getProfitOnProduct()); // 450-300
    }

    // 9
    @Test
    @DisplayName("toViewDto: falls back to stored profit when costs missing")
    void toViewDto_usesStoredProfitWhenCostsMissing() {
        Product p = new Product();
        p.setId(2L);
        p.setProductName("P");
        p.setBrandName("B");
        p.setRetailCost(null);
        p.setSellingCost(null);
        p.setProfitOnProduct(new BigDecimal("123.45"));

        ViewDto dto = invokeToViewDto(p);
        assertEquals(new BigDecimal("123.45"), dto.getProfitOnProduct());
    }

    /** Helper to access private toViewDto via reflection (since it's private in service) */
    private ViewDto invokeToViewDto(Product p) {
        try {
            var m1 = IProductServiceImp.class.getDeclaredMethod("toViewDto", Product.class);
            m1.setAccessible(true);
            return (ViewDto) m1.invoke(service, p);
        } catch (Exception e) {
            fail("Reflection failed to invoke toViewDto: " + e.getMessage());
            return null;
        }
    }
}