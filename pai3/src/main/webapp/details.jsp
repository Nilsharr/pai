<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.pai3.CountryBean"%>
<jsp:useBean id="countries" class="com.mycompany.pai3.CountryBean" scope="session" />
<jsp:setProperty name="countries" property="*" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <% 
        ArrayList<CountryBean> list = (ArrayList<CountryBean>)session.getAttribute("list");
        if(list != null){
            int id = Integer.parseInt(request.getParameter("id"));       
            CountryBean country = list.get(id);
            out.println("Details of " + country.getName() + "<br>");
            out.println("County code: " + country.getCode() + "<br>");
            out.println("Population: " + country.getPopulation() + "<br>");
            out.println("Surface area: " + country.getSurface() + "<br><br>");
        }
        else{
            out.println("No data <br><br>");
        }
    %>
        <a href="./ListServlet">Country List</a>
    </body>
</html>
