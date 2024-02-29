import org.jetbrains.kotlin.cli.jvm.main

buildscript {
//    ext {
//        compose_ui_version = "1.6.2"
//    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.22")
    }

    //tasks.named<JavaExec>("run") {
    //    standardInput = System.`in` }

}

plugins {
    id("com.android.application") version "8.1.2" apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
}
