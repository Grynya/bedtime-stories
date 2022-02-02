package com.bedtimestories.demo.services;

import com.bedtimestories.demo.dao.LikedStoryDao;
import com.bedtimestories.demo.dao.ListenedStoryDao;
import com.bedtimestories.demo.dao.StoryDao;
import com.bedtimestories.demo.dao.impl.LikedStoryDaoImpl;
import com.bedtimestories.demo.dao.impl.ListenedStoryDaoImpl;
import com.bedtimestories.demo.dao.impl.StoryDaoImpl;
import com.bedtimestories.demo.domain.request.StoryRequest;
import com.bedtimestories.demo.model.Story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryService{

    private final StoryDao storyDao;
    private final LikedStoryDao likedStoryDao;
    private final ListenedStoryDao listenedStoryDao;

    @Autowired
    public StoryService(StoryDaoImpl storyDao, LikedStoryDaoImpl likedStoryDao,
                        ListenedStoryDaoImpl listenedStoryDao) {
        this.storyDao = storyDao;
        this.likedStoryDao = likedStoryDao;
        this.listenedStoryDao = listenedStoryDao;
    }

    public List<Story> stories(int numOfLast){
        return (storyDao
                .findAll())
                .stream()
                .limit(numOfLast)
                .collect(Collectors.toList());
    }

    public List<Story> ukrStories(){
        return (storyDao
                .findAll())
                .stream()
                .filter((s)->(s.getName()).matches("^[?!,і,ї,є,.а-яА-ЯёЁ0-9\\s]+$"))
                .collect(Collectors.toList());
    }
    public List<Story> engStories(){
        return (storyDao
                .findAll())
                .stream()
                .filter((s)->(s.getName()).matches("^[?!,.A-Za-z0-9\\s]+$"))
                .collect(Collectors.toList());
    }

    public List<Story> stories(){
        return storyDao.findAll();
    }

    @Transactional
    public List<Story> add(StoryRequest storyRequest) {
        Story story = new Story(storyRequest.getName(), 0, "/stories/"+storyRequest.getPath(),
                storyRequest.getDescription());
        storyDao.add(story);
        return storyDao.findAll();
    }

    public boolean delete(Long id) {
        storyDao.delete(id);//delete story
            return true;
    }

    @Transactional
    public Optional<Story> modify(Long id, StoryRequest storyRequest) {
            storyDao.update(new Story(id, storyRequest.getName(), 0, storyRequest.getPath(),
                    storyRequest.getDescription()));
            return Optional.of(storyDao.getStoryById(id));
    }

    public List<Story> find(int minNumOfLikes) {
        return storyDao.find(minNumOfLikes);
    }

    @Transactional
    public Optional<Story> like(Long idStory, Long idUser) {

        if (storyDao.getStoryById(idStory)!=null) {
            storyDao.like(idStory);
            likedStoryDao.like(idStory, idUser);
            return Optional.of(storyDao.getStoryById(idStory));
        }
        return Optional.empty();
    }
    @Transactional
    public Optional<Story> removeLike(Long idStory, Long idUser) {
        if (storyDao.getStoryById(idStory)!=null) {
            storyDao.removeLike(idStory);
            likedStoryDao.removeLike(idStory, idUser);
            return Optional.of(storyDao.getStoryById(idStory));
        }
        return Optional.empty();
    }

    public List<Story> getLiked(Long idUser){
        return likedStoryDao.getLiked(idUser);
    }
    public List<Story> getListened(Long idUser){
        return listenedStoryDao.getListened(idUser);
    }
    @Transactional
    public List<Story> listen(Long idStory, Long idUser){
        listenedStoryDao.listen(idStory, idUser);
        return listenedStoryDao.getListened(idUser);
    }

    @Transactional
    public List<Story> removeFromListened(Long idStory, Long idUser){
        listenedStoryDao.removeFromListened(idStory, idUser);
        return listenedStoryDao.getListened(idUser);
    }
}
