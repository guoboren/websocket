package websocket.servlet;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AdminServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  public void init(ServletConfig config)
    throws ServletException
  {
    List<String> list = new ArrayList<String>();
    config.getServletContext().setAttribute("onlineUsers", list);
    System.out.println(list);
  }
}
