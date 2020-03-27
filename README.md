# spring-test-toolkit
[![](https://jitpack.io/v/ahunigel/spring-test-toolkit.svg)](https://jitpack.io/#ahunigel/spring-test-toolkit)

Provide an additional test toolkit library for spring framework.

## Features
- JUnit 4 `@IfProfileValue` profile source enhancement
    - `@ProfileValueSourceConfiguration(EnvironmentProfileValueSource.class)`, use environment as profile value source
    - `@ProfileValueSourceConfiguration(MergedSystemEnvAndPropertyProfileValueSource.class)`, use environment and system properties as profile value source
- JUnit 4 `@RunTestOnWindowsOnly` annotation, restrict JUnit 4 tests running only on windows operation system
- `SpringRunnerWithParametersFactory`
    - JUnit 4 `@Parameterized` support for `SpringRunner`, support run spring test with parameters

## How to use

### Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
## Step 2. Add the dependency
```groovy
dependencies {
    implementation 'com.github.ahunigel:spring-test-toolkit:{version}'
}
```
_Refer to https://jitpack.io/#ahunigel/spring-test-toolkit for details._

## Step 3. Sample code
```java
@ProfileValueSourceConfiguration(EnvironmentProfileValueSource.class)
public class FooTest {
}
```

```java
@ProfileValueSourceConfiguration(MergedSystemEnvAndPropertyProfileValueSource.class)
public class FooTest {}
```

```java
@RunTestOnWindowsOnly
public class FooTest {}
```

```java
@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(SpringRunnerWithParametersFactory.class)
public class FooTest {}
```
## References
- [spring-test-toolkit](https://github.com/ahunigel/spring-test-toolkit)
- [spring-security-oauth2-test](https://github.com/ahunigel/spring-security-oauth2-test)
- [spring-toolkit](https://github.com/ahunigel/spring-toolkit)

## TODOs

- none
