package com.github.ahunigel.test.hamcrest;

import net.minidev.json.JSONArray;
import org.hamcrest.*;
import org.hamcrest.core.IsNot;
import org.springframework.test.util.JsonPathExpectationsHelper;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;


/**
 * Created by nigel on 2020/4/9.
 *
 * @author nigel
 */
public class JsonPathMatcher<T> extends TypeSafeMatcher<T> {
  private final Matcher<T> matcher;
  private final JsonPathExpectationsHelper jsonPathHelper;

  public JsonPathMatcher(String expression, Matcher<T> matcher) {
    this.matcher = matcher;
    this.jsonPathHelper = new JsonPathExpectationsHelper(expression);
  }

  @Override
  protected boolean matchesSafely(Object item) {
    assert item instanceof String;
    jsonPathHelper.assertValue((String) item, matcher);
    return true;
  }

  @Override
  public void describeTo(Description description) {

  }

  @Factory
  public static <T> Matcher<T> jsonPath(String expression, Matcher<T> matcher) {
    assertThat("JsonPathMatcher cycle invocation", matcher, IsNot.not(instanceOf(JsonPathMatcher.class)));
    return new JsonPathMatcher<T>(expression, matcher);
  }

  public static <T> Matcher<T> isObject() {
    return new BaseMatcher<T>() {
      @Override
      public boolean matches(Object item) {
        assert item instanceof Map;
        return true;
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }

  public static <T> Matcher<T> isArray() {
    return new BaseMatcher<T>() {
      @Override
      public boolean matches(Object item) {
        assert item instanceof JSONArray;
        return true;
      }

      @Override
      public void describeTo(Description description) {

      }
    };
  }
}
