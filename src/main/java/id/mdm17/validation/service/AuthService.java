package id.mdm17.validation.service;

import org.springframework.web.client.HttpClientErrorException;

import id.mdm17.validation.dto.RegisterReq;
import id.mdm17.validation.dto.UsernamePassword;
import id.mdm17.validation.entity.User;

public interface AuthService {

    User register(RegisterReq req);

    String login(UsernamePassword req) throws HttpClientErrorException;
}
