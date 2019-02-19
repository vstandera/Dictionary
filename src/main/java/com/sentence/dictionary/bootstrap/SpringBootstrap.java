package com.sentence.dictionary.bootstrap;

import com.sentence.dictionary.config.SecurityJavaConfig;
import com.sentence.dictionary.domain.Role;
import com.sentence.dictionary.domain.User;
import com.sentence.dictionary.repositories.RoleRepository;
import com.sentence.dictionary.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class SpringBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SecurityJavaConfig securityJavaConfig;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initUserAndRole();
    }

    private void initUserAndRole(){
        Role roleUser = new Role();
        roleUser.setRole("ROLE_USER");
        roleUser = roleRepository.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setRole("ROLE_ADMIN");
        roleAdmin = roleRepository.save(roleAdmin);

        User user1 = new User();
        user1.setUsername("vasek");
        user1.setPassword(securityJavaConfig.encoder().encode("vasek"));
        user1.addRole(roleAdmin);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("kamca");
        user2.setPassword(securityJavaConfig.encoder().encode("kamca"));
        user2.addRole(roleAdmin);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("pepa");
        user3.setPassword(securityJavaConfig.encoder().encode("pepa"));
        user3.addRole(roleAdmin);
        userRepository.save(user3);

        User user4 = new User();
        user4.setUsername("user");
        user4.setPassword(securityJavaConfig.encoder().encode("user"));
        user4.addRole(roleUser);
        userRepository.save(user4);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(securityJavaConfig.encoder().encode("admin"));
        admin.addRole(roleAdmin);
        userRepository.save(admin);

    }
}
