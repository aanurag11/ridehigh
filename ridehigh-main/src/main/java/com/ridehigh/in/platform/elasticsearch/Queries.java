package com.ridehigh.in.platform.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.ridehigh.in.ums.models.User;
import java.io.IOException;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Queries {
  @Autowired
  private Connection esConnection;

  private ElasticsearchClient esClient;

  @PostConstruct
  private void init() {
    esClient = esConnection.getEsConnection();

  }

  public User getUserById(long userId) throws IOException {
    GetResponse<User> response = esClient.get(g -> g
            .index("user")
            .id(String.valueOf(userId)),
        User.class
    );

    if (response.found()) {
      User user = response.source();
      log.info("found user" + user);
      return user;
    } else {
      log.info ("user not found");
    }
    return null;
  }

}
