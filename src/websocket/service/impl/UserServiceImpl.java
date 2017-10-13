package websocket.service.impl;

import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import websocket.dao.UserDao;
import websocket.entity.DataResult;
import websocket.entity.User;
import websocket.service.UserService;

@Service
public class UserServiceImpl
  implements UserService
{
  @Resource
  private UserDao userDao;
  
  @Transactional
  public DataResult regist(String username, String password, String nickname)
  {
    DataResult dr = new DataResult();
    if (this.userDao.findUserByUsername(username) != null)
    {
      dr.setStatus(1);
      return dr;
    }
    User u = new User();
    u.setNickname(nickname);
    u.setPassword(password);
    u.setUsername(username);
    Serializable id = this.userDao.saveUser(u);
    if (id != null)
    {
      u.setId(((Integer)id).intValue());
      dr.setStatus(0);
      dr.setData(u);
    }
    else
    {
      dr.setStatus(2);
    }
    return dr;
  }
  
  public DataResult login(String username, String password)
  {
    DataResult dr = new DataResult();
    User u1 = this.userDao.findUserByUsername(username);
    if ((u1 != null) && (u1.getPassword().equals(password)))
    {
      dr.setStatus(0);
      dr.setData(u1);
    }
    else
    {
      System.out.println(password);
      dr.setStatus(1);
    }
    return dr;
  }
}
