package com.loctran.service;

import com.github.javafaker.Faker;
import com.loctran.service.entity.book.Book;
import com.loctran.service.entity.book.BookRepository;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.brand.BrandRepository;
import com.loctran.service.entity.collection.CollectionProduct;
import com.loctran.service.entity.collection.CollectionProductRepository;
import com.loctran.service.entity.collection.CollectionRepository;
import com.loctran.service.entity.comment.Comment;
import com.loctran.service.entity.comment.CommentRepository;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.country.CountryRepository;
import com.loctran.service.entity.post.Post;
import com.loctran.service.entity.post.PostRepository;
import com.loctran.service.entity.post.PostService;
import com.loctran.service.entity.post.PostType;
import com.loctran.service.entity.post.dto.UpsavePostDto;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.productCompare.ProductCompare;
import com.loctran.service.entity.productCompare.ProductCompareRepository;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.productNote.ProductNoteRepository;
import com.loctran.service.entity.productPrice.PriceType;
import com.loctran.service.entity.productPrice.LabelType;
import com.loctran.service.entity.productPrice.ProductPrice;
import com.loctran.service.entity.productPrice.ProductPriceRepository;
import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;
import com.loctran.service.entity.year.Year;
import com.loctran.service.entity.year.YearService;
import com.loctran.service.entity.year.dto.CreateYearDto;
import com.loctran.service.utils.StringUtil;

