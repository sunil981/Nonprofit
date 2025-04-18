package com.example.zoro

import org.springframework.stereotype.Repository

@Repository
class NonprofitRepository  {
    private val nonprofits = mutableMapOf<String, NonProfit>()

    fun save(nonprofit: NonProfit) {
        nonprofits[nonprofit.email] = nonprofit
    }

    fun findAllByEmail(emails: List<String>): List<NonProfit> {
        val result = mutableListOf<NonProfit>()
        emails.forEach { nonprofits.get(it)?.let { nonprofit -> result.add(nonprofit) } }
        return result
    }
}

@Repository
class EmailRepository {
    private val emails = mutableListOf<Email>()

    fun saveAll(newEmails: List<Email>) {
        emails.addAll(newEmails)
    }

    fun findAll(): List<Email> = emails.toList()
}