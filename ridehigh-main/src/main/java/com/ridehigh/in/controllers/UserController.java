package com.ridehigh.in.controllers;


import com.ridehigh.in.ums.models.User;
import com.ridehigh.in.ums.services.UserService;
import javax.validation.Valid;
import javax.xml.ws.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/user")
  public String hello(@RequestParam(value = "id", defaultValue = "0") long userId) {
    return userService.getUserById(userId).toString();
  }



  @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> insertUser(@RequestBody User request) {
    User createdUser = userService.createUser(request);

    if (createdUser != null) {
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }


}
