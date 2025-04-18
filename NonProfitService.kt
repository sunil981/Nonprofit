package com.example.zoro

import org.springframework.stereotype.Service

@Service
class NonprofitService(
    private val nonprofitRepo: NonprofitRepository,
    private val emailRepo: EmailRepository,
) {
    fun addNonprofit(nonprofit: NonProfit): String {
        if(nonprofitRepo.findAllByEmail(listOf(nonprofit.email)).isNotEmpty())
            return " Non profit already exists"

        nonprofitRepo.save(nonprofit)
        return "Nonprofit created"
    }

    fun sendEmails(request: EmailRequest): List<Email> {
        var emails = getEmailPayloads(request)

        // Filter already sent emails
        val idempotencykeys = emailRepo.findAll().map { it.idempotencyKey }
        emails = emails.filter { !idempotencykeys.contains(it.idempotencyKey) }

        // mocked email client call
        // emailclient.sendBulk(emails)

        emailRepo.saveAll(emails);
        return emails
    }

    fun getEmailPayloads(request: EmailRequest): List<Email> {
        val nonProfits = nonprofitRepo.findAllByEmail(request.recipients)

        return nonProfits.map { nonprofit ->
           val body = request.template
                .replace("{ name }", nonprofit.name)
                .replace("{ address }", nonprofit.address).toString()
            Email(
                to = nonprofit.email,
                body = body,
                idempotencyKey = request.eventKey+nonprofit.email
            )
        }
    }

    fun getAllEmails(): List<Email> = emailRepo.findAll()
}
