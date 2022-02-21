plugins {
    kotlin("multiplatform") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
}

group = "me.mohitto"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "16"
        }

        withJava()
    }

    js(IR) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    fun versionOf(name: String): String {
        return project.property("version.$name") as String
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${versionOf("kotlinx.serialization-json")}")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:${versionOf("kotlinx.datetime")}")
                implementation("io.arrow-kt:arrow-optics:${versionOf("arrow")}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
