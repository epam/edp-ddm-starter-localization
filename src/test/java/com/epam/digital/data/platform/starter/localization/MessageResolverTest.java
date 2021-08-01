package com.epam.digital.data.platform.starter.localization;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.digital.data.platform.starter.localization.config.LocalizationAutoConfiguration;
import com.epam.digital.data.platform.starter.localization.enums.TestEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import(LocalizationAutoConfiguration.class)
public class MessageResolverTest {

  @Autowired
  private MessageResolver messageResolver;

  @Test
  public void testMessageResolver() {
    var result = messageResolver.getMessage(TestEnum.TEST_ENUM);

    assertThat(result).isEqualTo("Test message value");
  }

  @Test
  public void testMessageResolverWithParams() {
    var param = "param1";

    var result = messageResolver.getMessage(TestEnum.TEST_ENUM_WITH_PARAM, param);

    assertThat(result).isEqualTo("Test message value with param param1");
  }
}
