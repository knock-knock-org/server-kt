package com.knockknock.server.utils

import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class BeanPrinter(
        val applicationContext: ApplicationContext
): CommandLineRunner {
    override fun run(vararg args: String?) {
        val beans = applicationContext.beanDefinitionNames

        for(bean in beans){
            println("name : $bean")
        }
    }

}