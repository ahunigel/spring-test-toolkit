package com.github.ahunigel.test;

import org.springframework.test.annotation.ProfileValueSource;

/**
 * Merge environment and system properties as profile value source
 * <p>
 * Created by Nigel.Zheng on 2019/5/14.
 *
 * @author Nigel.Zheng
 */
public class MergedSystemEnvAndPropertyProfileValueSource implements ProfileValueSource {

  @Override
  public String get(String key) {
    return System.getenv(key) != null ? System.getenv(key) : System.getProperty(key);
  }
}
