package com.github.ahunigel.test.junit.runner;

import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.parameterized.ParametersRunnerFactory;
import org.junit.runners.parameterized.TestWithParameters;

/**
 * support run spring test with parameters
 * <pre>
 *   @RunWith(Parameterized.class)
 *   @Parameterized.UseParametersRunnerFactory(SpringRunnerWithParametersFactory.class)
 *   public class FooTest {}
 * </pre>
 * Created by nigel on 2020/3/22.
 *
 * @author nigel
 * @see org.junit.runners.Parameterized
 * @see org.springframework.test.context.junit4.SpringRunner
 */
public class SpringParametersRunnerFactory implements ParametersRunnerFactory {
  @Override
  public Runner createRunnerForTestWithParameters(TestWithParameters test) throws InitializationError {
    return new SpringRunnerWithParameters(test);
  }
}
