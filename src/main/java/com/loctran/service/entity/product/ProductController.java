package com.loctran.service.entity.product;

import com.loctran.service.common.ResponseDto;
import com.loctran.service.entity.product.dto.CreateProductDto;
import com.loctran.service.entity.product.dto.ListProductDto;
import com.loctran.service.entity.product.dto.ProductDetailDto;
import com.loctran.service.entity.product.dto.UpdateProductDto;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.ProductCompareRepository;
import com.loctran.service.entity.productCompare.dto.ProductCompareDto;
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

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @GetMapping("")
    public ResponseEntity<ResponseDto> getAllProduct(@RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "12") int size,
                                                     @RequestParam(defaultValue = "id") String sortBy,
                                                     @RequestParam(defaultValue = "asc") String sortDir, @RequestParam(required = false)String productName ,@RequestParam(required = false) Long brandId,
                                                     @RequestParam(required = false) Long countryId,
                                                     @RequestParam(required = false) List<Long> notesIds) {
        Page<ListProductDto> product = productService.getAllProduct(page, size, sortBy, sortDir, brandId, countryId, notesIds,productName);

        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy tất cả thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/id")
    public ResponseEntity<ResponseDto> getAllProductId() {

        List<Long> ids = productService.getAllProductId();

        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy IDs thành công");
        responseDto.setStatus(200);
        responseDto.setData(ids);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findProductById(@PathVariable("id") Long id) {
        Product product = productService.findProductById(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("product-compare/{id}")
    public ResponseEntity<ResponseDto> findProducCompareById(@PathVariable("id") Long id) {
        ProductCompareDto productCompare = productService.findProductCompare(id);
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Lấy thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(productCompare);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody CreateProductDto dto) {
        Product product = productService.createProduct(dto);

        ResponseDto responseDto = ResponseDto.builder().build();

        responseDto.setMessage("Tạo sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);

        return ResponseEntity.ok(responseDto);
    }

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


    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> updateProductDetail(@PathVariable("id") String id,
                                                           @RequestBody CreateProductDto dto) {
        System.out.println("update product detail...");
        Product product = productService.updateProduct(Long.parseLong(id), dto);
        System.out.println("product after updated...");
        ResponseDto responseDto = ResponseDto.builder().build();
        responseDto.setMessage("Update thông tin sản phẩm thành công");
        responseDto.setStatus(200);
        responseDto.setData(product);
        return ResponseEntity.ok(responseDto);
    }


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
