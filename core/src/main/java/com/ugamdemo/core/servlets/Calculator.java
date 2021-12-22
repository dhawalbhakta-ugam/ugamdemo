package com.ugamdemo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths(
        value = {"/bin/calc"}
)
public class Calculator extends SlingAllMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException, IOException {
        int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
        int secondNumber = Integer.parseInt(req.getParameter("secondNumber"));
        int answer = firstNumber+secondNumber;
        resp.getWriter().print(answer);
    }

}
