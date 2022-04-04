plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.fuwumai.store"
    compileSdk = 32

    defaultConfig {
        applicationId = "com.fuwumai.store"
        minSdk = 29
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("release") {
            storeFile = file("../singing/store.jks")
            storePassword = System.getenv("STORE_KSTOREPWD")
            keyAlias = "store"
            keyPassword = System.getenv("STORE_KEYPWD")
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }

        release {
            isMinifyEnabled = false

            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += listOf("client", "version")

    productFlavors {

        create("customer") {
            dimension = "client"
            applicationIdSuffix = ".customer"
            versionNameSuffix = "-customer"
        }

        create("merchant") {
            dimension = "client"
            applicationIdSuffix = ".merchant"
            versionNameSuffix = "-merchant"
        }

        create("admin") {
            dimension = "client"
            applicationIdSuffix = ".admin"
            versionNameSuffix = "-admin"
        }

        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            buildConfigField("String", "BASE_URL", "\"http://dev.com/api/\"")

        }
        create("prod") {
            dimension = "version"
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"

            buildConfigField("String", "BASE_URL", "\"http://prod.com/api/\"")
        }
    }

}

//androidComponents {
//    beforeVariants { variantBuilder ->
//        if (variantBuilder.productFlavors.contains("version" to "prod")) {
//            variantBuilder.enable = false
//        }
//        if (variantBuilder.buildType.equals("release")) {
//            variantBuilder.enable = false
//        }
//    }
//}

dependencies {

    implementation(project(":core-domain"))
    implementation(project(":ui-search"))
    implementation(project(":ui-settings"))
//    implementation(project(":ui:profile"))
//    "customerImplementation"(project(":ui:profile"))
    //"customerImplementation"(project(":ui:profile", configuration = "customer"))

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