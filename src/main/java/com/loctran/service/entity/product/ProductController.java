package com.loctran.service.entity.product;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.product.dto.ProductDetailDto;
import com.loctran.service.entity.productCompare.dto.ProductCompareDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API for managing products.
 * Provides endpoints for retrieving, creating, updating, voting, and deleting products.
 */
@Tag(name = "Product", description = "APIs for managing products")
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Retrieves all products with pagination and optional filters")
    @GetMapping
    public ResponseEntity<ResponseDto> getAllProduct(@RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "12") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir,
        @RequestParam(required = false) String productName,
        @RequestParam(required = false) Long brandId,
        @RequestParam(required = false) Long countryId,
        @RequestParam(required = false) List<Long> notesIds) {
        Page<ListProductDto> product = productService.getAllProduct(page, size, sortBy, sortDir, brandId, countryId, notesIds, productName);
        return ResponseEntity.ok(ResponseDto.getDataSuccess(product));
    }

    @Operation(summary = "Get all product IDs", description = "Retrieves all product IDs")
    @GetMapping("/id")
    public ResponseEntity<ResponseDto> getAllProductId() {
        List<Long> ids = productService.getAllProductId();
        return ResponseEntity.ok(ResponseDto.getDataSuccess(ids));
    }

    @Operation(summary = "Get a product by ID", description = "Retrieves a product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(ResponseDto.getDataSuccess(product));
    }

    @Operation(summary = "Get product comparison", description = "Retrieves product comparison details by ID")
    @GetMapping("product-compare/{id}")
    public ResponseEntity<ResponseDto> findProductCompareById(@PathVariable Long id) {
        ProductCompareDto productCompare = productService.findProductCompare(id);
        return ResponseEntity.ok(ResponseDto.getDataSuccess(productCompare));
    }

    @Operation(summary = "Create a product", description = "Creates a new product")
    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@RequestBody CreateProductDto dto) {
        Product product = productService.createProduct(dto);
        return ResponseEntity.ok(ResponseDto.createDataSuccess(product));
    }

    @Operation(summary = "Toggle vote for a product", description = "Toggles the vote status for a product")
    @PutMapping("/{id}/vote")
    public ResponseEntity<ResponseDto> toggleVote(HttpServletRequest request, @PathVariable Long productId) {
        Long userId = (Long) request.getAttribute("userId");
        Product product = productService.toggleVote(userId, productId);
        return ResponseEntity.ok(ResponseDto.updateDataSuccess(product));
    }

    @Operation(summary = "Update a product", description = "Updates an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProductDetail(@PathVariable Long id, @RequestBody CreateProductDto dto) {
        Product product = productService.updateProduct(id, dto);
        return ResponseEntity.ok(ResponseDto.updateDataSuccess(product));
    }

    @Operation(summary = "Delete a product", description = "Deletes a product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable Long id) {
        Product product = productService.deleteProduct(id);
        return ResponseEntity.ok(ResponseDto.deleteDataSuccess(product));
    }
}
