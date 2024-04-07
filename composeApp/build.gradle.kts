import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kmpNativeCoroutines)
}

buildkonfig {
    packageName = "com.zaki.kotlibs"
    defaultConfigs {
        val prop = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }
        val reposUrl: String = prop.getProperty("REPOS_URL")
        require(reposUrl.isNotEmpty()) {
            "Give Path For Repo JSON and place it in local.properties as `REPOS_URL`"
        }
        buildConfigField(STRING, "REPOS_URL", reposUrl)
    }
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.bundles.ktor.common)
            implementation(libs.koin.core)
            implementation(libs.voyager)
            implementation(libs.kmmViewModel)
            implementation(libs.koin.compose)
        }
    }
}

compose {
    kotlinCompilerPlugin.set(libs.versions.compose.compiler)
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=${libs.versions.kotlin}")
}

compose.experimental {
    web.application {}
}
