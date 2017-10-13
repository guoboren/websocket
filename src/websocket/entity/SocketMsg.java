package websocket.entity;

import java.util.Set;

public class SocketMsg
{
  private String msg;
  private Set<String> users;
  
  public String getMsg()
  {
    return this.msg;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public Set<String> getUsers()
  {
    return this.users;
  }
  
  public void setUsers(Set<String> users)
  {
    this.users = users;
  }
}
