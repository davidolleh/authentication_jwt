package com.example.authentication.authentication_jwt.config

import com.example.authentication.authentication_jwt.interceptor.AuthenticationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class MvcConfiguration @Autowired constructor(
    val authenticationInterceptor: AuthenticationInterceptor
) : WebMvcConfigurer {


    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/auth/local/**")
    }
}