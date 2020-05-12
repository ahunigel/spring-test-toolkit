package com.github.ahunigel.test.mockmvc;

import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.util.AssertionErrors.fail;

/**
 * Enhanced movkmvc request matcher for <code>spring-test</code>
 * Created by nigel on 5/12/2020.
 *
 * @author nigel
 * @see org.springframework.test.web.client.RequestMatcher
 * @since 1.6
 */
public class EnhanceResultMatcher {
  public static FormRequestMatchers form() {
    return new FormRequestMatchers();
  }

  /**
   * Static method for matching with an array of result matchers.
   *
   * @param matchers the matchers
   * @implNote transferred from sprint-test:5.1 in case using spring-test:5.0
   * @see org.springframework.test.web.client.RequestMatcher
   * @since spring-test:5.1
   */
  public static ResultMatcher matchAll(ResultMatcher... matchers) {
    return result -> {
      for (ResultMatcher matcher : matchers) {
        matcher.match(result);
      }
    };
  }

  /**
   * a shortcut for {@link #matchAll(ResultMatcher...)}
   *
   * @param matchers
   * @return
   */
  public static ResultMatcher allOf(ResultMatcher... matchers) {
    return matchAll(matchers);
  }

  public static ResultMatcher anyOf(ResultMatcher... matchers) {
    return result -> {
      int matchCount = 0;
      for (ResultMatcher matcher : matchers) {
        try {
          matcher.match(result);
          matchCount++;
        } catch (Exception e) {
        }
      }
      if (matchCount == 0) {
        fail("None of the matchers matched.");
      }
    };
  }
}
