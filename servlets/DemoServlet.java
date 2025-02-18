package com.rpwebcrafts.aem.core.servlets;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.Servlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
// import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

/* ---> Sling safe methods servlet <--- */

//      1. Path based servlet
//      2. Resource type based servlet



//      1. Path based servlet
// @Component(service = Servlet.class, immediate = true)
// @SlingServletPaths(value = { "/bin/demo/servlet" })
// public class DemoServlet extends SlingSafeMethodsServlet {

//     @Override
//     protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
//             throws ServletException, IOException {
//         resp.getWriter().write(
//                 "This response is from DemoServlet class and SlingSafeMethodsServlet & Path based servlet is used.");
//     }

// }

//      2. Resource type based servlet
@Component(service = Servlet.class, immediate = true)
@SlingServletResourceTypes(resourceTypes = "main/user")
public class DemoServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().write(
                "This response is from DemoServlet class and SlingSafeMethodsServlet & Resource type based servlet is used.");
    }

}