package websocket.action;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import websocket.entity.DataResult;
import websocket.service.UserService;

@Controller
public class UserAction
{
  private DataResult result;
  @Resource
  private UserService userService;
  
  private synchronized void addOnlineUser(HttpServletRequest request, String username)
  {
    @SuppressWarnings("unchecked")
	List<String> list = (List<String>)request.getServletContext().getAttribute("onlineUsers");
    list.add(username);
    request.getServletContext().setAttribute("onlineUsers", list);
  }
  
  private synchronized void removeOnlineUser(HttpServletRequest request, String username)
  {
    @SuppressWarnings("unchecked")
	List<String> list = (List<String>)request.getServletContext().getAttribute("onlineUsers");
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      if (((String)it.next()).equals(username))
      {
        it.remove();
        break;
      }
    }
    request.getServletContext().setAttribute("onlineUsers", list);
  }
  
  public String regist()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String username = request.getParameter("username");
    String nickname = request.getParameter("nickname");
    String password = request.getParameter("password");
    this.result = this.userService.regist(username, password, nickname);
    if (this.result.getStatus() == 0) {
      System.out.println("已注册");
    } else {
      System.out.println("注册失败");
    }
    return "success";
  }
  
  public String login()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    this.result = this.userService.login(username, password);
    if (this.result.getStatus() == 0)
    {
      if (request.getServletContext().getAttribute(username) != null)
      {
        this.result.setStatus(-1);
        System.out.println("重复登录");
      }
      else
      {
        request.getServletContext().setAttribute(username, 
          this.result.getData());
        addOnlineUser(request, username);
        System.out.println("已登录");
      }
    }
    else {
      System.out.println("登陆失败");
    }
    return "success";
  }
  
  public String logout()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String username = request.getParameter("username");
    this.result = new DataResult();
    if (request.getServletContext().getAttribute(username) != null)
    {
      request.getServletContext().removeAttribute(username);
      removeOnlineUser(request, username);
      System.out.println("已退出");
      this.result.setStatus(0);
    }
    else
    {
      this.result.setStatus(1);
    }
    return "success";
  }
  
  public String checkLogin()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String username = request.getParameter("username");
    this.result = new DataResult();
    if (request.getServletContext().getAttribute(username) != null)
    {
      System.out.println("检测到已登录");
      this.result.setStatus(0);
    }
    else
    {
      System.out.println("检测到未登录");
      this.result.setStatus(1);
    }
    return "success";
  }
  
  public String clearLogin()
  {
    HttpServletRequest request = ServletActionContext.getRequest();
    String username = request.getParameter("username");
    this.result = new DataResult();
    request.getServletContext().removeAttribute(username);
    removeOnlineUser(request, username);
    System.out.println("已清空" + username + "的登录信息");
    this.result.setStatus(0);
    return "success";
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
