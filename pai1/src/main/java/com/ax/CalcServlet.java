
package com.ax;

import com.ax.Enums.MathOperator;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {
    private final String CALCULATOR_HISTORY_SESSION_ATTRIBUTE = "calculatorHistory";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String clearHistory = request.getParameter("clearCalculatorHistory");
        HttpSession session = request.getSession();
        
        String calcResult = "";
        ArrayList<String> calcHistory = new ArrayList();        
               
        // redirect to form
        /*if(clearHistory != null && clearHistory.equals("true")){
            session.removeAttribute(CALCULATOR_HISTORY_SESSION_ATTRIBUTE); 
            response.sendRedirect("/pai1");
            return;
        }*/
        
        if(clearHistory != null && clearHistory.equals("true")){
            session.removeAttribute(CALCULATOR_HISTORY_SESSION_ATTRIBUTE); 
        }
        else{
            calcResult = getCalculateResult(request);
            calcHistory = getCalcHistory(session);
        }
               
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Kalkulator</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(getUserGreeting(request, response));
            out.println("<a href=\"index.html\">Powrót do formularza</a>&ensp;");
            out.println("<a href=\"CalcServlet?clearCalculatorHistory=true\">Wyczyść historię sesji</a>");
            out.println("<h1>Wynik obliczeń:</h1>");
            out.println(calcResult);
            out.println("<h1>Historia obliczeń z sesji:</h1>");
            for(String calc: calcHistory){
               out.println(calc + "<br>"); 
            }
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
    
    private String getCalculateResult(HttpServletRequest request){
        String result;
        String n1 = request.getParameter("num1");
        String n2 = request.getParameter("num2");
        
        if(n1 == null || n2 == null || n1.trim().equals("") || n2.trim().equals("")){
            result = "Błędne dane!";
        }
        else{           
            double num1, num2;
        
            try{
                num1 = Double.parseDouble(n1);        
                num2 = Double.parseDouble(n2);
                MathOperator mop = MathOperator.valueOf(request.getParameter("op").toUpperCase());
        
                if(mop == MathOperator.DIVIDE && num2 == 0){            
                    result = formatResult(num1, num2, "Nie dziel przez zero!", mop);
                }  
                else{
                    double res = mop.apply(num1, num2);
                    result = formatResult(num1, num2, res, mop);
                }
            }
            catch(NumberFormatException e){
                result = "Błędne dane!";
            }                                     
        }
        
        updateCalcHistory(request.getSession(), result);
        return result;
    }
    
    private void updateCalcHistory(HttpSession session, final String newResult){
        ArrayList<String> calcHistory = (ArrayList<String>)session.getAttribute(CALCULATOR_HISTORY_SESSION_ATTRIBUTE);
        if(calcHistory == null){
            calcHistory = new ArrayList(){{add(newResult);}};
        }
        else{
            calcHistory.add(newResult);
        }      
        session.setAttribute(CALCULATOR_HISTORY_SESSION_ATTRIBUTE, calcHistory);
    }
    
    private ArrayList<String> getCalcHistory(HttpSession session){
        ArrayList<String> calcHistory = (ArrayList<String>)session.getAttribute(CALCULATOR_HISTORY_SESSION_ATTRIBUTE);
        return calcHistory == null ? new ArrayList<String>() : calcHistory;     
    }
    
    private String getUserGreeting(HttpServletRequest request, HttpServletResponse response){
        String cookieName = "UserId";
        Cookie[] cookies = request.getCookies();
        if (cookies != null){ 
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())){
                    return "<h2>Witaj po raz kolejny </h2>";
                }
            }
        }
        response.addCookie(new Cookie(cookieName, UUID.randomUUID().toString()));
        return "<h2>Witaj po raz pierwszy</h2>";
    }
    
    private String formatResult(double n1, double n2, double res, MathOperator op){
        DecimalFormat df = getDecimalFormatter();   
        return String.format("%s %s %s = %s", df.format(n1), op.getSymbol(), df.format(n2),df.format(res));
    }     
    
    private String formatResult(double n1, double n2, String res, MathOperator op){
        DecimalFormat df = getDecimalFormatter();
        return String.format("%s %s %s = %s", df.format(n1), op.getSymbol(), df.format(n2), res);
    }
    
    private DecimalFormat getDecimalFormatter(){
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        return df;
    }
}

