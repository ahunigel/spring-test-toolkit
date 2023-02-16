package com.github.ahunigel.test.hamcrest;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.Map;

/**
 * Extra matchers for hamcrest
 *
 * @author Nigel.Zheng
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchersEx {
  public static <U> Matcher<java.lang.Iterable<? extends U>> countItem(int count, Matcher<U> itemMatcher) {
    return Count.countItem(count, itemMatcher);
  }

  public static Matcher<String> isJsonArray(String expression) {
    return JsonArrayMatcher.isJsonArray(expression);
  }

  public static Matcher<String> isJsonArray() {
    return JsonArrayMatcher.isJsonArray("$");
  }

  public static Matcher<String> isJsonObject(String expression) {
    return JsonObjectMatcher.isJsonObject(expression);
  }

  public static Matcher<String> isJsonObject() {
    return JsonObjectMatcher.isJsonObject("$");
  }

  public static Matcher<Map<?, ?>> isObject() {
    return JsonPathMatcher.isObject();
  }

  public static <E> Matcher<Collection<? extends E>> isArray() {
    return JsonPathMatcher.isArray();
  }
}
