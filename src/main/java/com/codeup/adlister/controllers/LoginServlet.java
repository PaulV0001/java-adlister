package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // make sure we find a user with that username
        // select * from users where username = ?
        User user = DaoFactory.getUsersDao().findByUsername(username);

        // check the submitted password against what you have in your database
        boolean validAttempt = user != null && user.getPassword().equals(password);

        if (validAttempt) {
            // store the logged in user object in the session
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
            // request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // if it's logged in
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}