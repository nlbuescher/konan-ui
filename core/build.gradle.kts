import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
	kotlin("multiplatform")
}

val host: OperatingSystem = OperatingSystem.current()

kotlin {
	when {
		host.isLinux -> linuxX64("linux")
		host.isMacOsX -> macosX64("macos")
		host.isWindows -> mingwX64("mingw")
	}

	targets.withType<KotlinNativeTarget> {
		compilations["main"].run {
			cinterops.create("gtk4")
		}
	}

	sourceSets {
		all {
			languageSettings.useExperimentalAnnotation("kotlin.ExperimentalUnsignedTypes")
		}

		targets.withType<KotlinNativeTarget> {
			named("${name}Main") {
				kotlin.srcDir("src/nativeMain/kotlin")
				resources.srcDir("src/nativeMain/resources")
			}
			named("${name}Test") {
				kotlin.srcDir("src/nativeTest/kotlin")
				resources.srcDir("src/nativeTest/resources")
			}
		}
	}
}
