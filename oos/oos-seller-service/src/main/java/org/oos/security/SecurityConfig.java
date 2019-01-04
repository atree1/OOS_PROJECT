package org.oos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.Setter;
import lombok.extern.java.Log;

// 시큐리티에 대한 설정을 담당한다

@EnableGlobalMethodSecurity(prePostEnabled = true)
//메소드의 pre post 잡아줌(이거 안넣으면 컨트롤러 @PreAuthorize 활성화못시킴)
@Log
@EnableWebSecurity // SecurityConfig를 인식되게 한다
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// 설정 담당 클래스
	@Setter(onMethod_=@Autowired)
	DataSource dataSource;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// 설정 담당 클래스

	@Bean
	public UserDetailsService userDetailsService() {
		// Oosserservice를 빈으로 설정
		return new OosUserService();
	}

	
	@Override
	// 웹과 관련된 보안 설정
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/seller/*").permitAll()
		.antMatchers("/store/*", "/qna/*","/notify/*","/adminNotify/notify","/product/*","/exam")
<<<<<<< HEAD
		.hasAnyRole("SELLER","ADMIN")		
		.antMatchers("/admin/*","/adminNotify/modify","/adminNotify/register").hasRole("ADMIN");
=======
		.hasAnyRole("SELLER","ADMIN")
		.antMatchers("/admin/*","/adminNotify/modify","/adminNotify/register").hasRole("ADMIN");

>>>>>>> branch 'master' of https://github.com/atree1/OOS_PROJECT.git
		http.formLogin().loginPage("/seller/login").defaultSuccessUrl("/main");
		
		
		// access denied 걸리면 로그인페이지 갈거라고 선언
		http.rememberMe().key("seller")
		.userDetailsService(userDetailsService())
		.tokenRepository(getJDBCReopsitory())
		.tokenValiditySeconds(60*60*24*15);
		
		
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true)
		.deleteCookies("remember-me","JSESSION_ID").logoutSuccessUrl("/seller/login");

	}



	private PersistentTokenRepository getJDBCReopsitory() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

}
