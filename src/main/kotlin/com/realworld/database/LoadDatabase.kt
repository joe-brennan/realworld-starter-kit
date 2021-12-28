package com.realworld.database

import com.realworld.domain.profile.Profile
import com.realworld.domain.user.User
import com.realworld.domain.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class LoadDatabase {
    @Bean
    fun initDatabase(repository: UserRepository): CommandLineRunner {
        return CommandLineRunner {
            val username1 = "Bilbo Baggins"
            val user1 = User(username1, "bilbo@mail.com", "password")
            val profile1 = Profile(username1)
            user1.profile = profile1
            profile1.user = user1

            log.info("Preloading " + repository.save(user1))

            val username2 = "Frodo Baggins"
            val user2 = User(username2, "frodo@mail.com", "password")
            val profile2 = Profile(username2)
            user2.profile = profile2
            profile2.user = user2

            log.info("Preloading " + repository.save(user2))
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoadDatabase::class.java)
    }
}