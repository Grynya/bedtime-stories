package com.bedtimestories.demo.dao.impl;

import com.bedtimestories.demo.dao.ListenedStoryDao;
import com.bedtimestories.demo.model.Story;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListenedStoryDaoImpl implements ListenedStoryDao {
    private final JdbcTemplate jdbcTemplate;

    public ListenedStoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void listen(long idStory, long idUser) {
        jdbcTemplate.update("insert into listened_story (story_id, user_id)" +
                " values (?, ?)", idStory, idUser);
    }

    @Override
    public void removeFromListened(long idStory, long idUser) {
        jdbcTemplate.update("delete from listened_story where story_id=? and user_id=?",
                idStory, idUser);
    }

    @Override
    public List<Story> getListened(long idUser) {
        return jdbcTemplate.query("select * from story inner join listened_story on " +
                        "listened_story.story_id=story.id where listened_story.user_id=?",
                ROW_MAPPER, idUser);
    }
}
