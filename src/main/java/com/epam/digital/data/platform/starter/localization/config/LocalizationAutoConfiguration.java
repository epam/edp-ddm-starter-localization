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

package com.epam.digital.data.platform.starter.localization.config;

import com.epam.digital.data.platform.starter.localization.MessageResolver;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * The class represents implementation of {@link WebMvcConfigurer} that is used for registering
 * custom {@link LocaleChangeInterceptor} bean. Also, the class contains a list of beans for web
 * configuration. Each method that produces a bean - must be annotated with @Bean annotation to be
 * managed by the Spring container. The method should create, set up and return an instance of a
 * bean.
 */
@Configuration
@ComponentScan(basePackageClasses = MessageResolver.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class LocalizationAutoConfiguration implements WebMvcConfigurer {

  @Bean
  @ConditionalOnMissingBean(PropertySourcesPlaceholderConfigurer.class)
  public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public LocaleResolver localeResolver(@Value("${locale.language:ukr}") String locale) {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(new Locale(locale));
    return localeResolver;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Bean
  public ResourceBundleMessageSource messageSource(
      @Value("${locale.resource-basename:lang/messages}") String resourceBasename) {
    ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
    rs.setUseCodeAsDefaultMessage(true);
    rs.setDefaultEncoding(StandardCharsets.UTF_8.name());
    rs.setBasename(resourceBasename);
    return rs;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }
}
