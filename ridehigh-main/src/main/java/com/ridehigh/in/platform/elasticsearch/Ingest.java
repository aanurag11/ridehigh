package com.ridehigh.in.platform.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.ridehigh.in.ums.models.User;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ingest {

  @Autowired
  private Connection esConnection;

  private ElasticsearchClient esClient;

  @PostConstruct
  private void init() {
    esClient = esConnection.getEsConnection();
  }

  public void ingest(User user) throws IOException {
    esClient.index(i -> i.index("user").id(String.valueOf(user.getUserId())).document(user));
  }

}
