import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

@WebServlet(name="/SignupServlet",urlPatterns = {"/signup"})

public class SignupServlet extends HttpServlet {

    private static final Map<String, String> users = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");
        users.put(username, password);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Signup successful");
        out.println("Login below");
        response.sendRedirect("index.jsp");

    }

    public static boolean isUserValid(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
