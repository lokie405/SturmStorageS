//package com.seryoga.sturmstorages.web
//
//import com.seryoga.sturmstorages.db.Product
//import com.seryoga.sturmstorages.db.ProductResponse
//import com.seryoga.sturmstorages.web.KtorClient.client
//import io.ktor.client.HttpClient
//import io.ktor.client.engine.okhttp.OkHttp
//import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
//import io.ktor.client.request.get
//import io.ktor.client.request.request
//import io.ktor.client.statement.HttpResponse
//import io.ktor.http.HttpMethod
//import io.ktor.serialization.kotlinx.json.json
//import kotlinx.serialization.json.Json
//import kotlinx.serialization.json.JsonObject
//
//object KtorClient {
//    val client = HttpClient(OkHttp) {
//        install(ContentNegotiation) {
//            json(Json { ignoreUnknownKeys = true })
//        }
//    }
//}
//
//suspend fun fetchProducts(): List<Product> {
//    val response: ProductResponse = KtorClient.client.get("https://example.com/products")
//    val jsonArray = response["data"]!!.jsonArray
//
//    return jsonArray.map {
//        val obj = it.jsonObject
//        Product(
//            name = obj["name"]!!.jsonPrimitive.content,
//            price = obj["price"]!!.jsonPrimitive.content,
//            quantity = obj["quantity"]!!.jsonPrimitive.content,
//            provider = obj["provider"]!!.jsonPrimitive.content
//        )
//    }
//}