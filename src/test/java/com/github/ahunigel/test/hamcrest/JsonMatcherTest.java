package com.github.ahunigel.test.hamcrest;

import org.junit.Test;

import static com.github.ahunigel.test.hamcrest.JsonArrayMatcher.isJsonArray;
import static com.github.ahunigel.test.hamcrest.JsonObjectMatcher.isJsonObject;
import static com.github.ahunigel.test.hamcrest.JsonPathMatcher.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


/**
 * Created by nigel on 2020/4/11.
 *
 * @author nigel
 */
public class JsonMatcherTest {

  private String jsonStr = "{\"id\":2,\"siteName\":\"Site\",\"timezone\":\"Asia\\/Shanghai\",\"units\":[{},{}]," +
      "\"address\":{\"street\":[]}}";

  @Test
  public void testJsonPath() {
    assertThat(jsonStr, jsonPath("$.siteName", equalTo("Site")));
    assertThat(jsonStr, jsonPath("$.id", is(2)));
    assertThat(jsonStr, jsonPath("$.address", isObject()));
    assertThat(jsonStr, jsonPath("$.address", hasEntry(equalTo("street"), anything())));
    assertThat(jsonStr, jsonPath("$.units", isArray()));
    assertThat(jsonStr, jsonPath("$.units", hasSize(2)));
  }

  @Test
  public void testJson() {
    assertThat(jsonStr, isJsonObject("$.address"));
    assertThat(jsonStr, isJsonArray("$.units"));
  }

  @Test(expected = AssertionError.class)
  public void testNonJsonObject() {
    assertThat(jsonStr, isJsonObject("$.units"));
  }

  @Test(expected = AssertionError.class)
  public void testNonJsonArray() {
    assertThat(jsonStr, isJsonArray("$.address"));
  }

}
