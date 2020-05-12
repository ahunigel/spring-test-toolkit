package com.github.ahunigel.test.matcher;

import com.github.ahunigel.test.mockmvc.EnhanceResultMatcher;
import com.github.ahunigel.test.mockmvc.FormRequestMatchers;

/**
 * Created by nigel on 2020/3/15.
 *
 * @author nigel
 * @deprecated use {@link FormRequestMatchers} instead, should remove in next release
 */
@Deprecated
public class FormDataRequestMatchers<K, V> {

  /**
   * @since 1.1
   * @deprecated use {@link EnhanceResultMatcher#form()}  or {@link FormRequestMatchers#form()} instead
   */
  @Deprecated
  public static FormRequestMatchers form() {
    return new FormRequestMatchers();
  }

}
