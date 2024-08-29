package com.tmdbviewer.common.data

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AuthInterceptorTest {

  private lateinit var mockWebServer: MockWebServer
  private lateinit var client: OkHttpClient

  @BeforeEach
  fun setUp() {
    mockWebServer = MockWebServer()
    mockWebServer.start()

    val interceptor = AuthInterceptor()
    client = OkHttpClient.Builder()
      .addInterceptor(interceptor)
      .build()
  }

  @AfterEach
  fun tearDown() {
    mockWebServer.shutdown()
  }

  @Test
  fun intercept_shouldAddAuthorizationHeader() {
    val apiKey = ApiConstants.TMDB_API_KEY
    mockWebServer.enqueue(MockResponse().setResponseCode(200))

    val request = Request.Builder()
      .url(mockWebServer.url("/test"))
      .build()

    val response: Response = client.newCall(request).execute()
    val recordedRequest = mockWebServer.takeRequest()

    assertNotNull(recordedRequest.getHeader("Authorization"))
    assertEquals("Bearer $apiKey", recordedRequest.getHeader("Authorization"))
  }

  @Test
  fun intercept_shouldProceedWithModifiedRequest() {
    mockWebServer.enqueue(MockResponse().setResponseCode(200))

    val request = Request.Builder()
      .url(mockWebServer.url("/test"))
      .build()

    val response: Response = client.newCall(request).execute()

    assertEquals(200, response.code)
  }

  @Test
  fun intercept_shouldNotModifyOtherHeaders() {
    mockWebServer.enqueue(MockResponse().setResponseCode(200))

    val request = Request.Builder()
      .url(mockWebServer.url("/test"))
      .addHeader("Custom-Header", "CustomValue")
      .build()

    val response: Response = client.newCall(request).execute()
    val recordedRequest = mockWebServer.takeRequest()

    assertEquals("CustomValue", recordedRequest.getHeader("Custom-Header"))
  }
}