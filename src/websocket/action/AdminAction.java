package websocket.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import websocket.entity.DataResult;

@Controller
public class AdminAction
{
  private DataResult result;
  
  @SuppressWarnings("unchecked")
public String manageOnlineUsers()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    this.result = new DataResult();
    this.result.setData((List<String>)request.getServletContext().getAttribute(
      "onlineUsers"));
    this.result.setStatus(0);
    return "success";
  }
  
  public String forceOutlineUser()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String username = request.getParameter("username");
    this.result = new DataResult();
    removeOnlineUser(request, username);
    this.result.setStatus(0);
    return "success";
  }
  
  private synchronized void removeOnlineUser(HttpServletRequest request, String username)
  {
    @SuppressWarnings("unchecked")
	List<String> list = (List<String>)request.getServletContext()
      .getAttribute("onlineUsers");
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      if (((String)it.next()).equals(username))
      {
        it.remove();
        break;
      }
    }
    request.getServletContext().removeAttribute(username);
    request.getServletContext().setAttribute("onlineUsers", list);
  }
  
  public DataResult getResult()
  {
    return this.result;
  }
  
  public void setResult(DataResult result)
  {
    this.result = result;
  }
}
