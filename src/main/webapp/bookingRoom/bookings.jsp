<%@page import="com.booking.model.Booking"%>
<%@page import="com.booking.dao.BookingDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.booking.dbconnect.DBConnect"%>
<%@page import="com.booking.dao.*"%>
<%@page import="com.booking.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.*" %>

<html>
<head>
<title>MeetNspace Dashboard</title>
<style><%@include file="../css/book.css"%></style>
</head>
<body>
    <div class="sidebar" id="sidebar">
        <div class="sidebar-header">
            <div class="sidebar-title">meetNspace</div>
            <button class="toggle-btn" id="toggleSidebar">≡</button>
        </div>

        <ul class="menu-list">
            <a href="#">
                <li class="menu-item " data-tooltip="Dashboard"><span class="menu-icon">📊</span> 
                <span class="menu-text">Dashboard</span></li>
            </a>
            <a href="${pageContext.request.contextPath}/bookings">
                <li class="menu-item active" data-tooltip="View Bookings"><span class="menu-icon">🔍</span> 
                <span class="menu-text">Booking History</span></li>
            </a>
            <a href="${pageContext.request.contextPath}/rooms">
                <li class="menu-item" data-tooltip="Add Room Details"><span class="menu-icon">➕</span> 
                <span class="menu-text">Create Booking</span></li>
            </a>
            <a href="">
                <li class="menu-item" data-tooltip="Feedbacks"><span class="menu-icon">📝</span> 
                <span class="menu-text">Feedbacks</span></li>
            </a>
            <div class="divider"></div>
            <a href="#">
                <li class="menu-item" data-tooltip="Profile"><span class="menu-icon">👤</span> 
                <span class="menu-text">Profile</span></li>
            </a>
            <div class="divider"></div>
            <a href="#">
                <li class="menu-item" data-tooltip="Logout"><span class="menu-icon">↩️</span> 
                <span class="menu-text">Logout</span></li>
            </a>
        </ul>
    </div>

    <button class="mobile-toggle" id="mobileToggle">☰</button>

    <div class="content" id="content">
        <h2>Your Booking History</h2>

        <!-- Navbar -->
        <div class="history-header">
            <div class="navbar">
                <button class="tab-btn active" data-tab="allBookings">All Bookings</button>
                <button class="tab-btn" data-tab="activeBookings">Active Bookings</button>
                <button class="tab-btn" data-tab="cancelledBookings">Cancelled Bookings</button>
            </div>
            <div class="search-container">
                <input type="text" id="searchBooking" placeholder="Enter Booking ID...">
                <button id="searchBtn">Search</button>
                <button id="clearSearchBtn" style="display: none;">Clear</button>
            </div>
        </div>

        <div id="allBookings" class="tab-content active">
            <h4>All Bookings</h4>
            <table>
                <thead>
                    <tr>
                        <th>Booking Id</th>
                        <th>Room</th>
                        <th>Location</th>
                        <th>Capacity</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Snacks</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Sample Data (Replace with actual database query results)
                        List<Booking> bookings = new ArrayList<>();
                        Connection con=DBConnect.getConnection();
                        bookings=BookingDAO.getBookings(con); 
                        for (Booking b : bookings) {
                    %>
                    <tr>
                        <td><%= b.getBookingId() %></td>
                        <td><%=b.getEmpId()  %></td>
                        <td><%= b.getRoomId()%></td>
                        <td><%= b.getStatus()%></td>

                        <td><%= (Date)b.getBookDate()%></td>
                        <td><%= b.getFromTime()%></td>
                        <td><%=b.getToTime() %></td>
                        <td><button>Edit</button> <button>Delete</button></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <!-- Modal -->
        <div id="editModal" class="modal">
            <div class="modal-content">
                <span class="close" id="close">&times;</span>
                <h2>Edit Booking</h2>
                <label>Room Name:</label> <input type="text" id="editRoomName">
                <label>Location:</label> <input type="text" id="editLocation">
                <label>Capacity:</label> <input type="number" id="editCapacity">
                <label>Date:</label> <input type="date" id="editDate"> 
                <label>From Time:</label> <input type="time" id="editFromTime"> 
                <label>To Time:</label> <input type="time" id="editToTime"> 
                <label><input type="checkbox" id="editSnacks"> Snacks Needed</label>
                <button id="saveEdit">Save Changes</button>
            </div>
        </div>

        <div id="customPopup" class="custom-popup">
            <div class="popup-content">
                <span class="popup-close" id="closePopup">&times;</span>
                <p id="popupMessage"></p>
                <div class="popup-buttons">
                    <button id="popupConfirm" class="popup-btn confirm-btn">OK</button>
                    <button id="popupCancel" class="popup-btn cancel-btn">Cancel</button>
                </div>
            </div>
        </div>

        <script>
            // Get elements
            const sidebar = document.getElementById('sidebar');
            const content = document.getElementById('content');
            const toggleBtn = document.getElementById('toggleSidebar');
            const mobileToggle = document.getElementById('mobileToggle');
            const menuItems = document.querySelectorAll('.menu-item');
            
            // Toggle sidebar
            toggleBtn.addEventListener('click', () => {
                sidebar.classList.toggle('collapsed');
                content.classList.toggle('expanded');
            });
            
            // Mobile toggle
            mobileToggle.addEventListener('click', () => {
                sidebar.classList.toggle('mobile-open');
            });
            
            // Add click event to menu items
            menuItems.forEach(item => {
                item.addEventListener('click', function() {
                    // Remove active class from all menu items
                    menuItems.forEach(item => item.classList.remove('active'));
                    
                    // Add active class to clicked item
                    this.classList.add('active');
                    
                    // Close sidebar on mobile after clicking
                    if (window.innerWidth <= 768) {
                        sidebar.classList.remove('mobile-open');
                    }
                });
            });
            
            // Handle window resize
            window.addEventListener('resize', () => {
                if (window.innerWidth <= 768) {
                    sidebar.classList.remove('collapsed');
                    content.classList.remove('expanded');
                }
            });
            document.getElementById("closeBooking")
            .addEventListener("click", function () {
              document.getElementById("bookingModal").style.display = "none";
            });
        </script>
      
    </div>
</body>
</html>
