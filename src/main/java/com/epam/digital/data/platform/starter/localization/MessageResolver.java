/*
 * Copyright 2021 EPAM Systems.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epam.digital.data.platform.starter.localization;

import com.epam.digital.data.platform.starter.localization.config.LocalizationAutoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Service that is used for localization. Uses a {@code messageSource} that is initialized for
 * searching props in {@code lang/messages.properties} file (see {@link
 * LocalizationAutoConfiguration}).
 */
@Component
@RequiredArgsConstructor
public class MessageResolver {

  private final MessageSource messageSource;

  /**
   * Used for getting a parameterized localized message by string code. Parameters must be described
   * as {@code {n}} where n is a message parameter index (starting with 0).
   *
   * @param msgCode string code of a message
   * @param args    args that must fill the parameters in localized message
   * @return localized message
   */
  public String getMessage(String msgCode, Object... args) {
    return messageSource.getMessage(msgCode, args, LocaleContextHolder.getLocale());
  }

  /**
   * Used for getting a localized message by string code.
   *
   * @param msgCode string code of a message
   * @return localized message
   */
  public String getMessage(String msgCode) {
    return this.getMessage(msgCode, (Object[]) null);
  }

  /**
   * Used for getting a parameterized localized message by {@code MessageTitle}. Parameters must be
   * described as {@code {n}} where n is a message parameter index (starting with 0).
   *
   * @param messageTitle messageTitle that returns a string code of a message
   * @param args         args that must fill the parameters in localized message
   * @return localized message
   */
  public String getMessage(MessageTitle messageTitle, Object... args) {
    return this.getMessage(messageTitle.getTitleKey(), args);
  }

  /**
   * Used for getting a localized message by string code.
   *
   * @param messageTitle that returns a string code of a message
   * @return localized message
   */
  public String getMessage(MessageTitle messageTitle) {
    return this.getMessage(messageTitle.getTitleKey());
  }
}
