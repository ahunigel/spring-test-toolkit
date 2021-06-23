package com.github.ahunigel.test.mockito;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * Created by Nigel on 6/23/2021.
 *
 * @author Nigel
 */
public class MockitoBaseTest {
  private AutoCloseable closeable;

  @Before
  public void openMocks() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @After
  @SneakyThrows
  public void releaseMocks() {
    closeable.close();
  }
}
