package me.dlevin.aspectdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .httpBasic()
            .and()
            .csrf().disable()
            .headers().frameOptions().disable();
  }

}
