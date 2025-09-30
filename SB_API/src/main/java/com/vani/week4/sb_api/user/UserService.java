package com.vani.week4.sb_api.user;

import com.vani.week4.sb_api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //lombok 을 이용한 필수 매개변수 가진 생성자 기본이
@Service
public class UserService {

    public User createUser(String id, String email, String provider) {
        User user = new User(id, email, provider);

    }

    public User getUser(String email) {

    }
}
