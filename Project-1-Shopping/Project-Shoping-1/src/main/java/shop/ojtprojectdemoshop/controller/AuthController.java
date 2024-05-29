package shop.ojtprojectdemoshop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shop.ojtprojectdemoshop.model.dto.request.FormLogin;
import shop.ojtprojectdemoshop.model.dto.request.FormRegister;
import shop.ojtprojectdemoshop.model.dto.response.ResponseData;
import shop.ojtprojectdemoshop.service.IUserService;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {
    @Autowired
    private IUserService userService;

//    @PostMapping("/sign-in")
//    public ResponseEntity<JwtResponse> doLogin(@RequestBody FormLogin formLogin) {
//        return new ResponseEntity<>(userService.login(formLogin), HttpStatus.OK);
//    }
    @PostMapping("/sign-in")
    public ResponseData<?> doLogin(@RequestBody FormLogin formLogin) {
        return new ResponseData<>(1,"Login successfully",userService.login(formLogin));
    }

    @PostMapping("/sign-up")
    public ResponseData<?> doRegister(@Valid @RequestBody FormRegister formRegister) {
        boolean check = userService.register(formRegister);
        if (check) {
            return new ResponseData<>(1,"Register successfully");
        }else {
            throw new RuntimeException("Username or password is incorrect");
        }
    }

}