import jakarta.transaction.Transactional;
import java.util.*;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ServiceApplication implements  CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final ProductNoteRepository productNoteRepository;
    private final YearService yearService;
    private final CommentRepository commentRepository;
    private final ProductCompareRepository productCompareRepository;
    private final ProductPriceRepository productPriceRepository;
    private final CountryRepository countryRepository;
    private final BrandRepository brandRepository;
    private final PostRepository postRepository;
    private final PostService postService;
    private final BookRepository bookRepository;
    private final CollectionRepository collectionRepository;
    private final CollectionProductRepository collectionProductRepository;

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

        User managedUser = userRepository.findById(userSaved.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        createDummyProduct(managedUser);
        createProductCompare(managedUser);
        createDummyPost(managedUser);
        createDummyBook(managedUser);
//        createDummyCollection(managedUser);
    }

    private void createDummyBook(User userSaved) {
        Faker faker = new Faker(new Locale("vi"));
        for (int i = 0; i < 5; i++) {
            Book book = Book.builder()
                .name(faker.book().title())
                .description(faker.lorem().paragraph())
                .link(faker.internet().url())
                .thumbnail(generateBookThumbnail())
                .build();

            bookRepository.save(book);
        }
    }

    @Transactional
    private void createDummyCollection(User user) {
        Faker faker = new Faker(new Locale("vi"));
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            // Create a new Collection
            com.loctran.service.entity.collection.Collection collection = com.loctran.service.entity.collection.Collection.builder()
                .icon(faker.internet().avatar())
                .title(faker.book().title())
                .index(i)
                .build();

            Set<CollectionProduct> collectionProducts = new HashSet<>();
            int numberOfProducts = random.nextInt(6) + 5; // Ensure between 5 and 10 products

            for (int j = 0; j < numberOfProducts; j++) {
                Long randomProductId = (long) (random.nextInt(10) + 1); // Random product ID between 1 and 10
                Optional<Product> productOptional = productRepository.findById(randomProductId);

                if (productOptional.isPresent()) {
                    Product product = productOptional.get();

                    // Initialize lazy-loaded fields, if needed
                    Hibernate.initialize(product.getGalleries());
                    Hibernate.initialize(product.getOutfits());

                    // Build and add CollectionProduct
                    CollectionProduct collectionProduct = CollectionProduct.builder()
                        .product(product)
                        .collection(collection)
                        .index(j)
                        .build();

                    collectionProducts.add(collectionProduct);
                }
            }

            // Associate collectionProducts and save the collection
            collection.setCollectionProducts(collectionProducts);
            collectionRepository.save(collection);
        }
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

    private void createDummyPost(User user) {
        Faker faker = new Faker(new Locale("vi"));
        for (int i = 0; i < 20; i++) {
            String title = faker.book().title();
            String content = faker.lorem().paragraph();
            boolean isPinned = i < 8;

            UpsavePostDto post = UpsavePostDto.builder()
                .title(title)
                .content(content)
                .excerpt(faker.lorem().sentence())
                .slug(faker.lorem().word())
                .isPinned(isPinned)
                .thumbnail(generatePostThumbnail())
                .type(PostType.NEWS)
                .build();

            postService.createPost(user.getId(),post);
        }
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
                    newProductCompare.setOriginalVotes(new ArrayList<>(Arrays.asList(1L,2l,3L,4L)));
                    newProductCompare.setCompareVotes(new ArrayList<>(Arrays.asList(1L,2l,3L,4L)));
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

    private String generatePostThumbnail() {
        List<String> images = new ArrayList<>() {
        };
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStdxet3zX9keprAnh_Ba2VTYf5VQKhZ2RRQQ&s");
        images.add("https://theperfumebox.com/cdn/shop/articles/08.11.2024.jpg?v=1731052526");
        images.add("https://perfumeonline.ca/cdn/shop/files/Boxing_day_and_Christmas_Mobile_2fac571a-8b7e-4387-8a78-6020ce7907fd.png?v=1735074276");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_VUOmrrUWJiHR_PHJlmh7b22gIEA8xBuheg&s");
        images.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbFyCQsencz2Qx7o2mWyPRSz-HCG0wkV1FiA&s");

        Random random = new Random();
        int randomIndex = random.nextInt(images.size());
        return images.get(randomIndex);
    }

    private String generateBookThumbnail() {
        List<String> images = new ArrayList<>() {
        };
        images.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSDzRlS4jJKaMV0bKtv_LsTBFbYGHUpWFC-sYxkpqaASJw5XjLZ");
        images.add("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTRWSolGesc9VzyA7hw1nyn2RDJRiYQI6kDYsyomLsMg_mRX1Lq");
        images.add("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRLBT-_DQe88Uhedc9bbsM8QZGpG-KwYdPhDyWcYlFniumLTz79");
        images.add("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQkhrnpslzeScNq2QOT37BhkqWHiTE74dk4pscld8ucPLlatKNf");


        Random random = new Random();
        int randomIndex = random.nextInt(images.size());
        return images.get(randomIndex);
    }

    private Set<String> generateGallery() {
        Set<String> images = new HashSet<>();
        images.add("https://plus.unsplash.com/premium_photo-1679106770086-f4355693be1b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGVyZnVtZXxlbnwwfHwwfHx8MA%3D%3D");
        images.add("https://images.unsplash.com/photo-1605619082574-e92eee603b95?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cGVyZnVtZXxlbnwwfHwwfHx8MA%3D%3D");
        images.add("https://images.unsplash.com/photo-1506915925765-ed31516b9080?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://images.unsplash.com/photo-1547887537-6158d64c35b3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8cGVyZnVtZXxlbnwwfHwwfHx8MA%3D%3D");
        images.add("https://images.unsplash.com/photo-1590156221719-02e2d5ae7345?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://images.unsplash.com/photo-1590156221187-1710315f710b?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");
        images.add("https://plus.unsplash.com/premium_photo-1678449464118-75786d816fac?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTd8fHBlcmZ1bWV8ZW58MHx8MHx8fDA%3D");


        return images;
    }


    private Set<String> generateOutfit() {
        Set<String> images = new HashSet<>();
        images.add("https://plus.unsplash.com/premium_photo-1668485968642-30e3d15e9b9c?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1490114538077-0a7f8cb49891?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1512484580809-b5251c5df9dd?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1515734392280-e60c25eb9f01?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1547355332-7c6fcb397868?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://images.unsplash.com/photo-1525507119028-ed4c629a60a3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://plus.unsplash.com/premium_photo-1697183203538-08c30b0a6709?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OXx8b3V0Zml0fGVufDB8fDB8fHww");
        images.add("https://plus.unsplash.com/premium_photo-1673734625669-7ef119c3ef65?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTN8fG91dGZpdHxlbnwwfHwwfHx8MA%3D%3D");

        return images;
    }


    private void createDummyProduct(User user) {
        Faker faker = new Faker(new Locale("vi"));
        for (int i = 0; i < 20; i++) {


            Brand brand = Brand.builder()
                    .name(faker.company().name())
                    .homepageLink(faker.internet().url())
                    .description(faker.lorem().paragraph())
                    .thumbnail(generateImage())
                    .createdAt(new Date())
                    .build();
            Country country = new Country(); // Assuming Country is a simple entity, you need to set it up
            country.setName(faker.address().country());
            country.setCode(faker.book().genre());

            country = countryRepository.save(country);


            brand.setCountry(country);

            brand = brandRepository.save(brand);
            String productName = faker.commerce().productName();
            Product product = Product.builder()
                    .name(productName)
                    .description(faker.lorem().paragraph())
                    .thumbnail(generateImage())
                    .slug(StringUtil.convertToSlug(productName))
                    .country(country)
                    .topNotes(new HashSet<>())
                    .middleNotes(new HashSet<>())
                    .baseNotes(new HashSet<>())
                    .brand(brand)
                    .build();

            // Assuming you have a ProductRepository to save the Product entities
            product.setVotes(new HashSet<>());
            product.getVotes().add(user.getId());
            product.setGalleries(generateGallery());
            product.setOutfits(generateOutfit());


            ProductNote productNote1 = new ProductNote();
            productNote1.setName(faker.commerce().productName());
            productNote1.setEnName(faker.commerce().productName());
            productNote1.setSlug(faker.lorem().word());
            productNote1.setThumbnail("https://fimgs.net/mdimg/sastojci/t.105.jpg");
            ProductNote productNoteSaved1 = productNoteRepository.save(productNote1);

            ProductNote productNote2 = new ProductNote();
            productNote2.setName(faker.commerce().productName());
            productNote2.setEnName(faker.commerce().productName());
            productNote2.setSlug(faker.lorem().word());
            productNote2.setThumbnail("https://fimgs.net/mdimg/sastojci/m.373.jpg?1440724256");
            ProductNote productNoteSaved2 = productNoteRepository.save(productNote2);
            ProductNote productNote3 = new ProductNote();
            productNote3.setName(faker.commerce().productName());
            productNote3.setEnName(faker.commerce().productName());
            productNote3.setSlug(faker.lorem().word());
            productNote3.setThumbnail("https://fimgs.net/mdimg/sastojci/m.901.jpg?1598641517");
            ProductNote productNoteSaved3 = productNoteRepository.save(productNote3);

            product.setTopNotes(new HashSet<>());
            product.setMiddleNotes(new HashSet<>());
            product.setBaseNotes(new HashSet<>());

            product.getTopNotes().add(productNoteSaved1);
            product.getMiddleNotes().add(productNoteSaved1);
            product.getMiddleNotes().add(productNoteSaved2);
            product.getBaseNotes().add(productNoteSaved1);

            Product productSaved = productRepository.save(product);
            for (int j = 0; j < 20; j++) {
                Comment comment = commentRepository.save(Comment.builder().product(productSaved).user(user).content(faker.lorem().paragraph()).build());
                commentRepository.save(comment);
            }
            Random random = new Random();
            long min = 1000L;
            long max = 2000000L;
            long randomPrice = min + (long) (random.nextDouble() * (max - min));
            ProductPrice productPrice1 = ProductPrice.builder().labelType(LabelType.LISTED).isSearch(true).value(randomPrice).product(productSaved).priceType(PriceType.USD).link("https://parfumerie.vn/dior-sauvage-edp").build();
            ProductPrice productPrice2 = ProductPrice.builder().labelType(LabelType.VIETNAM_MARKET).isSearch(true).value(randomPrice).product(productSaved).priceType(PriceType.VND).link("https://parfumerie.vn/dior-sauvage-edp").build();

            productPriceRepository.save(productPrice1);
            productPriceRepository.save(productPrice2);


            CreateYearDto dto = new CreateYearDto(faker.number().numberBetween(1900, 2024));
            Year yearSaved = yearService.createYear(dto);

            productSaved.setDateReleased(yearSaved);
            productRepository.save(productSaved);
        }
    }


}
