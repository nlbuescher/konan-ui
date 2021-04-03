import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
	kotlin("multiplatform")
}

val host: OperatingSystem = OperatingSystem.current()

kotlin {
	when {
		host.isLinux -> linuxX64("native")
		host.isMacOsX -> macosX64("native")
		host.isWindows -> mingwX64("native")
	}

	targets.withType<KotlinNativeTarget> {
		binaries.executable {
			entryPoint = "konanui.samples.main"
		}
	}

	sourceSets {
		all {
			languageSettings.useExperimentalAnnotation("kotlin.ExperimentalUnsignedTypes")
		}

		commonMain {
			dependencies {
				implementation(project(":core"))
			}
		}
	}
}
