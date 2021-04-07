import app.futured.arkitekt.DependencyUpdates
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import java.net.URI

buildscript {
    repositories {
        google()
        jcenter()
        flatDir {
            dirs("templates/build/libs")
        }
    }
    dependencies {
        classpath(Deps.gradlePlugin)
        classpath(kotlin(Deps.Kotlin.gradlePlugin, Versions.kotlin))
        classpath(Deps.DI.hiltPlugin)
        classpath(Deps.AndroidX.safeArgsPlugin)
        classpath(Deps.Plugins.mavenPublish)
        classpath(Deps.Plugins.dokka)
    }
}

plugins {
    idea
    id(Deps.Plugins.detekt) version Versions.detekt
    id(Deps.Plugins.ktlint) version Versions.ktlint
//    uncomment this line for local testing purposes during template module development
//    id(ProjectSettings.Templates.id) version ProjectSettings.version
}

tasks {
    register<DependencyUpdates>("dependencyUpdates")
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = URI("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = Deps.Plugins.ktlint)

    ktlint {
        version.set(Versions.ktlintExtension)
        ignoreFailures.set(true)
        android.set(true)
        outputToConsole.set(true)
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
        }
    }
}

detekt {
    autoCorrect = false
    version = Versions.detekt
    input = files(
        "example/src/main/java",
        "core/src/main/java",
        "core-test/src/main/java",
        "dagger/src/main/java",
        "rx-usecases/src/main/java",
        "rx-usecases-test/src/main/java",
        "cr-usecases/src/main/java",
        "cr-usecases-test/src/main/java",
        "bindingadapters/src/main/java",
        "arkitekt-lint/src/main/java"
    )
//    filters = ".*/resources/.*,.*/build/.*"
    config = files("detekt.yml")
}
