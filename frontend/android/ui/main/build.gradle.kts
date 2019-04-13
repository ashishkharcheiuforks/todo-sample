import org.gradle.kotlin.dsl.project

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Android.compileSdkVersion)
    dataBinding {
        isEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    defaultConfig {
        minSdkVersion(Android.minSdkVersion)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = false
        }
    }
    lintOptions {
        setCheckReleaseBuilds(false)
    }
}

dependencies {
    androidTestImplementation(Libs.androidx_test_core)
    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.androidx_test_rules)
    androidTestImplementation(Libs.androidx_test_runner)
    androidTestImplementation(Libs.core_testing)
    androidTestImplementation(Libs.mockito_android)
    androidTestImplementation(Libs.mockito_core)
    implementation(Libs.android_paging_extensions)
    implementation(Libs.core_ktx)
    implementation(Libs.kotlin_stdlib_jdk8)
    implementation(Libs.timber)
    testImplementation(Libs.androidx_test_core)
    testImplementation(Libs.core_testing)
    testImplementation(Libs.junit_junit)
    testImplementation(Libs.mockito_kotlin)
    testImplementation(Libs.robolectric)
    implementation(project(":frontend:android:domain"))
    implementation(project(":frontend:android:data"))
    api(project(":frontend:android:ui:main:tasks"))
    implementation(project(":frontend:android:domain"))
    implementation(project(":frontend:android:ui:main:mainNavigation"))
    implementation(project(":frontend:android:base"))
    api(project(":frontend:android:ui:main:taskdetail"))
    api(project(":frontend:android:ui:main:about"))
    api(project(":frontend:android:ui:main:addedit"))
    api(project(":frontend:android:ui:main:search"))
    api(project(":frontend:android:ui:main:setting"))
    api(project(":frontend:android:ui:main:statistics"))
    implementation(project(":frontend:android:navigation"))
    implementation(Libs.epoxy)
    implementation(Libs.epoxy_databinding)
    implementation(Libs.epoxy_paging)
    kapt(Libs.epoxy_processor)
    implementation(Libs.mvrx)
    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)
    implementation(project(":frontend:android:base"))
    implementation(Libs.navigation_fragment_ktx)
    compileOnly(Libs.assisted_inject_annotations_dagger2)
    kapt(Libs.assisted_inject_processor_dagger2)
    kapt(Libs.dagger_android_processor)
    kapt(Libs.dagger_compiler)
    implementation(Libs.dagger_android_support)
    implementation(Libs.constraintlayout)
    kapt(Libs.epoxy_processor)
    implementation(Libs.epoxy)
    implementation(Libs.epoxy_databinding)
    implementation(Libs.epoxy_paging)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.navigation_fragment_ktx)
}

kapt {
    arguments {
        arg("dagger.formatGeneratedSource", "disabled")
        arg("dagger.gradle.incremental", "enabled")
    }
    javacOptions {
        option("-Xmaxerrs", 500)
    }
    // not sure
    useBuildCache = true
}
