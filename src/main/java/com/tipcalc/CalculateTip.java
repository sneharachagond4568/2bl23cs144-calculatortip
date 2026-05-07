package com.tipcalc; // This line fixes the 'Status 500' error

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculateTip")
public class CalculateTip extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // 1. Capture the data from index.html
            double bill = Double.parseDouble(request.getParameter("bill"));
            int tipPercent = Integer.parseInt(request.getParameter("tipPercent"));
            int people = Integer.parseInt(request.getParameter("people"));

            // 2. Logic & Calculations
            double tipAmount = bill * tipPercent / 100;
            double totalBill = bill + tipAmount;
            double perPerson = totalBill / people;

            // 3. Display the Result
            out.println("<html><body style='font-family: Arial; padding: 40px; text-align: center;'>");
            out.println("<div style='display: inline-block; border: 2px solid #3498db; padding: 20px; border-radius: 15px; background-color: #f9f9f9;'>");
            out.println("<h2 style='color: #2c3e50;'>Bill Summary</h2>");
            out.println("<p>Original Bill: <strong>$" + String.format("%.2f", bill) + "</strong></p>");
            out.println("<p>Tip (" + tipPercent + "%): <strong>$" + String.format("%.2f", tipAmount) + "</strong></p>");
            out.println("<p>Total Amount: <strong>$" + String.format("%.2f", totalBill) + "</strong></p>");
            out.println("<hr style='border: 1px solid #3498db;'>");
            out.println("<h2 style='color: #e74c3c;'>Each Person Pays: $" + String.format("%.2f", perPerson) + "</h2>");
            out.println("<br><a href='index.html' style='text-decoration: none; color: white; background: #3498db; padding: 10px 20px; border-radius: 5px;'>Back</a>");
            out.println("</div></body></html>");

        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error: Please enter valid numbers.</h3>");
            out.println("<a href='index.html'>Go Back</a>");
        }
    }
}