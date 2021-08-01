package com.epam.digital.data.platform.starter.localization.enums;

import com.epam.digital.data.platform.starter.localization.MessageTitle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestEnum implements MessageTitle {
  TEST_ENUM_WITH_PARAM("test.message.key.with.param"),
  TEST_ENUM("test.message.key");

  private final String titleKey;

}
