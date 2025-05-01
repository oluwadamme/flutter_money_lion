import com.facebook.react.ReactExtension

allprojects {
    repositories {
         maven {
            url = uri("${project.rootDir}/../react_native_module/node_modules/react-native/android")
        }
        google()
        mavenCentral()
    }
}
val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}

subprojects {
   project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

// React Native setup
// See: https://github.com/react-native-community/template/blob/main/template/android/build.gradle
buildscript {
    val buildToolsVersion by extra("34.0.0")
    val minSdkVersion by extra(23)
    val compileSdkVersion by extra(35)
    val targetSdkVersion by extra(35)
    val ndkVersion by extra("26.1.10909125")
    val kotlinVersion by extra("1.9.22")
	repositories {
		google()
		mavenCentral()
	}
	dependencies {
        classpath("com.android.tools.build:gradle") // or latest
        classpath("com.facebook.react:react-native-gradle-plugin")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
	}
}
