package com.professional.dummy.controller;

/*
 *
 *@author Sudhanshu Chaubey on 8/20/2023
 *
 */

import com.professional.dummy.enums.HttpStatusCode;
import com.professional.dummy.model.Authorization;
import com.professional.dummy.model.Response;
import com.professional.dummy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthorizationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthorizationController(AuthenticationManager authenticationManager,
                                   JwtUtil jwtUtil,
                                   UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> doLogin(@RequestBody Authorization user) {
        Response response = new Response();
        Map<String, Object> responseMap = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            final UserDetails userDetail = userDetailsService.loadUserByUsername(user.getUsername());
            if(userDetail == null) {
                response.setMessage("No such user found");
                response.setHttpStatus(HttpStatus.BAD_REQUEST);
                response.setHttpStatusCode(HttpStatusCode.INVALID_REQUEST.getStatusCode());
                response.setData(null);
                responseMap.put("response", response);

                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }
            response.setMessage("User detail found");
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
            response.setData(jwtUtil.generateToken(userDetail));
            responseMap.put("response", response);

            return new ResponseEntity(responseMap, HttpStatus.OK);
        } catch (Exception error) {
            response.setMessage("Error while performing login");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(HttpStatusCode.INTERNAL_SYSTEM_ERROR.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
