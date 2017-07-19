package com.beecho.springxoxo.example;

import com.beecho.springxoxo.model.User;
import com.beecho.springxoxo.model.Xxoo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2017/7/17.
 */
public class UserServiceImpl implements UserService {

    private final AtomicLong idGen = new AtomicLong();

    public Long registerUser(User user) {
        return idGen.incrementAndGet();
    }
}
