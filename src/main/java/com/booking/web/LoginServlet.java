package com.booking.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import com.booking.dao.LoginDAO;
import com.booking.dbconnect.DBConnect;
import com.booking.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("action: " + action);
       
        if ("/login".equals(action)) {
            response.sendRedirect(request.getContextPath()+"/login/login.jsp");
        } else if ("/register".equals(action)) {
        	System.out.println("get for register called");
            response.sendRedirect(request.getContextPath()+"/login/register.jsp");
        } else if("/logout".equals(action)) {
        	System.out.println("logout succesfully");
        	HttpSession session=request.getSession();
        	session.invalidate();
        	response.sendRedirect(request.getContextPath()+"/landingpage.jsp");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/landingpage.jsp");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("username");
        String psd = request.getParameter("password");
        
        
        System.out.println(new User(id,name,psd));
        Connection con = DBConnect.getConnection();
        boolean userExist = LoginDAO.authenticate(con, new User(id, psd));

        if ("/login".equals(action)) {
            if (userExist ) {
                if (LoginDAO.isAdmin(con, new User(id, name, psd))) {
                    action = "/admindashBoard";
                } else {
                    action = "/authenticate";
                }
            }
        }

        if (action == null) {
            action = "/";
        }
        

        System.out.println("action----->" + action);

        switch (action) {
            case "/register":
                register(request, response ,new User(id,name,psd));
                break;
            case "/authenticate":
                authenticate(request, response ,new User(id,psd));
                break;
            case "/admindashBoard":
                adminDashBoard(request, response);
                break;
           
            default:
                showLandingPage(request, response);
                break;
        }
    }

   

	private void adminDashBoard(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/adminDashboard.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println("Error redirecting to admin dashboard page");
            e.printStackTrace();
        }
    }

    private void showLandingPage(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("landingpage.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("Page not found");
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response,User u) {
    		Connection con=DBConnect.getConnection();
    		boolean ans=LoginDAO.authenticate(con, u);
    		System.out.println("ans of auth--->"+ans);
    		HttpSession session=request.getSession();
    		session.setAttribute("userId",u.getEmpId() );
    		session.setMaxInactiveInterval(30 * 60); 
    		System.out.println("called auth");    		
        try {
        	if(ans) response.sendRedirect(request.getContextPath()+"/booking/bookings.jsp");
        	else   response.sendRedirect(request.getContextPath()+"/login/login.jsp");
        } catch ( IOException e) {
            System.out.println("Page not found");
            e.printStackTrace();
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response,User u) {
    	System.out.println("register page called");
    	Connection con=DBConnect.getConnection();
       String ans= LoginDAO.register(con, u);
        System.out.println("ans----->"+ans);
        try {
			response.sendRedirect(request.getContextPath()+"/login/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error redirecting to register page");
			e.printStackTrace();
		}
       
    }
}
