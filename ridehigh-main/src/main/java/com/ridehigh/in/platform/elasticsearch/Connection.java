package com.ridehigh.in.platform.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Connection {
 public ElasticsearchClient esClient;


  @PostConstruct
  private void createConnection() {
    String serverUrl = "https://localhost:9200";
    String apiKey = "VnVhQ2ZHY0JDZGJrU...";

    String hostname = "localhost";
    int port = 9200;
    String scheme = "https";
    String username = "elastic";
    String password = "mDyTALIedgIQ2JrkY7*B";

// Create the low-level client
    /*RestClient restClient = RestClient
        .builder(HttpHost.create(serverUrl))
        .setDefaultHeaders(new Header[]{
            new BasicHeader("Authorization", "ApiKey " + apiKey)
        })
        .build();*/

    //RestClient client = createClient(hostname, port, scheme, username, password);

    RestClient client = null;
    try {
      client = createClient();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyStoreException e) {
      e.printStackTrace();
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

    log.info("esClient created:{}", client);

// Create the transport with a Jackson mapper
    ElasticsearchTransport transport = new RestClientTransport(
        client, new JacksonJsonpMapper());

// And create the API client
    esClient = new ElasticsearchClient(transport);
  }

  /*public static RestClient createClient(String hostname, int port, String scheme, String username, String password) {
    final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(
        AuthScope.ANY, new UsernamePasswordCredentials(username, password));

    RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, port, scheme))
        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
          @Override
          public HttpAsyncClientBuilder customizeHttpClient(
              HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
          }
        });

    return builder.build();
  }*/

  public static RestClient createClient()
      throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    final CredentialsProvider credentialsProvider =
        new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
        new UsernamePasswordCredentials("elastic", "mDyTALIedgIQ2JrkY7*B"));

    /*RestClientBuilder builder = RestClient.builder(
            new HttpHost("localhost", 9200))
        .setHttpClientConfigCallback(new HttpClientConfigCallback() {
          @Override
          public HttpAsyncClientBuilder customizeHttpClient(
              HttpAsyncClientBuilder httpClientBuilder) {
            httpClientBuilder.disableAuthCaching();
            return httpClientBuilder
                .setDefaultCredentialsProvider(credentialsProvider);
          }
        });*/
    SSLContext sslContext = SSLContextBuilder.create()
        .loadTrustMaterial((chain, authType) -> true)
        .build();

    RestClient restClient = RestClient
        .builder(new HttpHost("localhost", 9200, "https"))

        .setHttpClientConfigCallback(hc -> hc
            .setDefaultCredentialsProvider(credentialsProvider)
            .setSSLContext(sslContext)

        )
        .build();

    return restClient;
  }


  public ElasticsearchClient getEsConnection() {
    return esClient;
  }

}
