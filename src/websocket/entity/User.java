package websocket.entity;

public class User
{
  private int id;
  private String username;
  private String nickname;
  private String password;
  private int onlineStatus;
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getNickname()
  {
    return this.nickname;
  }
  
  public void setNickname(String nickname)
  {
    this.nickname = nickname;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public int getOnlineStatus()
  {
    return this.onlineStatus;
  }
  
  public void setOnlineStatus(int onlineStatus)
  {
    this.onlineStatus = onlineStatus;
  }
}
