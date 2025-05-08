package com.teste.castgroup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//
//
//        http.cors().disable();
//        http.headers().frameOptions().disable();
//        http.csrf().disable();
//
//        http.authorizeRequests().antMatchers(
//          "/agencias/**",
//          "/contas/cadastro/**"
//          ).hasRole("ADMIN");
//
//        http.authorizeRequests().antMatchers(
//          "/contas/credito/**",
//          "/contas/creditar/**",
//          "/contas/debito/**",
//          "/contas/debitar/**",
//          "/contas/transferencia/**",
//          "/contas/transferir/**",
//          "/contas/busca/**",
//          "/contas/buscar/**")
//                .permitAll();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().anyRequest().authenticated();
//        http.addFilter(customAuthenticationFilter);
//
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//}