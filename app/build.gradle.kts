plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerhilt)
}

android {
    namespace = "rachman.forniandi.pokemonapiapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "rachman.forniandi.pokemonapiapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
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


    //Retrofit
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)


    //dagger hilt
    implementation (libs.hilt.android)
    implementation(libs.androidx.navigation.compose.android)
    implementation(libs.hilt.navigation.compose)
    ksp (libs.hilt.compiler)
    ksp (libs.dagger.compiler)

    //viewmodel
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.activity.ktx)

    //paging
    implementation(libs.androidx.paging)
    implementation(libs.androidx.paging.compose)

    //glide
    implementation(libs.glide)

    //lifecycle
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //datastore
    implementation(libs.androidx.datastore.preferences)

    //chucker
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.no.op)

    //coil
    implementation(libs.coil.compose)

    //lottie
    implementation(libs.lottie.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}