object Version {
    const val kotlinVersion = "1.2.61"
    const val androidGradleVersion = "3.1.4"
    const val constraintLayoutVersion = "1.1.2"
    const val multiDescVersion = "1.0.3"

    // Compile dependencies
    const val supportVersion = "28.0.0-rc01"
    const val daggerVersion = "2.14.1"


    // Unit tests
    const val mockitoVersion = "2.13.0"
    const val googleServices = "4.0.0"

    //Rx Versions
    const val rxJava2 = "2.1.9"
    const val rxAndroid = "2.0.1"
    const val rxKotlin = "2.1.0"

    //Square Versions
    const val picassoVersion = "2.5.2"
    const val moshiConverterVersion = "2.3.0"
    const val rxAdapterVersion = "1.0.0"
    const val moshiVersion = "1.5.0"
    const val retrofitVersion = "2.3.0"
    const val okhttpVersion = "3.11.0"
    const val timberVersion = "4.6.0"

    const val room_version = "1.1.1-rc1"

    const val glideVersion = "4.7.1"
    const val butterKnifeVersion = "8.8.1"
}


object Dependencies {

    val androidGradle = "com.android.tools.build:gradle:${Version.androidGradleVersion}"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"

    val buildToolsVersion = "27.0.3"
    val minSdkVersion = 16
    val targetSdkVersion = 28
    val compileSdkVersion = 28
    val applicationId = "com.esoxjem.movieguide"
    val versionCode = 1
    val versionName = "1.0"

}

object Libs {
    val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlinVersion}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Version.kotlinVersion}"
    val appcompat = "com.android.support:appcompat-v7:${Version.supportVersion}"
    val support = "com.android.support:support-v4:${Version.supportVersion}"
    val constraint_layout = "com.android.support.constraint:constraint-layout:${Version.constraintLayoutVersion}"
    val design = "com.android.support:design:${Version.supportVersion}"
    val multidex = "com.android.support:multidex:${Version.multiDescVersion}"
    val supportAnnotation = "com.android.support:support-annotations:${Version.supportVersion}"
    val palattte = "com.android.support:palette-v7:${Version.supportVersion}"
}

object Square {
    val picasso = "com.squareup.picasso:picasso:${Version.picassoVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    val moshi_retrofit = "com.squareup.retrofit2:converter-moshi:${Version.moshiConverterVersion}"
    val rxadapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Version.rxAdapterVersion}"
    val moshi = "com.squareup.moshi:moshi:${Version.moshiVersion}"
    val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshiVersion}"
    val retrofitGsonConvertor = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttpVersion}"
    val okhttp_intercepter = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpVersion}"
    val timber = "com.jakewharton.timber:timber:${Version.timberVersion}"
    val butterKnife = "com.jakewharton:butterknife:${Version.butterKnifeVersion}"
    val butterKnifeCompiler = "com.jakewharton:butterknife-compiler:${Version.butterKnifeVersion}"
}

object Glide {
    val glide = "com.github.bumptech.glide:glide:${Version.glideVersion}"
    val glideOkhttp3integration = "com.github.bumptech.glide:okhttp3-integration:${Version.glideVersion}"
}

object Reactive {
    val rxJava2 = "io.reactivex.rxjava2:rxjava:${Version.rxJava2}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Version.rxKotlin}"
}

object Di {
    val dagger = "com.google.dagger:dagger:${Version.daggerVersion}"
    val dagger_android = "com.google.dagger:dagger-android:${Version.daggerVersion}"
    val dagger_android_support = "com.google.dagger:dagger-android-support:${Version.daggerVersion}"
    val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Version.daggerVersion}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Version.daggerVersion}"
    val databinding_complier = "com.android.databinding:compiler:3.1.1"
}


object Aac {
    val room = "android.arch.persistence.room:runtime:${Version.room_version}"
    val roomCompiler = "android.arch.persistence.room:compiler:${Version.room_version}"
    val roomRx = "android.arch.persistence.room:rxjava2:${Version.room_version}"
}

object TestLibs {
    val junit = "junit:junit:4.12"
    val runner = "com.android.support.test:runner:1.0.1"
    val mockito = "org.mockito:mockito-core:${Version.mockitoVersion}"
    val mockito_kotlin = "com.nhaarman:mockito-kotlin:1.5.0"
    val dexmaker = "com.google.dexmaker:dexmaker:1.2"
    val dexmaker_mockito = "com.google.dexmaker:dexmaker-mockito:1.2"
    val espresso = "com.android.support.test.espresso:espresso-core:3.0.1"
}