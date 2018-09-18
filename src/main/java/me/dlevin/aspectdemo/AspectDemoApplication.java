package me.dlevin.aspectdemo;

import me.dlevin.aspectdemo.model.Bucket;
import me.dlevin.aspectdemo.model.User;
import me.dlevin.aspectdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class AspectDemoApplication {

  private final UserRepository userRepository;

  @Autowired
  public AspectDemoApplication(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static void main(final String[] args) {
    SpringApplication.run(AspectDemoApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner commandLineRunner() {
    return args -> {
      final User user1 = new User("user1", "password1");
      final User user2 = new User("user2", "password2");

      final Bucket bucket1 = new Bucket("bucket1");
      final Bucket bucket2 = new Bucket("bucket2");

      user1.getAccessibleBuckets().add(bucket1);
      user2.getAccessibleBuckets().add(bucket2);

      this.userRepository.saveAll(Arrays.asList(user1, user2));
    };
  }

}
