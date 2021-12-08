package org.iot.myhome.repository;

import org.iot.myhome.model.User;

import java.util.List;

public interface CustomizedUserRepository {
    List<User> findByUsernameCustom(String username);

    List<User> findByUsernameJdbc(String username);
}
