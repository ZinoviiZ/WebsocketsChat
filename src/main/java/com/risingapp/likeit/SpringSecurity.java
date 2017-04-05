package com.risingapp.likeit;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by zinoviyzubko on 5.04.17.
 */
//@Configuration
//@EnableWebSecurity
public class SpringSecurity { //} extends WebSecurityConfigurerAdapter {
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService);
////                .passwordEncoder(getShaPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/", "/home", "/rest/**").authenticated()
//                .and()
//                    .exceptionHandling().accessDeniedPage("/unauthorized")
//                .and()
//                    .addFilterBefore(new CustomUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                    .formLogin()
//                        .loginProcessingUrl("/login")
//                        .loginPage("/hello")
//                        .defaultSuccessUrl("/home")
//                        .failureUrl("/hello")
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .permitAll()
//                .and()
//                    .logout()
//                        .permitAll()
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/hello")
//                        .invalidateHttpSession(true);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources/**"); // #3
//    }
//
////    @Bean
////    public ShaPasswordEncoder getShaPasswordEncoder(){
////        return new ShaPasswordEncoder();
////    }
}