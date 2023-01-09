package com.example.demo.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.model.UserDetail;
import com.example.demo.utils.RoleEnum;

@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	protected final Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,final HttpServletResponse response,
			final Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);
        clearAuthenticationAttributes(request);

	}
	
    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    protected String determineTargetUrl(final Authentication authentication) {
    	boolean isAdmin = false;
        boolean isOwner = false;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(RoleEnum.ROLE_OWNER.name())) {
                isOwner = true;

            }else if (grantedAuthority.getAuthority().equals(RoleEnum.ROLE_ADMIN.name())) {
                isOwner = false;
                isAdmin = true;
                break;
            }
        }
        
        if (isOwner) {
       	 String username;
            if (authentication.getPrincipal() instanceof UserDetail) {
            	username = ((UserDetail)authentication.getPrincipal()).getUsername();
            }
            else {
            	username = authentication.getName();
            }

           return "/owner/index?email="+username;

       }else if (isAdmin) {
       	String username;
           if (authentication.getPrincipal() instanceof UserDetail) {
           	username = ((UserDetail)authentication.getPrincipal()).getUsername();
           }
           else {
           	username = authentication.getName();
           }
           return "/admin/index?user="+username;
       }else {
           throw new IllegalStateException();
       }
    }


    
    protected final void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
