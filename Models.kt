package com.example.zoro

class Models {
}

data class NonProfit(
    val name: String,
    val address : String,
    val email: String
)

data class EmailRequest(
    val template: String,
    val recipients: List<String>,
    val eventKey: String
)

data class Email(
    val to: String,
    val body: String,
    val idempotencyKey: String
)
