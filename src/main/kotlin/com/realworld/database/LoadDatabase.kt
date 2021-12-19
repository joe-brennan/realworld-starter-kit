package com.realworld.database

import com.realworld.domain.user.UserRepository
import org.springframework.boot.CommandLineRunner
import com.realworld.domain.user.User
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class LoadDatabase {
    @Bean
    fun initDatabase(repository: UserRepository): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            log.info("Preloading " + repository.save(User("Bilbo Baggins", "bilbo@mail.com", "password")))
            log.info("Preloading " + repository.save(User("Frodo Baggins", "frodo@mail.com", "password")))
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoadDatabase::class.java)
    }
}