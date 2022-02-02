package com.bedtimestories.demo.dao.impl;

import com.bedtimestories.demo.dao.UserDao;
import com.bedtimestories.demo.model.Story;
import com.bedtimestories.demo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from website_user", ROW_MAPPER);
    }

    @Override
    public int add(User user) {
        System.out.println(user.getName());
        System.out.println(user.getPassword());

        return jdbcTemplate.update("insert into website_user (name, password, enabled)\n" +
                "values (?, ?, 1)", user.getName(), user.getPassword());
    }

    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from website_user where id=?", id);
    }
}
