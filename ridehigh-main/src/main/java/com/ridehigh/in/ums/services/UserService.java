package com.ridehigh.in.ums.services;

import com.ridehigh.in.platform.elasticsearch.Ingest;
import com.ridehigh.in.platform.elasticsearch.Queries;
import com.ridehigh.in.ums.models.User;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
   @Autowired
   private Queries queries;
   @Autowired
   private Ingest ingest;

  public User getUserById(long userId) {
    try {
      return queries.getUserById(userId);
    } catch (IOException e) {
      e.printStackTrace();
    }
   return null;
  }

  public User createUser(User user) {
    try {
      ingest.ingest(user);
      return user;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
