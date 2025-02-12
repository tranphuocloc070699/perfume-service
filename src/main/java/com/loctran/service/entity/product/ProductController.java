package com.loctran.service.entity.product;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.product.dto.ProductDetailDto;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.ProductCompareRepository;
import com.loctran.service.entity.productCompare.dto.ProductCompareDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * Retrieves all products with pagination and optional filters.
     */
    @Operation(summary = "Get all products", description = "Retrieves all products with pagination and optional filters")
    @GetMapping("")
    public ResponseEntity<ResponseDto> getAllProduct(@RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "12") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDir,
        @RequestParam(required = false) String productName,
        @RequestParam(required = false) Long brandId,
        @RequestParam(required = false) Long countryId,
        @RequestParam(required = false) List<Long> notesIds) {
        Page<ListProductDto> product = productService.getAllProduct(page, size, sortBy, sortDir, brandId, countryId, notesIds, productName);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy tất cả thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Retrieves all product IDs.
     */
    @Operation(summary = "Get all product IDs", description = "Retrieves all product IDs")
    @GetMapping("/id")
    public ResponseEntity<ResponseDto> getAllProductId() {
        List<Long> ids = productService.getAllProductId();
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy IDs thành công");
        responseDto.setStatus(200);
        responseDto.setData(ids);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Retrieves a product by its ID.
     */
    @Operation(summary = "Get a product by ID", description = "Retrieves a product by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Retrieves product comparison details by ID.
     */
    @Operation(summary = "Get product comparison", description = "Retrieves product comparison details by ID")
    @GetMapping("product-compare/{id}")
    public ResponseEntity<ResponseDto> findProductCompareById(@PathVariable("id") Long id) {
        ProductCompareDto productCompare = productService.findProductCompare(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(productCompare);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Creates a new product.
     */
    @Operation(summary = "Create a product", description = "Creates a new product")
    @PostMapping("")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody CreateProductDto dto) {
        Product product = productService.createProduct(dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Tạo sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Toggles the vote status for a product.
     */
    @Operation(summary = "Toggle vote for a product", description = "Toggles the vote status for a product")
    @PutMapping("/{id}/vote")
    public ResponseEntity<ResponseDto> toggleVote(HttpServletRequest request, @PathVariable("id") Long productId) {
        Long userId = (Long) request.getAttribute("userId");
        Product product = productService.toggleVote(userId, productId);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Toggle vote thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Updates an existing product.
     */
    @Operation(summary = "Update a product", description = "Updates an existing product")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateProductDetail(@PathVariable("id") String id, @RequestBody CreateProductDto dto) {
        Product product = productService.updateProduct(Long.parseLong(id), dto);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Update thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * Deletes a product by its ID.
     */
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable("id") String id) {
        Product product = productService.deleteProduct(Long.parseLong(id));
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Xóa sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }
}
