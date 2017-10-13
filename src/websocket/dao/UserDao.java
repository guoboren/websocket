package websocket.dao;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import websocket.entity.User;

@Repository
public class UserDao
{
  @Resource
  private HibernateTemplate hibernateTemplate;
  
  public Serializable saveUser(User user)
  {
    return this.hibernateTemplate.save(user);
  }
  
  public User findUserByUsername(String username)
  {
    List<?> list = this.hibernateTemplate.find("from User u where u.username = '" + username + "'");
    if (list.size() > 0) {
      return (User)list.get(0);
    }
    return null;
  }
}
