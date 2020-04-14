package com.github.ahunigel.test.junit;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.junit.Assert.assertTrue;

/**
 * Additional assertions for JUnit
 * <p>
 * Created by nigel on 4/14/2020.
 *
 * @author nigel
 */
public class Assertions {
  public static void assertNotEmpty(String message, Collection collection) {
    assertTrue(message, nonNull(collection) && !collection.isEmpty());
  }

  public static void assertNotEmpty(Collection collection) {
    assertNotEmpty(null, collection);
  }

  public static void assertBlank(String message, Collection collection) {
    assertTrue(message, isNull(collection) || collection.isEmpty() || collection.stream().allMatch(Objects::isNull));
  }

  public static void assertBlank(Collection collection) {
    assertBlank(null, collection);
  }

  public static void assertNotBlank(String message, Collection collection) {
    assertTrue(message, nonNull(collection) && !collection.isEmpty() && collection.stream().allMatch(Objects::nonNull));
  }

  public static void assertNotBlank(Collection collection) {
    assertNotBlank(null, collection);
  }

  public static void assertNotEmpty(String message, Map map) {
    assertTrue(message, nonNull(map) && !map.isEmpty());
  }

  public static void assertNotEmpty(Map map) {
    assertNotEmpty(null, map);
  }

  public static void assertNotEmpty(String message, String string) {
    assertTrue(message, nonNull(string) && !string.isEmpty());
  }

  public static void assertNotEmpty(String string) {
    assertNotEmpty(null, string);
  }

  public static void assertNotBlank(String message, String string) {
    assertTrue(message, nonNull(string) && string.trim().length() > 0);
  }

  public static void assertNotBlank(String string) {
    assertNotEmpty(null, string);
  }
}
