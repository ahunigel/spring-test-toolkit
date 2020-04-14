package com.github.ahunigel.test.junit;

import org.junit.Test;

import java.util.*;

import static com.github.ahunigel.test.junit.Assertions.*;

/**
 * Created by Nigel.Zheng on 4/14/20 014.
 *
 * @author Nigel.Zheng
 */
public class AssertionsTest {
  @Test
  public void assertNotEmpty_list() {
    List list = Arrays.asList("test", null);
    assertNotEmpty(list);
    assertNotEmpty("list should not be empty", list);
  }

  @Test
  public void assertBlank_list() {
    List list = Arrays.asList(null, null);
    assertBlank(list);
    assertBlank("list should be blank", list);
  }

  @Test
  public void assertNotBlank_list() {
    List list = Arrays.asList("test", "null");
    assertNotBlank(list);
    assertNotBlank("list should not be blank", list);
  }

  @Test
  public void assertNotEmpty_set() {
    Set set = new HashSet();
    set.add("test");
    set.add(null);
    assertNotEmpty(set);
    assertNotEmpty("set should not be empty", set);
  }

  @Test
  public void assertBlank_set() {
    Set set = new HashSet();
    set.add(null);
    set.add(null);
    assertBlank(set);
    assertBlank("set should be blank", set);
  }

  @Test
  public void assertNotBlank_set() {
    Set set = new HashSet();
    set.add("test");
    set.add("null");
    assertNotBlank(set);
    assertNotBlank("list should not be blank", set);
  }

  @Test
  public void assertNotEmpty_map() {
    Map map = new HashMap<>();
    map.put("key", "val");
    assertNotEmpty(map);
    assertNotEmpty("map should not be empty", map);
  }

  @Test
  public void assertNotEmpty_string() {
    String s = " ";
    assertNotEmpty(s);
    assertNotEmpty("string should not be empty", s);
  }

  @Test
  public void assertNotBlank_string() {
    String s = " s ";
    assertNotBlank(s);
    assertNotBlank("string should not be blank", s);
  }
}
