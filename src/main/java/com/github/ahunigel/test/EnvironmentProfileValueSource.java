package com.github.ahunigel.test;

import org.springframework.test.annotation.ProfileValueSource;

/**
 * Use environment as profile value source
 * <p>
 * Created by Nigel.Zheng on 2019/5/14.
 *
 * @author Nigel.Zheng
 * @since 1.0
 */
public class EnvironmentProfileValueSource implements ProfileValueSource {

  @Override
  public String get(String key) {
    return System.getenv(key);
  }
}
