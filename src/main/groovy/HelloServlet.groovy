import javax.servlet.http.*
import com.google.appengine.api.users.UserService
import com.google.appengine.api.users.User
import com.google.appengine.api.users.UserServiceFactory

class HelloServlet extends HttpServlet {
  void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.contentType = "text/plain"
    resp.writer.println "Hello Google App Engine Groovy!"
    resp.writer.println new GroovyShell().evaluate("3*4")


    UserService userService = UserServiceFactory.getUserService();
      User user = userService.getCurrentUser();

      if (user != null) {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, " + user.getNickname());
      } else {
        resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
      }
  }
}
