package com.bedtimestories.demo.dao.impl;

import com.bedtimestories.demo.dao.LikedStoryDao;
import com.bedtimestories.demo.model.Story;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikedStoryDaoImpl implements LikedStoryDao {
    private final JdbcTemplate jdbcTemplate;

    public LikedStoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void like(long idStory, long idUser) {
        jdbcTemplate.update("insert into liked_story (story_id, user_id)" +
                        " values (?, ?)", idStory, idUser);
    }

    @Override
    public void removeLike(long idStory, long idUser) {
        jdbcTemplate.update("delete from liked_story where story_id=? and user_id=?",
                idStory, idUser);
    }

    @Override
    public List<Story> getLiked(long idUser) {
        return jdbcTemplate.query("select * from story inner join liked_story on " +
                        "liked_story.story_id=story.id where liked_story.user_id=?",
                ROW_MAPPER, idUser);

    }
}
