object Deps {
    object Compose {
        private const val version = "1.2.0-alpha05"
        const val ui = "androidx.compose.ui:ui:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"

        const val testJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
        const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
        const val testManifest = "androidx.compose.ui:ui-test-manifest:$version"
    }

    object Androidx {
        const val coreKtx = "androidx.core:core-ktx:1.9.0-alpha01"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha03"
        const val activityCompose = "androidx.activity:activity-compose:1.5.0-alpha03"
        const val navigationCompose = "androidx.navigation:navigation-compose:2.5.0-alpha03"

        const val material3 = "androidx.compose.material3:material3:1.0.0-alpha06"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"
        const val extJunit = "androidx.test.ext:junit:1.1.4-alpha04"
        const val espressoCore = "androidx.test.espresso:espresso-core:3.5.0-alpha04"
    }
}