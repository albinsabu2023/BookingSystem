package com.booking.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/booking/*")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the path info after /booking, e.g., /bookings, /rooms, etc.
        String action = request.getPathInfo();  // Use getPathInfo to capture action from URL
        HttpSession session =request.getSession(false);
        if (action == null || action.equals("/")  ||session.getAttribute("userId").equals(null) ) {
            action = "/";  // Default action if no specific path is found
        }
     
        
        
        System.out.println("action----->" + action);
        
        // Match the action and forward the request to the correct page
        switch (action) {
            case "/bookings":
                showBookings(request, response);
                break;
            case "/rooms":
                showRooms(request, response);
                break;
            case "/feedbacks":
                showFeedbacks(request, response);
                break;
            default:
                showLandingPage(request, response);
                break;
        }
    }

    private void showRooms(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookingRoom/book.jsp");  // Correct path for the JSP inside booking folder
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Booking room page not found");
            e.printStackTrace();
        }
    }

    private void showLandingPage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/landingpage.jsp");  // Correct path for landing page
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Landing page not found");
            e.printStackTrace();
        }
    }

    private void showFeedbacks(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Feedbacks page called");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/feedbacks.jsp");  // Correct path for feedbacks page inside booking folder
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showBookings(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Bookings page called");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/bookingRoom/bookings.jsp");  // Correct path for bookings page inside booking folder
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Bookings page not found");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward POST requests to the same logic as GET
        doGet(request, response);
    }
}
