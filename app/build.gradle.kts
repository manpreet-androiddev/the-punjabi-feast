import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.google.hilt)
    alias(libs.plugins.firebase.crashlytics)
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.app.thepunjabifeast"
    compileSdk = 35
   /* signingConfigs {
        create("config") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }*/

    defaultConfig {
        applicationId = "com.app.thepunjabifeast"
        manifestPlaceholders["app_name"] = "The Punjabi Feast"
        manifestPlaceholders["api_url"] = "https://api.example.com"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // Creates a property for the FileProvider authority.
        val filesAuthorityValue = applicationId + ".files"
        // Creates a placeholder property to use in the manifest.
        manifestPlaceholders["filesAuthority"] = filesAuthorityValue
        // Adds a new field for the authority to the BuildConfig class.
        buildConfigField("String",
            "FILES_AUTHORITY",
            "\"${filesAuthorityValue}\"")
    }


  //  flavorDimensions += listOf("type")

    productFlavors {
        /*create("Free") {
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"
            dimension = "type"
            buildConfigField("String", "API_URL", "\"https://api.escuelajs.co/api/v1/\"")
            resValue("string", "base_url", "xxxxxxxxxx")
            manifestPlaceholders["app_name"] = "Punjabi Feast Free"
            manifestPlaceholders["environment"] = "development"
        }
        create("pro") {
            applicationIdSuffix = ".pro"
            versionNameSuffix = "-pro"
            dimension = "type"
            manifestPlaceholders["app_name"] = "Punjabi Feast"
            manifestPlaceholders["environment"] = "production"
            buildConfigField("String", "API_URL", "\"https://api.escuelajs.co/api/v1/\"")
        }*/

       /* create("minApi21") {
            dimension = "api"
            minSdk = 21
            versionCode = 10000  + (android.defaultConfig.versionCode ?: 0)
            versionNameSuffix = "-minApi21"
        }*/
    }

    buildTypes {
        /*debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            manifestPlaceholders["api_url"] = "https://dev.example.com"
            buildConfigField("String", "BUILD_TIME", "\"0\"")
            resValue("string", "build_time", "0")
        }*/

        release {
            isMinifyEnabled = true
            isJniDebuggable = false
            isShrinkResources = true
         //   signingConfig = signingConfigs.getByName("config")
          //  buildConfigField("String", "BUILD_TIME", "\"${minutesSinceEpoch}\"")
         //   resValue("string", "build_time", "${minutesSinceEpoch}")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

       /* create("staging") {
            initWith(getByName("debug"))
            manifestPlaceholders["hostname"] = "internal.example.com"
            applicationIdSuffix = ".debugStaging"
            manifestPlaceholders["google_map_key"] = "xxxxxxxxxx"
            manifestPlaceholders["app_label_name"] = "xxxxxxx"

        }*/
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

//Filter variants
/*androidComponents {
  beforeVariants { variantBuilder ->
      if(variantBuilder.productFlavors.contains(listOf("api" to "minApi21", "type" to "Free"))) {
          variantBuilder.enable = false
      }
  }
}*/

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.activity)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization.converter)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.coil)
    implementation(libs.serialization.json)
    //datastore
    implementation(libs.androidx.datastore)
    //room
    implementation(libs.room.runtime)
    implementation(libs.room)
    implementation(libs.androidx.graphics.shapes)
    implementation(libs.material)
    // implementation(libs.play.services.auth)
    ksp(libs.room.ksp)
    //work manager
    implementation(libs.androidx.work)
    //splash screen
    implementation(libs.androidx.splashscreen)

    //material icon
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.material.icons)

    // biometric
    implementation(libs.androidx.biometric)

    //lottie
    implementation(libs.lottie)

    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.credentials)
    implementation(libs.firebase.play.services.auth)
    implementation(libs.firebase.googleid)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    //hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //timber
    implementation(libs.jakewharton.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}