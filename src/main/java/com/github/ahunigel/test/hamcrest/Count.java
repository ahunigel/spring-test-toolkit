package com.github.ahunigel.test.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class Count<T> extends TypeSafeDiagnosingMatcher<Iterable<? extends T>> {
  private final int expect;
  private final Matcher<? super T> matcher;

  public Count(int expect, Matcher<? super T> matcher) {
    this.expect = expect;
    this.matcher = matcher;
  }

  @Override
  public boolean matchesSafely(Iterable<? extends T> collection, Description mismatchDescription) {
    int matched = 0;
    for (T t : collection) {
      if (matcher.matches(t)) {
        matched++;
      }
    }
    if (expect == matched) {
      return true;
    } else {
      mismatchDescription.appendText("matched ");
      matcher.describeMismatch(matched, mismatchDescription);
      return false;
    }
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("count ").appendValue(expect).appendText(" items are ").appendDescriptionOf(matcher);
  }

  /**
   * @param count       the quality to expect to be matched
   * @param itemMatcher the matcher to apply to every item provided by the examined {@link Iterable}
   */
  public static <U> Matcher<Iterable<? extends U>> countItem(int count, final Matcher<U> itemMatcher) {
    return new Count<>(count, itemMatcher);
  }
}
