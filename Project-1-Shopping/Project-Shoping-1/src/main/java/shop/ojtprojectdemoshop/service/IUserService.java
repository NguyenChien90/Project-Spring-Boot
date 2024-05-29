package shop.ojtprojectdemoshop.service;

import shop.ojtprojectdemoshop.model.dto.request.FormLogin;
import shop.ojtprojectdemoshop.model.dto.request.FormRegister;
import shop.ojtprojectdemoshop.model.dto.response.JwtResponse;

public interface IUserService {
    boolean register(FormRegister formRegister);
    JwtResponse login(FormLogin formLogin);
}
