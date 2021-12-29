package com.realworld.domain.profile

import com.realworld.domain.profile.Profile

interface ProfileApi {
    fun getProfile(username: String?): Profile
    fun followUser(username: String?): Profile
    fun unfollowUser(username: String?): Profile
}