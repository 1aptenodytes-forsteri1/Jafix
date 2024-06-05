package com.example.demo.Repositories;

import com.example.demo.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;
    RowMapper<User> userRowMapper = (r, i) -> {
        User rowObject = new User(
                r.getInt("user_id"),
                r.getString("login"),
                r.getString("password"),
                r.getString("name"),
                r.getString("surname"),
                r.getDouble("bonuses")
        );

        return rowObject;
    };
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User findUserByLogin(String login){
        String sql = "SELECT * FROM user WHERE login = ?";
        if (jdbc.query(sql, userRowMapper,login).size()>0){
            return jdbc.query(sql, userRowMapper,login).get(0);
        }else {
            return null;
        }

    }
    public void addUser(String login, String password, String name, String surname){
        String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?, ?, 0.0)";
        jdbc.update(sql,login,password,name,surname);
    }
    public void updateUser(User user){
        String sql = "UPDATE user SET login = ?, password = ?, name = ?, surname = ? WHERE user_id = ?;";
        jdbc.update(sql,user.getLogin(),user.getPassword(),user.getName(),user.getSurname(),user.getId());
    }
    public User findUserById(Integer id){
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return jdbc.query(sql,userRowMapper,id).get(0);
    }
}