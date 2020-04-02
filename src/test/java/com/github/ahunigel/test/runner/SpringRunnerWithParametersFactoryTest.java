package com.github.ahunigel.test.runner;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nigel on 2020/3/21.
 *
 * @author nigel
 */
@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(SpringRunnerWithParametersFactory.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SpringRunnerWithParametersFactoryTest {
  @Autowired
  private Environment environment;

  private String input;

  @Parameterized.Parameters(name = "{0}")
  public static List<String> data() {
    return Arrays.asList("julia", "nigel", "sara", "vincent");
  }

  public SpringRunnerWithParametersFactoryTest(String input) {
    this.input = input;
  }

  @SneakyThrows
  @Test
  public void testEnv() {
    assertThat(input).isNotEmpty();
    assertThat(input.length()).isBetween(4, 7);
  }

}
