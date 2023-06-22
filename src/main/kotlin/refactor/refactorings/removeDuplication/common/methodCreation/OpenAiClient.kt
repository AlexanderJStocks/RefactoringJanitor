package refactor.refactorings.removeDuplication.common.methodCreation

import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.Serializable

class OpenAiClient {
    private val client = HttpClient(Apache)
    private val baseUrl = "https://api.openai.com"
    private val accessToken = "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"

    @Serializable
    data class OpenAIPrompt(val model: String, val messages: List<OpenAIMessage>)

    @Serializable
    data class OpenAIMessage(val role: String, val content: String)

    @Serializable
    data class OpenAIResponse(val id: String, val `object`: String, val choices: List<OpenAIChoice>)

    @Serializable
    data class OpenAIChoice(val message: OpenAIMessage)

    private fun buildRequest(
        url: String, method: HttpMethod, accessToken: String, body: String? = null
    ): HttpRequestBuilder {
        return HttpRequestBuilder().apply {
            this.url("$baseUrl$url")
            this.method = method
            this.header(HttpHeaders.Authorization, "Bearer $accessToken")
            this.accept(ContentType.Application.Json)
            this.header("X-OpenAI-Api-Version", "2022-11-28")
            if (body != null) {
                this.setBody(TextContent(body, ContentType.Application.Json))
            }
        }
    }

    suspend fun askGptForMethodNames(methodBody: String): List<String> {
        val apiUrl = "/v1/chat/completions"
        val systemMessage = OpenAIMessage("system", "You are a helpful assistant.")
        val userMessage = OpenAIMessage(
            "user",
            "Given the following method body: $methodBody. Please generate three potential method names, only respond with the three words that are the method names and nothing else, separated by spaces, with no introduction to your response or punctuation or numbers."
        )
        val messageBody = Gson().toJson(OpenAIPrompt("gpt-3.5-turbo", listOf(systemMessage, userMessage)))
        val request = buildRequest(apiUrl, HttpMethod.Post, accessToken, messageBody)

        var retryCount = 0
        while (retryCount < 3) {
            val response: HttpResponse? = withTimeoutOrNull(10000L) {
                client.request(request)
            }

            if (response != null) {
                val responseText = response.bodyAsText()
                return try {
                    val choices = Gson().fromJson(responseText, OpenAIResponse::class.java).choices
                    val assistantMessage = choices.firstOrNull()?.message?.content
                    val cleanedMessage = assistantMessage?.replace("\\d+\\.".toRegex(), "")?.trim() ?: ""

                    // Remove all punctuation
                    val noPunctuationMessage = cleanedMessage.replace("\\p{Punct}".toRegex(), "")

                    val methodNames = noPunctuationMessage.split(" ").map { it.trim() }
                    methodNames
                } catch (e: Exception) {
                    emptyList()
                }
            } else {
                retryCount++
                delay(1000L) // Wait for a second before retrying
            }
        }

        return emptyList()
    }
}
