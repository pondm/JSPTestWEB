package com.pdn.jdbctestweb.dao;

import com.pdn.jdbctestweb.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }
    
    public static User mapString(String[] str) {
        User user = new User();
        user.setId(Integer.valueOf(str[0]));
        user.setUsername(str[1]);
        user.setFirstname(str[2]);
        user.setLastname(str[3]);
        user.setPassword(str[4]);
        user.setEmail(str[5]);
        return user;
    }
}