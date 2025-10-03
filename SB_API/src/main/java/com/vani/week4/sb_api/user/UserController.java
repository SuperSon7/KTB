package com.vani.week4.sb_api.user;

import com.vani.week4.sb_api.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller //컨트롤러임을 알리기
@RequestMapping("/user")
public class UserController {


    @GetMapping("/gettest")
    public ResponseEntity<String> gettest(){
        return ResponseEntity.ok("gettest Success!");
    }

    @PostMapping("/posttest")
    public ResponseEntity<String> posttest(@RequestBody String request) {
        List<String> ans = new ArrayList<>();

        for(Integer i=0; i<=10; i++){
            Integer temp = Integer.parseInt(request);
            temp *= i;
            ans.add(temp.toString());
        }
        return ResponseEntity.ok(ans.toString()+"posttest Success!");
    }

    @DeleteMapping("/deletetest")
    public ResponseEntity<String> delete_test() {
        return ResponseEntity.ok("delete Success!");
    }

    @PutMapping("/puttest")
    public ResponseEntity<String> put_test() {
        return ResponseEntity.ok("put Success!");
    }

}
