package shop.ojtprojectdemoshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import shop.ojtprojectdemoshop.model.dto.request.FormLogin;
import shop.ojtprojectdemoshop.model.dto.request.FormRegister;
import shop.ojtprojectdemoshop.model.dto.response.JwtResponse;
import shop.ojtprojectdemoshop.model.dto.response.ResponseSuccess;
import shop.ojtprojectdemoshop.security.jwt.JwtEntryPoint;
import shop.ojtprojectdemoshop.service.IUserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

//    @PostMapping("/sign-in")
//    public ResponseEntity<JwtResponse> doLogin(@RequestBody FormLogin formLogin) {
//        return new ResponseEntity<>(userService.login(formLogin), HttpStatus.OK);
//    }
    @PostMapping("/sign-in")
    public ResponseSuccess doLogin(@RequestBody FormLogin formLogin) {
        return new ResponseSuccess(HttpStatus.CREATED,"Login successfully",userService.login(formLogin));
    }

    @PostMapping("/sign-up")
    public ResponseSuccess doRegister(@Valid @RequestBody FormRegister formRegister) {
        boolean check = userService.register(formRegister);
        if (check) {
            return new ResponseSuccess(HttpStatus.CREATED,"Register successfully",1); // chua co thong bao
        }else {
            throw new RuntimeException("Username or password is incorrect");
        }
    }

}
