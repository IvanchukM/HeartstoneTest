plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kotlinSerialization)
  alias(libs.plugins.dagger.hilt.android)
  alias(libs.plugins.kapt)
  alias(libs.plugins.ksp)
  alias(libs.plugins.androidx.room)
}

android {
  namespace = "com.example.heartstonetestapp"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.heartstonetestapp"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }

    buildConfigField("String", "API_KEY", "\"55a7575255msh3c4e2019f134e2ep111b8bjsnb26ec382794f\"")
    buildConfigField("String", "BASE_URL", "\"https://omgvamp-hearthstone-v1.p.rapidapi.com\"")
    buildConfigField("String", "API_HOST", "\"omgvamp-hearthstone-v1.p.rapidapi.com\"")
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
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

room {
  schemaDirectory("${rootProject.projectDir}/schemas")
}

dependencies {

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)

  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.lifecycle.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.hilt.navigation.compose)

  implementation(libs.retrofit)
  implementation(libs.retrofit.adapters.result)
  implementation(libs.retrofit.converter.kotlinx.serialization)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.okhttp)
  implementation(libs.androidx.hilt.common)
  implementation(libs.androidx.hilt.work)
  implementation(libs.material)
  debugImplementation(libs.okhttp.logging.interceptor)

  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.android)

  implementation(libs.dagger.hilt.android)
  kapt(libs.dagger.hilt.compiler)
  kapt(libs.hilt.androidx.compiler)

  implementation(libs.coil.compose)
  implementation(libs.coil.core)

  ksp(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)

  implementation (libs.androidx.work.runtime.ktx)

  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}