package com.crm.realestatecrm.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.crm.realestatecrm.entity.Logs;
import com.crm.realestatecrm.service.LogsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomeSuccessHandler implements AuthenticationSuccessHandler{
	
	private LogsService logsService;
	
	@Autowired
	public CustomeSuccessHandler(LogsService logsService) {
		this.logsService = logsService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		
		
		Logs logs = new Logs();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String name = authentication.getName();
        String redirectUrl = "/dashboard";

        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();

            if (role.equals("ROLE_ADMIN")) {
            	logs.setAction("Login");
            	logs.setDetials("Admin LogedIn!");
            	logs.setEmail(name);
            	logsService.save(logs);
                redirectUrl = "/admin"; 
                break;
            } else if (role.equals("ROLE_MANAGER")) {
            	logs.setAction("Login");
            	logs.setDetials("Manager LogedIn!");
            	logs.setEmail(name);
            	logsService.save(logs);
                redirectUrl = "/dashboard";
                break;
            }
            else if (role.equals("ROLE_SALES")) {
            	logs.setAction("Login");
            	logs.setDetials("Sales LogedIn!");
            	logs.setEmail(name);
            	logsService.save(logs);
                redirectUrl = "/dashboard";
                break;
            }
        }

        response.sendRedirect(request.getContextPath() + redirectUrl);
    
		
	}

}
