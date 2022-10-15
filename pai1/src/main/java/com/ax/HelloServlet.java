package com.ax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {
    String data1;

    @Override
    public void init() throws ServletException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        data1 = dateFormat.format(new Date());
    } 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloServlet at " + request.getContextPath() + "</h1>");
            out.println("<h2>Dane serwera</h2>");
            out.println("<p>request.getServerName(): " + request.getServerName() + "</p>");
            out.println("<p>request.getServerPort(): " + request.getServerPort() + "</p>");
            out.println("<p>request.getRemoteHost(): " + request.getRemoteHost() + "</p>");
            out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");
            out.println("<h2>Szczegóły żądania</h2>");
            out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
            out.println("<p>request.getQueryString(): " + request.getQueryString() + "</p>");
            out.println("<p>data z processRequest " + data1 + "</p>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
