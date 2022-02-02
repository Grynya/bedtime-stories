package com.bedtimestories.demo.dao;

import com.bedtimestories.demo.model.Story;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public interface LikedStoryDao {
    RowMapper<Story> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Story(resultSet.getLong("id"), resultSet.getString("name"),
            resultSet.getInt("rating"), resultSet.getString("path"),
            resultSet.getString("description"));


    void like(long idStory, long idUser);
    void removeLike(long idStory, long idUser);
    List<Story> getLiked(long idUser);
}
