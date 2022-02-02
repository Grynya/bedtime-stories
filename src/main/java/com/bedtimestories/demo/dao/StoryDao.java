package com.bedtimestories.demo.dao;

import com.bedtimestories.demo.model.Story;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface StoryDao {

    RowMapper<Story> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Story(resultSet.getLong("id"), resultSet.getString("name"),
            resultSet.getInt("rating"), resultSet.getString("path"),
            resultSet.getString("description"));

    List<Story> findAll();

    int add(Story story);

    Story getStoryById(long id);

    int delete(Long id);

    void update(Story story);

    List<Story> find(int minNumOfLikes);

    void like(long id);

    void removeLike(long id);


}
