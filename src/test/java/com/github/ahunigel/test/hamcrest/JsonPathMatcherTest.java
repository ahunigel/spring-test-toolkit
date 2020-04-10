package com.github.ahunigel.test.hamcrest;

import org.junit.Test;

import static com.github.ahunigel.test.hamcrest.JsonPathMatcher.jsonPath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by nigel on 2020/4/11.
 *
 * @author nigel
 */
public class JsonPathMatcherTest {
  @Test
  public void testJsonPath() {
    String jsonStr = "{\"id\":2,\"siteName\":\"Site\",\"timezone\":\"Asia\\/Shanghai\"}";
    assertThat(jsonStr, jsonPath("$.siteName", equalTo("Site")));
    assertThat(jsonStr, jsonPath("$.id", is(2)));
  }
}
