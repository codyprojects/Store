plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.fuwumai.ui.settings"
    compileSdk = 32

    defaultConfig {
        minSdk = 29
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha06"
    }
}

dependencies {

    implementation(project(":core-domain"))

    with(Deps.Compose) {
        implementation(ui)
        implementation(toolingPreview)

        androidTestImplementation(testJunit4)
        debugImplementation(uiTooling)
        debugImplementation(testManifest)
    }

    with(Deps.Androidx) {
        implementation(coreKtx)
        implementation(runtimeKtx)
        implementation(activityCompose)
        implementation(navigationCompose)

        implementation(material3)
    }

    with(Deps.Test) {
        testImplementation(junit)
        androidTestImplementation(extJunit)
        androidTestImplementation(espressoCore)
    }
}