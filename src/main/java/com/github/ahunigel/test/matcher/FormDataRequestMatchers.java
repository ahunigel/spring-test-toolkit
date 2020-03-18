package com.github.ahunigel.test.matcher;

import org.hamcrest.Matcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.mock.http.client.MockClientHttpRequest;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.util.MultiValueMap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

/**
 * Created by nigel on 2020/3/15.
 *
 * @author nigel
 */
public class FormDataRequestMatchers<K, V> {

  public static FormDataRequestMatchers form() {
    return new FormDataRequestMatchers();
  }

  public RequestMatcher hasKey(String key) {
    return request -> {
      MultiValueMap<String, String> form = getFormData(request);
      assertTrue("Request form has key: " + key, form.containsKey(key));
    };
  }

  public RequestMatcher data(final MultiValueMap<String, String> expectedContent) {
    return content().formData(expectedContent);
  }

  public <K, V> RequestMatcher data(Matcher<? super Map<? super K, ? super V>> matcher) {
    return request -> {
      MultiValueMap<String, String> form = getFormData(request);
      assertTrue("Request form data: " + form + " match: " + matcher, matcher.matches(form));
    };
  }

  private MultiValueMap<String, String> getFormData(ClientHttpRequest request) throws IOException {
    HttpInputMessage inputMessage = new HttpInputMessage() {
      @Override
      public InputStream getBody() throws IOException {
        MockClientHttpRequest mockRequest = (MockClientHttpRequest) request;
        return new ByteArrayInputStream(mockRequest.getBodyAsBytes());
      }

      @Override
      public HttpHeaders getHeaders() {
        return request.getHeaders();
      }
    };
    FormHttpMessageConverter converter = new FormHttpMessageConverter();
    return converter.read(null, inputMessage);
  }
}
