package com.loctran.service;

import com.github.javafaker.Faker;
import com.loctran.service.entity.brand.Brand;
import com.loctran.service.entity.country.Country;
import com.loctran.service.entity.media.Media;
import com.loctran.service.entity.media.MediaType;
import com.loctran.service.entity.product.Product;
import com.loctran.service.entity.product.ProductRepository;
import com.loctran.service.entity.productNote.ProductNote;
import com.loctran.service.entity.user.Role;
import com.loctran.service.entity.user.User;
import com.loctran.service.entity.user.UserRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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



  private void createDummyProduct() {
    Faker faker = new Faker(new Locale("vi"));
    for (int i = 0; i < 100; i++) {
      Media media = Media.builder()
          .path(faker.file().fileName())
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
      Product product = Product.builder()
          .name(faker.commerce().productName())
          .description(faker.lorem().paragraph())
          .thumbnail(media)
          .dateReleased(faker.number().numberBetween(1900, 2024))
          .slug(faker.lorem().word())
          .country(country)
          .brand(brand)
          .build();

      // Assuming you have a ProductRepository to save the Product entities
      productRepository.save(product);
    }
  }




}
