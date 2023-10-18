/*
 * Copyright 2023 EPAM Systems.
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class JsonMessageSource extends AbstractMessageSource {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Map<Locale, Map<String, String>> messageCache = new ConcurrentHashMap<>();
  private String baseName;

  @Override
  protected MessageFormat resolveCode(String code, Locale locale) {
    var messages = loadResource(locale);
    if (messages != null && messages.containsKey(code)) {
      return new MessageFormat(messages.get(code), locale);
    }
    return null;
  }

  private Map<String, String> loadResource(Locale locale) {
    Map<String, String> messages = messageCache.get(locale);
    if (messages != null) {
      return messages;
    }
    String jsonMessageFilePath = baseName + "_" + locale.getLanguage() + ".json";
    Resource resource = new ClassPathResource(jsonMessageFilePath);
    try {
      messages = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {});
      messageCache.put(locale, messages);
    } catch (IOException e) {
      logger.warn("JsonResource [" + baseName + "] not found for MessageSource: " + e.getMessage());
    }
    return messages;
  }

  public String getBaseName() {
    return baseName;
  }

  public void setBaseName(String baseName) {
    this.baseName = baseName;
  }
}
