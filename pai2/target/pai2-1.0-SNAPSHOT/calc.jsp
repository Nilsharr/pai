
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errorPage.jsp"%>

<% 
DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
String date = df.format(new Date());
%>

<% 
String result = "";
if (request.getParameter("submit") != null){  
    //try {
        Double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        Double yearlyPercentage = Double.parseDouble(request.getParameter("yearlyPercentage")) / 100;
        Double installmentNumber = Double.parseDouble(request.getParameter("installmentNumber"));
        Double monthlyPercentage = yearlyPercentage / 12;
        
        Double monthlyInstallment = (loanAmount * monthlyPercentage) / (1 - 1 / Math.pow(1 + monthlyPercentage, installmentNumber));
        DecimalFormat decimalFormatter = new DecimalFormat("#.00");
        result = "Rata miesięczna: " + decimalFormatter.format(monthlyInstallment);
    //}
    //catch (NumberFormatException ex) { }
}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%= date %>
        <h1>Kalkulator rat</h1>
        <form method="post" action="calc.jsp">
            <label for="loanAmount">Kwota pożyczki:</label>
            <input type="text" name="loanAmount"><br>
            <label for="yearlyPercentage">Procent roczny:</label>
            <input type="text" name="yearlyPercentage"><br>
            <label for="installmentNumber">Liczba rat:</label>
            <input type="text" name="installmentNumber"><br>
            <input type="submit" name="submit" value="Wyślij">
        </form> 
        <%= result %>
    </body>
</html>
