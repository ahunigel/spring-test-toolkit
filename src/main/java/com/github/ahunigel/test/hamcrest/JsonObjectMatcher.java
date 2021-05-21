package com.github.ahunigel.test.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.test.util.JsonPathExpectationsHelper;


/**
 * Created by nigel on 2020/4/9.
 *
 * @author nigel
 */
public class JsonObjectMatcher extends TypeSafeMatcher<String> {
  private final JsonPathExpectationsHelper jsonPathHelper;

  public JsonObjectMatcher(String expression) {
    this.jsonPathHelper = new JsonPathExpectationsHelper(expression);
  }

  @Override
  protected boolean matchesSafely(String item) {
    jsonPathHelper.assertValueIsMap(item);
    return true;
  }

  @Override
  public void describeTo(Description description) {

  }

  public static Matcher<String> isJsonObject(String expression) {
    return new JsonObjectMatcher(expression);
  }

  public static Matcher<String> isJsonObject() {
    return new JsonObjectMatcher("$");
  }

}
