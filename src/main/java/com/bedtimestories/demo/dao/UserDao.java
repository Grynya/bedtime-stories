package com.bedtimestories.demo.dao;

import com.bedtimestories.demo.model.Story;
import com.bedtimestories.demo.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao {
    RowMapper<User> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new User(resultSet.getLong("id"),
            resultSet.getString("name"), resultSet.getString("password"),
            resultSet.getBoolean("enabled"));
    List<User> findAll();

    int add(User user);

    int delete(Long id);
}
