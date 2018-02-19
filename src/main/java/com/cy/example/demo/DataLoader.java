package com.cy.example.demo;

import com.cy.example.demo.Role;
import com.cy.example.demo.RoleRepository;
import com.cy.example.demo.User;
import com.cy.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        roleRepository.save(new Role("APPLICANT"));
        roleRepository.save(new Role("EMPLOYER"));
        roleRepository.save(new Role("ADMIN"));

        Role applicantRole = roleRepository.findByRole("APPLICANT");
        Role employerRole = roleRepository.findByRole("EMPLOYER");
        Role adminRole = roleRepository.findByRole("ADMIN");

        // Add user roles
        User user = new User("bob@burger.com", "password", "Bobby", "Burger", true, "employer");
        user.setRoles(Arrays.asList(employerRole));
        userRepository.save(user);

        user = new User("jane@virgin.com", "password", "Jane", "Virgin", true, "applicant");
        user.setRoles(Arrays.asList(applicantRole));
        userRepository.save(user);

        user = new User("jerry@smith.com", "password", "Jerry", "Smith", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);




    }
}
