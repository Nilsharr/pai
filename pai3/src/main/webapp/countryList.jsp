<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.pai3.CountryBean"%>
<jsp:useBean id="countries" class="com.mycompany.pai3.CountryBean" scope="session" />
<jsp:setProperty name="countries" property="*" />

<% 
    ArrayList<CountryBean> list = (ArrayList<CountryBean>)session.getAttribute("list");
    if(list == null){
        list = new ArrayList<>();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Kraje Europy</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Code</th>
            <th>Population</th>
            <th>Details</th>
        </tr>
        <%for (CountryBean country:list){ %>
        <tr>
            <td><%= country.getName()%></td>
            <td><%= country.getCode()%></td>
            <td><%= country.getPopulation()%></td>
            <td><a href="details.jsp?id=<%=list.indexOf(country)%>">Details</a></td>
        </tr>
      <%}%>
    </table>
    </body>
</html>