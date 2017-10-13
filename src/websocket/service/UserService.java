package websocket.service;

import websocket.entity.DataResult;

public abstract interface UserService
{
  public abstract DataResult regist(String paramString1, String paramString2, String paramString3);
  
  public abstract DataResult login(String paramString1, String paramString2);
}
