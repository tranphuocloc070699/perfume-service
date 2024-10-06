package com.loctran.service;

import com.github.javafaker.Faker;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.comment.CommentRepository;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaRepository;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.media.dto.MediaDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.ProductCompareRepository;
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
    private final MediaRepository mediaRepository;
    private final CommentRepository commentRepository;
    private final ProductCompareRepository productCompareRepository;

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String password = "123456";
        String passwordEncoded = passwordEncoder.encode(password);
        User user = User.builder().name("Quản trị viên ").email("admin@admin.com")
                .password(passwordEncoded).role(Role.ADMIN).build();
        User userSaved = userRepository.save(user);

        createDummyProduct(userSaved);
        createProductCompare(userSaved);
    }


    public static Long generateRandomExcluding(int min, int max, Long exclude) {
        Random random = new Random();
        Long randomNumber;

        // Ensure min is inclusive and max is exclusive (like nextInt)
        int range = max - min;

        if (range <= 1) {
            throw new IllegalArgumentException("Range is too small to exclude a value.");
        }

        do {
            randomNumber = (long) (random.nextInt(range) + min);
        } while (randomNumber == exclude); // Regenerate if it matches the excluded value

        return randomNumber;
    }

    private void createProductCompare(User user) {
        List<Product> products = productRepository.findAll();
        Faker faker = new Faker(new Locale("vi"));
        for (Product product : products) {

            for (int i = 0; i <= 20; i++) {
                Long randomId = generateRandomExcluding(1,100,product.getId());

                Optional<Product> productCompare = productRepository.findById(randomId);
                if(productCompare.isPresent()) {
                    ProductCompare newProductCompare = new ProductCompare();
                    newProductCompare.setProductOriginal(product);
                    newProductCompare.setProductCompare(productCompare.get());
                  var productCompareSaved =   productCompareRepository.save(newProductCompare);
                    for (int j = 0; j < 20; j++) {
                        Comment comment = commentRepository.save(Comment.builder().productCompare(productCompareSaved).user(user).content(faker.lorem().paragraph()).build());
                        commentRepository.save(comment);
                    }

                }

            }



        }
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
        return images.get(randomIndex);
    }

    private void generateGallery(Product product) {
        List<String> images = new ArrayList<>() {
        };
        images.add("https://plus.unsplash.com/premium_photo-1679106770086-f4355693be1b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGVyZnVtZXxlbnwwfHwwfHx8MA%3D%3D");
        images.add("https://images.unsplash.com/photo-1605619082574-e92eee603b95?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGVyZnVtZXxlbnwwfHwwfHx8MA%3D%3D");
        images.add("https://images.unsplash.com/photo-1506915925765-ed31516b9080?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://images.unsplash.com/photo-1547887537-6158d64c35b3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8cGVyZnVtZXxlbnwwfHwwfHx8MA%3D%3D");
        images.add("https://images.unsplash.com/photo-1590156221719-02e2d5ae7345?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://images.unsplash.com/photo-1590156221187-1710315f710b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://images.unsplash.com/photo-1590156221719-02e2d5ae7345?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://plus.unsplash.com/premium_photo-1678449464118-75786d816fac?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");

        images.forEach((image) -> {
            Media media = Media.builder().path(image).type(MediaType.PRODUCT_GALLERY).product(product).build();
            mediaRepository.save(media);
        });
    }


    private void generateOutfit(Product product) {
        List<String> images = new ArrayList<>() {
        };
        images.add("https://plus.unsplash.com/premium_photo-1668485968642-30e3d15e9b9c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1490114538077-0a7f8cb49891?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1512484580809-b5251c5df9dd?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1515734392280-e60c25eb9f01?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1547355332-7c6fcb397868?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1525507119028-ed4c629a60a3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://plus.unsplash.com/premium_photo-1697183203538-08c30b0a6709?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://plus.unsplash.com/premium_photo-1673734625669-7ef119c3ef65?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTN8fG91dGZpdHxlbnwwfHwwfHx8MA%3D%3D");

        images.forEach((image) -> {
            Media media = Media.builder().path(image).type(MediaType.PRODUCT_OUTFIT).product(product).build();
            mediaRepository.save(media);
        });
    }


    private void createDummyProduct(User user) {
        Faker faker = new Faker(new Locale("vi"));
        for (int i = 0; i < 100; i++) {
            Media media = Media.builder()
                    .path(generateImage())
                    .type(MediaType.PRODUCT_THUMBNAIL)
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
            product.setVotes(new ArrayList<>());
            product.getVotes().add(user);
            Product productSaved = productRepository.save(product);
            generateGallery(productSaved);
            generateOutfit(productSaved);

            for (int j = 0; j < 20; j++) {
                Comment comment = commentRepository.save(Comment.builder().product(productSaved).user(user).content(faker.lorem().paragraph()).build());
                commentRepository.save(comment);
            }




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
