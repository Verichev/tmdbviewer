plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.ksp)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.hilt)
}

android {
  namespace = "com.tmdbviewer"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.tmdbviewer"
    minSdk = 21
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }

  buildFeatures {
    compose = true
  }

}

dependencies {
  implementation(libs.compose.ui)
  implementation(libs.compose.material)
  implementation(libs.compose.foundation)
  implementation(libs.compose.tooling)
  implementation(libs.lifecycle.runtime.compose)
  implementation(libs.activity.compose)
  implementation(libs.retrofit)
  implementation(libs.retrofit.gson)
  implementation(libs.coroutines)
  implementation(libs.hilt.android)
  implementation(libs.compose.navigation)
  implementation(libs.hilt.navigation.compose)
  implementation(libs.okhttp.logging.interceptor)
  implementation(libs.paging.compose)
  implementation(libs.compose.shimmer)
  implementation(libs.coil.compose)
  implementation(libs.paging.compose)
  implementation(libs.paging.runtime)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)

  ksp(libs.hilt.compiler)

  testImplementation(libs.junit.jupiter.api)
  testImplementation(libs.junit.jupiter.engine)
  testImplementation(libs.mockito.core)
  testImplementation(libs.mockito.junit.jupiter)
  testImplementation(libs.okhttp.mockwebserver)
  testImplementation(libs.mockito.kotlin)
  testImplementation(libs.coroutines.test)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

}


tasks.withType<Test> {
  useJUnitPlatform()
}