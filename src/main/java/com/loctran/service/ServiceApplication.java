package com.loctran.service;

import com.github.javafaker.Faker;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.productNote.ProductNoteRepository;
import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;

import com.loctran.service.entity.year.Year;
import com.loctran.service.entity.year.YearRepository;
import com.loctran.service.entity.year.YearService;
import com.loctran.service.entity.year.dto.CreateYearDto;
import com.loctran.service.entity.year.dto.YearDto;
import com.loctran.service.utils.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ServiceApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final ProductNoteRepository productNoteRepository;
    private final YearService yearService;
    private final YearRepository yearRepository;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String password = "123456";
        String passwordEncoded = passwordEncoder.encode(password);
        User user = User.builder().name("Quản trị viên ").email("admin@admin.com")
                .password(passwordEncoded).role(Role.ADMIN).build();
        userRepository.save(user);

        createDummyProduct();
    }

    private String generateImage() {
        List<String> images = new ArrayList<>() {
        };
        images.add("https://bizweb.dktcdn.net/thumb/medium/100/358/756/products/neroli-portofino-50ml.jpg?v=1601210049230");
        images.add("https://bizweb.dktcdn.net/thumb/medium/100/358/756/products/fucking-fabulous-50ml.jpg?v=1601211470253");
        images.add("https://bizweb.dktcdn.net/thumb/medium/100/358/756/products/jasmin-rouge-50ml.jpg?v=1601209923277");
        images.add("https://bizweb.dktcdn.net/thumb/medium/100/358/756/products/tony-iommi-monkey-special-50ml-1.png?v=1692030543600");
        images.add("https://bizweb.dktcdn.net/thumb/medium/100/358/756/products/starlit-mandarin-honey-cologne-100ml-2.png?v=1638780410803");

        Random random = new Random();
        int randomIndex = random.nextInt(images.size());

        // Return the image URL at the randomly generated index
        return images.get(randomIndex);

    }


    private void createDummyProduct() {
        Faker faker = new Faker(new Locale("vi"));
        for (int i = 0; i < 100; i++) {
            Media media = Media.builder()
                    .path(generateImage())
                    .type(MediaType.PRODUCT_NOTE_THUMBNAIL)
                    .build();

            Brand brand = Brand.builder()
                    .name(faker.company().name())
                    .homepageLink(faker.internet().url())
                    .description(faker.lorem().paragraph())
                    .thumbnail(media)
                    .createdAt(new Date())
                    .build();
            Country country = new Country(); // Assuming Country is a simple entity, you need to set it up
            country.setName(faker.address().country());
            country.setCode(faker.book().genre());


            brand.setCountry(country);
            String productName = faker.commerce().productName();
            Product product = Product.builder()
                    .name(productName)
                    .description(faker.lorem().paragraph())
                    .thumbnail(media)
                    .slug(StringUtil.convertToSlug(productName))
                    .country(country)

                    .notes(new ArrayList<>())
                    .brand(brand)
                    .build();

            // Assuming you have a ProductRepository to save the Product entities

            Product productSaved = productRepository.save(product);

            ProductNote productNote1 = new ProductNote();
            productNote1.setName(faker.commerce().productName());
            productNote1.setSlug(faker.lorem().word());

            ProductNote productNote2 = new ProductNote();
            productNote2.setName(faker.commerce().productName());
            productNote2.setSlug(faker.lorem().word());

            ProductNote productNoteSaved1 = productNoteRepository.save(productNote1);
            productNoteSaved1.setProducts(new ArrayList<>());
            productNoteSaved1.getProducts().add(productSaved);
            productNoteRepository.save(productNoteSaved1);

            ProductNote productNoteSaved2 = productNoteRepository.save(productNote2);
            productNoteSaved2.setProducts(new ArrayList<>());
            productNoteSaved2.getProducts().add(productSaved);
            productNoteRepository.save(productNoteSaved2);

            CreateYearDto dto = new CreateYearDto(faker.number().numberBetween(1900, 2024));
            Year yearSaved = yearService.createYear(dto);

            productSaved.setDateReleased(yearSaved);
            productRepository.save(productSaved);
        }
    }


}
