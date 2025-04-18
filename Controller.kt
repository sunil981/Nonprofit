package com.example.zoro

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/nonprofits")
class NonprofitController(
    private val service: NonprofitService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createNonprofit(@RequestBody nonprofit: NonProfit): String {
       return service.addNonprofit(nonprofit)
    }

    @PostMapping("/send-emails")
    @ResponseStatus(HttpStatus.OK)
    fun sendEmails(@RequestBody request: EmailRequest): List<Email> {
        return service.sendEmails(request)
    }

    @GetMapping("/sent-emails")
    @ResponseStatus(HttpStatus.OK)
    fun getSentEmails(): List<Email> {
        return service.getAllEmails()
    }
}
