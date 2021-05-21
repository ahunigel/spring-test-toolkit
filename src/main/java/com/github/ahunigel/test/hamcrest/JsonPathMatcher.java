package com.github.ahunigel.test.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsNot;
import org.springframework.test.util.JsonPathExpectationsHelper;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasEntry;


/**
 * Created by nigel on 2020/4/9.
 *
 * @author nigel
 */
public class JsonPathMatcher extends TypeSafeMatcher<String> {
  private final Matcher<Object> matcher;
  private final JsonPathExpectationsHelper jsonPathHelper;

  public JsonPathMatcher(String expression, Matcher<Object> matcher) {
    this.matcher = matcher;
    this.jsonPathHelper = new JsonPathExpectationsHelper(expression);
  }

  @Override
  protected boolean matchesSafely(String item) {
    jsonPathHelper.assertValue(item, matcher);
    return true;
  }

  @Override
  public void describeTo(Description description) {

  }

  public static Matcher jsonPath(String expression, Matcher matcher) {
    assertThat("JsonPathMatcher cycle invocation", matcher, IsNot.not(instanceOf(JsonPathMatcher.class)));
    return new JsonPathMatcher(expression, matcher);
  }

  public static Matcher<Map<?, ?>> isObject() {
    return hasEntry(anything(), anything());
  }

  public static <E> Matcher<Collection<? extends E>> isArray() {
    return org.hamcrest.collection.IsCollectionWithSize.<E>hasSize(anything());
  }
}
