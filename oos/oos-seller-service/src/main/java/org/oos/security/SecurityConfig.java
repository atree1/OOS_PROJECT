package org.oos.security;


import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.java.Log;

// 시큐리티에 대한 설정을 담당한다

@EnableGlobalMethodSecurity(prePostEnabled = true)
//메소드의 pre post 잡아줌(이거 안넣으면 컨트롤러 @PreAuthorize 활성화못시킴)
@Log
@EnableWebSecurity // SecurityConfig를 인식되게 한다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
									// 설정 담당 클래스

	@Override
	//웹과 관련된 보안 설정
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").permitAll()
		.antMatchers("/adminNotify/**", "/user/**","/user/mypage/**").hasRole("SELLER")
				 .and().csrf().disable();
		
		http.formLogin().loginPage("/seller/login");
		// access denied 걸리면  로그인페이지 갈거라고 선언

		http.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/main");
		
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/**");
	}
	
}
