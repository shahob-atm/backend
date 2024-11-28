package org.example.backend.config;

import lombok.RequiredArgsConstructor;
import org.example.backend.entity.*;
import org.example.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepo categoryRepo;
    private final FoodRepo foodRepo;
    private final PostRepo postRepo;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepo.findAll().isEmpty()) {

            Role roleAdmin = Role.builder().name("ROLE_ADMIN").build();
            Role roleUser = Role.builder().name("ROLE_USER").build();
            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);
        }

        List<User> userList = List.of(
                User.builder().roles(roleRepo.findAllByName("ROLE_USER")).firstName("Dilshod").lastName("Jo'rayev").enabled(true).password(passwordEncoder.encode("123")).username("dilshod").build(),
                User.builder().roles(roleRepo.findAllByName("ROLE_USER")).firstName("Javohir").lastName("Jamolov").enabled(true).password(passwordEncoder.encode("123")).username("java").build(),
                User.builder().roles(roleRepo.findAllByName("ROLE_USER")).firstName("Shahruz").lastName("Sharipov").enabled(true).password(passwordEncoder.encode("123")).username("shaha").build()
        );

        List<Category> categoryList = List.of(
                Category.builder().name("Grill").build(),
                Category.builder().name("Salad").build(),
                Category.builder().name("Burger").build(),
                Category.builder().name("Soup").build()
        );

        List<Food> foodList = List.of(
                Food.builder().name("Vegie Muffen").price(16d).description("There are many things are needed to start the Fast Food Business.").category(categoryList.get(0)).imageUrl("vegie_muffen.png").build(),
                Food.builder().name("Salads").price(12d).description("There are many things are needed to start the Fast Food Business.").category(categoryList.get(1)).imageUrl("salads.png").build(),
                Food.builder().name("Burger").price(15d).description("There are many things are needed to start the Fast Food Business.").category(categoryList.get(2)).imageUrl("burger.png").build(),
                Food.builder().name("Peach Melba dish").price(18d).description("There are many things are needed to start the Fast Food Business.").category(categoryList.get(3)).imageUrl("peach_melba_dish.png").build(),
                Food.builder().name("Egg Masala").price(18d).description("There are many things are needed to start the Fast Food Business.").category(categoryList.get(1)).imageUrl("egg_masala.png").build(),
                Food.builder().name("Delmonica Steak dish").price(18d).description("There are many things are needed to start the Fast Food Business.").category(categoryList.get(0)).imageUrl("delmonica_steak_dish.png").build()

        );

        List<Post> postList = List.of(
                Post.builder().title("Making Food great again and again").content("You need not only Just Food Stalls with Persons but also specialized equipment, Skills to manage Customers, Effective Product catlogues etc very successful to make your.").author(userList.get(0)).build(),
                Post.builder().title("Making Food great again and again").content("You need not only Just Food Stalls with Persons but also specialized equipment, Skills to manage Customers, Effective Product catlogues etc very successful to make your.").author(userList.get(1)).build(),
                Post.builder().title("Making Food great again and again").content("You need not only Just Food Stalls with Persons but also specialized equipment, Skills to manage Customers, Effective Product catlogues etc very successful to make your.").author(userList.get(2)).build(),
                Post.builder().title("Making Food great again and again").content("You need not only Just Food Stalls with Persons but also specialized equipment, Skills to manage Customers, Effective Product catlogues etc very successful to make your.").author(userList.get(2)).build(),
                Post.builder().title("Making Food great again and again").content("You need not only Just Food Stalls with Persons but also specialized equipment, Skills to manage Customers, Effective Product catlogues etc very successful to make your.").author(userList.get(1)).build()
        );

        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {

            User user = User.builder().roles(roleRepo.findAllByName("ROLE_ADMIN")).firstName("Diyor").lastName("Rustamov").enabled(true).password(passwordEncoder.encode("123")).username("diyor").build();
            userRepo.save(user);

            userRepo.saveAll(userList);

            categoryRepo.saveAll(categoryList);

            foodRepo.saveAll(foodList);

            postRepo.saveAll(postList);

        }

    }
}
