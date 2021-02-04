package com.programming2.payroll.services;

import com.programming2.payroll.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService extends BaseService {

    public User findUser(String username) {
        String hql = "FROM User WHERE username = :username";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        List<User> users = getRecords(User.class, hql, params);

        if (users == null || users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    public boolean authenticate(User user, String password) {
        if (user == null) return false;

        return password.equals(user.getPassword());
    }
}
