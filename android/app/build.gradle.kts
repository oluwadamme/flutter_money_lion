plugins {
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
    // Apply React Native plugin
    // See: https://github.com/react-native-community/template/blob/main/template/android/app/build.gradle
	id("com.facebook.react")
    id("com.facebook.react.rootproject")
}
react {
    cliFile.set(file("../../react_native_module/node_modules/react-native/cli.js"))
    autolinkLibrariesWithApp()
}
android {
    namespace = "com.example.flutter_money_lion"
    compileSdk = 35
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    defaultConfig {
        // TODO: Specify your own unique Application ID (https://developer.android.com/studio/build/application-id.html).
        applicationId = "com.example.flutter_money_lion"
        // You can update the following values to match your application needs.
        // For more information, see: https://flutter.dev/to/review-gradle-config.
        minSdk = flutter.minSdkVersion
        targetSdk = 35
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    buildTypes {
        release {
            // TODO: Add your own signing config for the release build.
            // Signing with the debug keys for now, so `flutter run --release` works.
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    configurations.all {
        resolutionStrategy {
            force("com.facebook.react:react-native:0.79.1")  // Use the latest stable version
        }
    }
}

flutter {
    source = "../.."
}

// React Native setup
// See: https://github.com/react-native-community/template/blob/main/template/android/app/build.gradle
dependencies {
	implementation ("com.facebook.react:react-android")
	implementation ("com.facebook.react:hermes-android")
}
