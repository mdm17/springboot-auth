package id.mdm17.validation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import id.mdm17.validation.dto.RegisterReq;
import id.mdm17.validation.dto.UsernamePassword;
import id.mdm17.validation.entity.User;
import id.mdm17.validation.repository.UserRepository;
import id.mdm17.validation.security.JwtProvider;
import id.mdm17.validation.service.AuthService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtUtils;

    @Override
    public User register(RegisterReq req) {
        try {
            User user = new User();
            user.setName(req.getName());
            user.setUsername(req.getUsername());
            user.setPassword(encoder.encode(req.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalError(e);
        }
    }

    @Override
    public String login(UsernamePassword req) throws HttpClientErrorException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtUtils.generateToken(authentication);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "Username/Password Salah");
        }
    }

}
