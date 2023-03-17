package id.mdm17.validation.controller;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.mdm17.validation.dto.DataResponse;
import id.mdm17.validation.dto.RegisterReq;
import id.mdm17.validation.dto.UsernamePassword;
import id.mdm17.validation.service.AuthService;
import id.mdm17.validation.service.ValidationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    AuthService authService;

    @Autowired
    ValidationService validationService;

    @PostMapping("/register")
    public ResponseEntity<DataResponse> createUser(@RequestBody RegisterReq u) {
        validationService.validate(u);
        authService.register(u);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<DataResponse> login(@RequestBody UsernamePassword u) {
        validationService.validate(u);
        HashMap<String, String> dataRes = new HashMap<>();
        dataRes.put("token", authService.login(u));
        return ResponseEntity.ok().body(new DataResponse(200, "success", dataRes));
    }

}
