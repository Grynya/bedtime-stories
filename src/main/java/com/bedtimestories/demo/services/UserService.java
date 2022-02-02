package com.bedtimestories.demo.services;

import com.bedtimestories.demo.dao.UserDao;
import com.bedtimestories.demo.dao.impl.UserDaoImpl;
import com.bedtimestories.demo.domain.request.UserRequest;
import com.bedtimestories.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public List<User> users() {
        return userDao.findAll();
    }

    @Transactional
    public List<User> add(UserRequest userRequest) {
        userDao.add(new User(userRequest.getName(), userRequest.getPassword(), true));
        return userDao.findAll();
    }

    @Transactional
    public List<User> delete(Long id){
        userDao.delete(id);
        return userDao.findAll();
    }
}
