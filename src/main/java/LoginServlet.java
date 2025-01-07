import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "/LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (SignupServlet.isUserValid(username, password)) {
            out.println("Login successful! Welcome, " + username + ".");
        } else {
            out.println("Invalid credentials.");
        }
    }
}
