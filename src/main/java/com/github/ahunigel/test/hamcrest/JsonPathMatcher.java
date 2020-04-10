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

  public static <T> Matcher<T> jsonPath(String expression, Matcher<T> matcher) {
    return new JsonPathMatcher<T>(expression, matcher);
  }

}
