package websocket.socket;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import net.sf.json.JSONObject;
import websocket.entity.SocketMsg;
import websocket.util.DateUtil;

@ServerEndpoint("/chat")
public class MyWebSocket
{
  private static int onlineCount = 0;
  private static Map<String, MyWebSocket> webSocketSet = new ConcurrentHashMap<String, MyWebSocket>();
  private Session session;
  
  @OnOpen
  public void onOpen(Session session)
    throws IOException
  {
    this.session = session;
    String username = 
      (String)((List<String>)session.getRequestParameterMap().get("username")).get(0);
    String key = username;
    if (webSocketSet.containsKey(username)) {
      return;
    }
    webSocketSet.put(key, this);
    addOnlineCount();
    System.out.println(username + "加入连接！当前在线人数为" + getOnlineCount());
    SocketMsg sm = new SocketMsg();
    sm.setUsers(webSocketSet.keySet());
    for (MyWebSocket item : webSocketSet.values()) {
      try
      {
        String msg = JSONObject.fromObject(sm).toString();
        item.sendMessage(msg);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  @OnClose
  public void onClose(Session session)
  {
    String username = 
      (String)((List<String>)session.getRequestParameterMap().get("username")).get(0);
    String key = username;
    webSocketSet.remove(key);
    subOnlineCount();
    System.out.println(username + "关闭连接！当前在线人数为" + getOnlineCount());
    SocketMsg sm = new SocketMsg();
    sm.setUsers(webSocketSet.keySet());
    for (MyWebSocket item : webSocketSet.values()) {
      try
      {
        String msg = JSONObject.fromObject(sm).toString();
        item.sendMessage(msg);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  @OnMessage
  public void onMessage(String message, Session session)
  {
    String username = (String)((List<String>)session.getRequestParameterMap().get("username")).get(0);
    System.out.println("[ " + username + " ]: " + message);
    for (MyWebSocket item : webSocketSet.values()) {
      try
      {
        SocketMsg sm = new SocketMsg();
        sm.setMsg("[" + username + "](" + 
          DateUtil.getTimeString() + ")说 : \n" + message);
        item.sendMessage(JSONObject.fromObject(sm).toString());
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  @OnError
  public void onError(Session session, Throwable error)
  {
    System.out.println("发生错误");
    error.printStackTrace();
  }
  
  public void sendMessage(String message)
    throws IOException
  {
    this.session.getBasicRemote().sendText(message);
  }
  
  public static synchronized int getOnlineCount()
  {
    return onlineCount;
  }
  
  public static synchronized void addOnlineCount()
  {
    onlineCount += 1;
  }
  
  public static synchronized void subOnlineCount()
  {
    onlineCount -= 1;
  }
}
