package com.github.ahunigel.test;

import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;

import java.lang.annotation.*;

/**
 * Run JUnit 4 tests only on windows operation system
 * <p>
 * Created by Nigel.Zheng on 2019/5/20.
 *
 * @author Nigel.Zheng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ProfileValueSourceConfiguration(EnvironmentProfileValueSource.class)
@IfProfileValue(name = "OS", values = {"Windows_NT"})
public @interface RunTestOnWindowsOnly {
}
