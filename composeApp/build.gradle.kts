import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

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
        buildConfigField(STRING, "REPOS_URL", "https://iammohdzaki.github.io/kotlibs")
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
            implementation(libs.kamel)
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
