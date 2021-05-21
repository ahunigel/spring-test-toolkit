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
public class JsonArrayMatcher extends TypeSafeMatcher<String> {
  private final JsonPathExpectationsHelper jsonPathHelper;

  public JsonArrayMatcher(String expression) {
    this.jsonPathHelper = new JsonPathExpectationsHelper(expression);
  }

  @Override
  protected boolean matchesSafely(String item) {
    jsonPathHelper.assertValueIsArray(item);
    return true;
  }

  @Override
  public void describeTo(Description description) {

  }

  public static Matcher<String> isJsonArray(String expression) {
    return new JsonArrayMatcher(expression);
  }

  public static Matcher<String> isJsonArray() {
    return new JsonArrayMatcher("$");
  }

}
