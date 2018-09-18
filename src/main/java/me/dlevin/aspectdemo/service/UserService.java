package me.dlevin.aspectdemo.service;

import me.dlevin.aspectdemo.model.User;
import me.dlevin.aspectdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> find(final String username) {
    return this.userRepository.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return this.find(username)
            .map(user -> org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .passwordEncoder(this.passwordEncoder::encode)
                    .roles("ADMIN")
                    .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException(username));
  }

}
