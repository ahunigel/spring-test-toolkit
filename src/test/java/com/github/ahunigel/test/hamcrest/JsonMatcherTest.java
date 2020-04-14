package com.github.ahunigel.test.hamcrest;

import org.junit.Test;

import static com.github.ahunigel.test.hamcrest.JsonArrayMatcher.isJsonArray;
import static com.github.ahunigel.test.hamcrest.JsonObjectMatcher.isJsonObject;
import static com.github.ahunigel.test.hamcrest.JsonPathMatcher.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by nigel on 2020/4/11.
 *
 * @author nigel
 */
public class JsonMatcherTest {
  @Test
  public void testJsonPath() {
    String jsonStr = "{\"id\":2,\"siteName\":\"Site\",\"timezone\":\"Asia\\/Shanghai\",\"units\":[{},{}]," +
        "\"address\":{\"street\":[]}}";
    assertThat(jsonStr, jsonPath("$.siteName", equalTo("Site")));
    assertThat(jsonStr, jsonPath("$.id", is(2)));
    assertThat(jsonStr, isJsonObject("$.address"));
    assertThat(jsonStr, isJsonArray("$.units"));
    assertThat(jsonStr, jsonPath("$.address", isObject()));
    assertThat(jsonStr, jsonPath("$.units", isArray()));
  }

  @Test
  public void testJson() {
    String jsonStr = "{\"id\":2,\"siteName\":\"Site\",\"timezone\":\"Asia\\/Shanghai\",\"units\":[{},{}]," +
        "\"address\":{\"street\":[]}}";
    assertThat(jsonStr, isJsonObject("$.address"));
    assertThat(jsonStr, isJsonArray("$.units"));
  }

}
