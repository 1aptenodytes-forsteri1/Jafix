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
        User rowObject = new User();
        rowObject.setId(r.getInt("user_id"));
        rowObject.setLogin(r.getString("login"));
        rowObject.setPassword(r.getString("password"));
        rowObject.setName(r.getString("name"));
        rowObject.setSurname(r.getString("surname"));
        rowObject.setBonuses(r.getDouble("bonuses"));
        rowObject.setRights(r.getString("rights"));
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
        String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?, ?, 0.0, NULL)";
        jdbc.update(sql,login,password,name,surname);
    }
}