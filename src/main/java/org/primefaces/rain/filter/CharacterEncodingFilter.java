/**
 *  Copyright 2009-2020 PrimeTek.
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.primefaces.rain.filter;

import multiTenantConnection.MultiTenantIdentifierResolverImpl;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CharacterEncodingFilter implements Filter {

    int i = 0;// passage lors d  une erreur servletException

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        try {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
//        HttpSession session = ((HttpServletRequest) req).getSession(false);
//        if (session != null) {
//            String currentTenant = (String) session.getAttribute("currentTenant");
//            if (!currentTenant.isEmpty()) {
//                MultiTenantIdentifierResolverImpl.tenantIdentifier.set(currentTenant);
//            }
//        }
//        } catch (ServletException e ) {
////            if (i == 0 || i<=2) {
//                HttpServletRequest request = (HttpServletRequest) req;
//                HttpServletResponse response = (HttpServletResponse) resp;
//                response.sendRedirect(request.getContextPath() + request.getServletPath());
//                i++;
////            }
//
//        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void destroy() {

    }

}
