package com.vani.week4.sb_api.user;

import com.vani.week4.sb_api.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RequiredArgsConstructor // 직접 DI 해보기
@Controller //컨트롤러임을 알리기
@RequestMapping("/user")
public class UserController {

    // 의존성 주입하기
    private final UserService userService;

    // DI 위해 빈에 등록하기
    @Autowired // 4.3 이후 부터는 생성자 하나면 자동 주입이라 하나면 없어도 된다.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.allUsers());
    }

    @PostMapping()
    public ResponseEntity<뭔가의 DTO> signup(@RequestBody 뭔가의DTO request) {
        // 일단 온거 받고 뭐가 어디서 어떻게 오는데?
        UserResponseDto createdUser = userService.createdUser(request);
        // 검증하고 어떤 형식인데? 뭐 검증하는데? 어떻게 검증하는데?
        // 맞으면 등록하고 어디에 등록하는데? 어떻게 등록하는데?
        // 리턴은 200으로 어떻게 리턴하는데?
        // 예외 처리는?
        return ResponseEntity.ok(createdUser)
    }

    @DeleteMapping()
    public String user_delete() {

    }

    @PutMapping()
    public String user_update() {

    }

}
