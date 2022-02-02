package com.bedtimestories.demo.dao.impl;

import com.bedtimestories.demo.dao.StoryDao;
import com.bedtimestories.demo.model.Story;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StoryDaoImpl implements StoryDao {

    private final JdbcTemplate jdbcTemplate;

    public StoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Story> findAll() {
        return jdbcTemplate.query("select * from story", ROW_MAPPER);
    }

    @Override
    public int add(Story story) {
        return jdbcTemplate.update("insert into story (name, path, description, rating)" +
                        " values (?, ?, ?, ?)",
                story.getName(), story.getPath(), story.getDescription(), story.getRating());
    }

    @Override
    public Story getStoryById(long id) {
        Story story = null;
        try {
            story = jdbcTemplate.queryForObject("select * from story where id = ?", new Object[]{id}, ROW_MAPPER);
        } catch (DataAccessException dataAccessException) {
            System.out.println("Couldn't find entity of type Story with id "+id);
        }

        return story;
    }
    @Override
    public int delete(Long id) {
        return jdbcTemplate.update("delete from story where id = ?", id);
    }

    @Override
    public void update(Story story) {
        jdbcTemplate.update("update story set name=?, path =?, description=?  where id =?", story.getName(),
                story.getPath(), story.getDescription(), story.getId());
    }

    @Override
    public List<Story> find(int minNumOfLikes) {
        return jdbcTemplate.query("select * from story where rating>=?", ROW_MAPPER, minNumOfLikes);
    }

    @Override
    public void like(long id) {
        jdbcTemplate.update("update story set rating=rating+1  where id =?", id);
    }

    @Transactional
    @Override
    public void removeLike(long id) {
        if (getStoryById(id).getRating()>0)
            jdbcTemplate.update("update story set rating=rating-1  where id =?", id);
    }
}