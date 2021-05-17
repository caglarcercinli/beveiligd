package be.vdab.beveiligd2.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String MANAGER = "manager";
    private static final String HELPDESKMEDEWERKER = "helpdeskmedewerker";
    private static final String MAGAZIJNIER = "magazijnier";
    private final DataSource dataSource;

    SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //auth.jdbcAuthentication().dataSource(dataSource);
        auth.inMemoryAuthentication()
                .withUser("joe")
                .password("{noop}theboss")
                .authorities(MANAGER).and()
                .withUser("averell")
                .password("{noop}hungry")
                .authorities(HELPDESKMEDEWERKER, MAGAZIJNIER);


    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().mvcMatchers("/images/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(login->login.loginPage("/login"));
        http.authorizeRequests(requests -> requests.mvcMatchers("/offertes/**")
                .hasAuthority(MANAGER)
                .mvcMatchers("/werknemers/**")
                .hasAnyAuthority(MAGAZIJNIER, HELPDESKMEDEWERKER));
        http.logout(logout->logout.logoutSuccessUrl("/"));
    }
}
