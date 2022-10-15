
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<jsp:useBean id="loan" class="com.mycompany.pai2.LoanBean" scope="session" />
<jsp:setProperty name="loan" property="*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Kalkulator rat</h1>
        <form method="post" action="calcBean.jsp">
            <label for="loanAmount">Kwota pożyczki:</label>
            <input type="text" name="loanAmount"><br>
            <label for="yearlyPercentage">Procent roczny:</label>
            <input type="text" name="yearlyPercentage"><br>
            <label for="installmentNumber">Liczba rat:</label>
            <input type="text" name="installmentNumber"><br>
            <input type="submit" name="submit" value="Wyślij">
        </form> 
        Rata miesięczna: <input name='kwota' value="<%= loan.getMonthlyInstallment() %>">
    </body>
</html>
