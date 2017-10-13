package websocket.entity;

public class DataResult
{
  private String msg;
  private Object data;
  private int status;
  
  public String getMsg()
  {
    return this.msg;
  }
  
  public void setMsg(String msg)
  {
    this.msg = msg;
  }
  
  public Object getData()
  {
    return this.data;
  }
  
  public void setData(Object data)
  {
    this.data = data;
  }
  
  public int getStatus()
  {
    return this.status;
  }
  
  public void setStatus(int status)
  {
    this.status = status;
  }
}
