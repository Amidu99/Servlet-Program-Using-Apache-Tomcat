package lk.ijse.task5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mapping",urlPatterns = "/map/*")
public class Mapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello Mapping Started..");
        System.out.println("Context path: " + req.getContextPath());
        System.out.println("Path Info: " + req.getPathInfo());
        System.out.println("Servlet Path: " + req.getServletPath());
        System.out.println("Path Translated: " + req.getPathTranslated());
        System.out.println("Query String: " + req.getQueryString());
        System.out.println("Request URI: " + req.getRequestURI());
        System.out.println("Request URL: " + req.getRequestURL());
        System.out.println("Protocol: " + req.getProtocol());
        System.out.println("Scheme: " + req.getScheme());
        System.out.println("Remote Host: " + req.getRemoteHost());
        System.out.println("Remote Port: " + req.getRemotePort());
        System.out.println("Remote Address: " + req.getRemoteAddr());
        System.out.println("Server Name: " + req.getServerName());
        System.out.println("Server Port: " + req.getServerPort());
        System.out.println("Local Name: " + req.getLocalName());
        System.out.println("Local Address: " + req.getLocalAddr());
        System.out.println("Local port: " + req.getLocalPort());
        System.out.println("Method type: " + req.getMethod());
    }
}