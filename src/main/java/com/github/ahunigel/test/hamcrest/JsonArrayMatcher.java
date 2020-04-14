package com.github.ahunigel.test.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.springframework.test.util.JsonPathExpectationsHelper;


/**
 * Created by nigel on 2020/4/9.
 *
 * @author nigel
 */
public class JsonArrayMatcher<T> extends TypeSafeMatcher<T> {
  private final JsonPathExpectationsHelper jsonPathHelper;

  public JsonArrayMatcher(String expression) {
    this.jsonPathHelper = new JsonPathExpectationsHelper(expression);
  }

  @Override
  protected boolean matchesSafely(Object item) {
    assert item instanceof String;
    jsonPathHelper.assertValueIsArray((String) item);
    return true;
  }

  @Override
  public void describeTo(Description description) {

  }

  @Factory
  public static <T> Matcher<T> isJsonArray(String expression) {
    return new JsonArrayMatcher<T>(expression);
  }

  @Factory
  public static <T> Matcher<T> isJsonArray() {
    return new JsonArrayMatcher<T>("$");
  }

}
