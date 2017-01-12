package com.pdn.jdbctestweb.dao;

import com.pdn.jdbctestweb.model.User;
import java.util.List;

public interface UserDAO {
   public void setDataSource(Object ds);
   public void create(User u);
   public User getUser(Integer id);
   public List<User> listUsers();
   public void delete(Integer id);
   public void update(User u);
}
