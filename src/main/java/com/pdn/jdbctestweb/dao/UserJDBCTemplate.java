package com.pdn.jdbctestweb.dao;

import com.pdn.jdbctestweb.model.User;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    
    @Override
    public void setDataSource(Object dataSource) {
      this.dataSource = (DataSource)dataSource;
      this.jdbcTemplateObject = new JdbcTemplate((DataSource)dataSource);
    }

    @Override
    public void create(User u) {
       String SQL = "insert into Users values (?, ?, ?, ?, ?, ?)";
       jdbcTemplateObject.update(SQL, u.getId(), u.getUsername(), u.getPassword(), u.getFirstname(), u.getLastname(), u.getEmail());
    }
    
    @Override
    public User getUser(Integer id) {
       String SQL = "select * from Users where user_id = ?";
       User user = jdbcTemplateObject.queryForObject(SQL, 
                         new Object[]{id}, new UserMapper());
       return user;
    }

    @Override
    public List<User> listUsers() {
       String SQL = "select * from Users order by user_id";
       List <User> users = jdbcTemplateObject.query(SQL, new UserMapper());
       return users;
    }

    @Override
    public void delete(Integer id){
       String SQL = "delete from Users where user_id = ?";
       jdbcTemplateObject.update(SQL, id);
    }

    @Override
    public void update(User u){
       String SQL = "update Users set username = ?, password = ?, firstname = ?, lastname = ?, email = ? where user_id = ?";
       jdbcTemplateObject.update(SQL, u.getUsername(), u.getPassword(), u.getFirstname(), u.getLastname(), u.getEmail(), u.getId());
    }
}