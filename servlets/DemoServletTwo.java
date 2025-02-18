package com.rpwebcrafts.aem.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

/* ---> Sling all methods servlet <--- */

//      1. Path based servlet
//      2. Resource type based servlet

//      1. Path based servlet
// @Component(service = Servlet.class, immediate = true)
// @SlingServletPaths(value = { "/bin/demo/servlettwo" })
// public class DemoServletTwo extends SlingAllMethodsServlet {

//     @Override
//     protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
//             throws ServletException, IOException {
//         resp.getWriter().write(
//                 "This response is from DemoServletTwo class and SlingAllMethodsServlet & Path based servlet is used.");
//         resp.getWriter().write("This Response from GET Method");
//     }

//     @Override
//     protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
//             throws ServletException, IOException {
//         resp.getWriter().write(
//                 "This response is from DemoServletTwo class and SlingAllMethodsServlet & Path based servlet is used.");
//         resp.getWriter().write("This Response from POST Method");
//     }

//     @Override
//     protected void doPut(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
//             throws ServletException, IOException {
//         resp.getWriter().write(
//                 "This response is from DemoServletTwo class and SlingAllMethodsServlet & Path based servlet is used.");
//         resp.getWriter().write("This Response from PUT Method");
//     }

//     @Override
//     protected void doDelete(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
//             throws ServletException, IOException {
//         resp.getWriter().write(
//                 "This response is from DemoServletTwo class and SlingAllMethodsServlet & Path based servlet is used.");
//         resp.getWriter().write("This Response from DELETE Method");
//     }

// }

//----------------------------------------------------

//      2. Resource type based servlet
@Component(service = Servlet.class, immediate = true)
@SlingServletResourceTypes(resourceTypes = "codebase/admin")
public class DemoServletTwo extends SlingAllMethodsServlet {

        @Override
        protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
                        throws ServletException, IOException {
                resp.getWriter().write(
                                "This response is from DemoServletTwo class and SlingAllMethodsServlet & Resource Type Based servlet is used.");
                resp.getWriter().write("This Response from GET Method");
        }

        @Override
        protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
                        throws ServletException, IOException {
                resp.getWriter().write(
                                "This response is from DemoServletTwo class and SlingAllMethodsServlet & Resource Type Based servlet is used.");
                resp.getWriter().write("This Response from POST Method");
        }

        @Override
        protected void doPut(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
                        throws ServletException, IOException {
                resp.getWriter().write(
                                "This response is from DemoServletTwo class and SlingAllMethodsServlet & Resource Type Based servlet is used.");
                resp.getWriter().write("This Response from PUT Method");
        }

        @Override
        protected void doDelete(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
                        throws ServletException, IOException {
                resp.getWriter().write(
                                "This response is from DemoServletTwo class and SlingAllMethodsServlet & Resource Type Based servlet is used.");
                resp.getWriter().write("This Response from DELETE Method");
        }

}