// Inspiration from: https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/

object Vers {
    const val compile_sdk_version = 31
    const val min_sdk_version = 23
    const val build_tools_version = "30.0.2"
//    const val ndk_version = "21.3.6528147"
    const val gradle_build_tools = "4.0.2"
    const val kotlin_version = "1.6.0" // "1.4.10"
    const val fabric_tools_gradle = "1.31.2"
    const val app_compat = "1.4.0"
    const val core_ktx = "1.3.2"
    const val constraint_layout = "2.1.2" // "1.1.3"
    const val junit = "4.12"
    const val androidx_junit = "1.1.3"
    const val espresso = "3.4.0"
    const val google_services = "4.3.3"
    const val play_services_auth = "19.2.0"
    const val navigation = "2.1.0"
    const val lifecycle = "2.2.0"
    const val arch_version = "2.1.0"
    const val recycler_view = "1.1.0"
    const val card_view = "1.0.0"
    const val androidx_legacy = "1.0.0"
    const val live_event = "1.2.0"
//    const val plist_parser = "1.23"
    const val koinVersion = "2.0.1"
    const val kotlinCoroutines = "1.3.3"
    const val circular_image_view = "3.1.0"
//    const val aws_core = "2.16.8"
    const val view_pager = "1.0.0"
//    const val exo_player = "2.11.7"
//    const val blur_view = "1.2.1"
    const val fragment_ktx = "1.2.5"
    const val grid_layout = "1.0.0"

    const val material = "1.4.0"
    const val rx_version = "2.2.12"
    const val rx_android_version = "2.1.1"
    const val rx_adapter_version = "1.0.0"
    const val retrofitAdaptersVersion = "2.3.0"
    const val retrofitVersion = "2.9.0"
    const val okhttpVersion = "3.10.0"
    const val loggingInterceptorVersion = "5.0.0-alpha.2" //""3.8.1"
    const val file_downloader = "0.6.0"
    const val coroutines_adapter = "0.9.2"

    const val firebase_analytics = "17.4.4"
    const val firebase_crashlytics = "17.1.1"
    const val firebase_crashlytics_plugin = "2.2.0"
    const val crashlytics_version = "2.10.1"

    const val roomVersion = "2.2.1"
    const val multiDexVersion = "2.0.1" // "1.0.3"

    const val hyperlog = "0.0.9"
    const val glideVersion = "4.11.0"

    const val sdpVersion = "1.0.6"
    const val sspVersion = "1.0.6"

    const val permission = "0.4.0"
    const val multiSlider = "1.3"
    const val lottieVersion = "3.4.0"
    const val device_name = "2.0.0"

//    const val facebook = "7.1.0"
//    const val billingVersion = "3.0.0"
    const val gson = "2.8.6"

    const val okhttpUrlConnectionVersion = "3.0.0-RC1"

//    const val chromeCustomTabsVersion = "1.2.0"

    const val androidx_navigation_fragment = "2.4.0-beta02"
    const val androidx_navigation_ui = "2.4.0-beta02"
    const val arch_lifecycle_extension = "1.1.1"

    const val kotlinVersion = "1.5.31"
    const val mockWebserver = "4.7.2"

    const val hiltVersion = "2.38.1"

}

object Deps {

    const val dagger_hilt = "com.google.dagger:hilt-android:${Vers.hiltVersion}"
    const val dagger_hilt_compiler = "com.google.dagger:hilt-compiler:${Vers.hiltVersion}"


    const val gradle_build_tools = "com.android.tools.build:gradle:${Vers.gradle_build_tools}"
    const val google_services = "com.google.gms:google-services:${Vers.google_services}"
    const val play_services_auth = "com.google.android.gms:play-services-auth:${Vers.play_services_auth}"
    const val fabric_tools_gradle = "io.fabric.tools:gradle:${Vers.fabric_tools_gradle}"
    const val kotlin_jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Vers.kotlin_version}"
    const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin_version}"
    const val app_compat = "androidx.appcompat:appcompat:${Vers.app_compat}"
    const val core_ktx = "androidx.core:core-ktx:${Vers.core_ktx}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Vers.constraint_layout}"
    const val junit = "junit:junit:${Vers.junit}"
    const val androidx_junit = "androidx.test.ext:junit:${Vers.androidx_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Vers.espresso}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Vers.navigation}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Vers.fragment_ktx}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Vers.navigation}"
    const val recycler_view = "androidx.recyclerview:recyclerview:${Vers.recycler_view}"
    const val card_view = "androidx.cardview:cardview:${Vers.card_view}"
    const val androidx_legacy = "androidx.legacy:legacy-support-v4:${Vers.androidx_legacy}"
    const val live_event = "com.github.hadilq.liveevent:liveevent:${Vers.live_event}"
    const val koin_core = "org.koin:koin-core:${Vers.koinVersion}"
    const val koin_android = "org.koin:koin-android:${Vers.koinVersion}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Vers.koinVersion}"
    const val kotlin_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.kotlinCoroutines}"
    const val view_pager = "androidx.viewpager2:viewpager2:${Vers.view_pager}"
    const val android_material = "com.google.android.material:material:${Vers.material}"
    const val rx_java = "io.reactivex.rxjava2:rxjava:${Vers.rx_version}"
    const val rx_java_android = "io.reactivex.rxjava2:rxandroid:${Vers.rx_android_version}"
    const val rx_java_adapter =
        "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Vers.rx_adapter_version}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Vers.okhttpVersion}"
    const val logginInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Vers.loggingInterceptorVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Vers.retrofitVersion}"
    const val retrofit_gson_convertor =
        "com.squareup.retrofit2:converter-gson:${Vers.retrofitAdaptersVersion}"
    const val retrofit_rxjava_adapter =
        "com.squareup.retrofit2:adapter-rxjava:${Vers.retrofitAdaptersVersion}"
    const val file_downloader = "com.mindorks.android:prdownloader:${Vers.file_downloader}"
    const val firebase_analytics =
        "com.google.firebase:firebase-analytics:${Vers.firebase_analytics}"
    const val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics:${Vers.firebase_crashlytics}"
    const val crashlaytics = "com.crashlytics.sdk.android:crashlytics:${Vers.crashlytics_version}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Vers.lifecycle}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Vers.lifecycle}"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Vers.lifecycle}"
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Vers.lifecycle}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Vers.lifecycle}"
    const val lifecycle_common = "androidx.lifecycle:lifecycle-common-java8:${Vers.lifecycle}"
    const val lifecycle_reactivestreams =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Vers.lifecycle}"
    const val lifecycle_testing = "androidx.arch.core:core-testing:${Vers.arch_version}"
    const val room_runtime = "androidx.room:room-runtime:${Vers.roomVersion}"
    const val room_ktx = "androidx.room:room-ktx:${Vers.roomVersion}"
    const val multiDex = "com.android.support:multidex:${Vers.multiDexVersion}"
    const val hyperlog = "com.hypertrack:hyperlog:${Vers.hyperlog}"
    const val glide = "com.github.bumptech.glide:glide:${Vers.glideVersion}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Vers.glideVersion}"
    const val sdp = "com.intuit.sdp:sdp-android:${Vers.sdpVersion}"
    const val ssp = "com.intuit.ssp:ssp-android:${Vers.sspVersion}"
    const val quick_permission =
        "com.github.quickpermissions:quickpermissions-kotlin:${Vers.permission}"
    const val slider = "io.apptik.widget:multislider:${Vers.multiSlider}"
    const val lottie = "com.airbnb.android:lottie:${Vers.lottieVersion}"
    const val device_name = "com.jaredrummler:android-device-names:${Vers.device_name}"
    const val retrofit_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Vers.coroutines_adapter}"
    const val retrofit_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Vers.okhttpVersion}"
    const val gson = "com.google.code.gson:gson:${Vers.gson}"
    const val okhttp_urlconnection =
        "com.squareup.okhttp3:okhttp-urlconnection:${Vers.okhttpUrlConnectionVersion}"

    const val firebase_crashlytics_plugin =
        "com.google.firebase:firebase-crashlytics-gradle:${Vers.firebase_crashlytics_plugin}"

    const val grid_layout = "androidx.gridlayout:gridlayout:${Vers.grid_layout}"

    const val androidx_navigation_fragment = "androidx.navigation:navigation-fragment:${Vers.androidx_navigation_fragment}"
    const val androidx_navigation_ui = "androidx.navigation:navigation-ui:${Vers.androidx_navigation_ui}"

    const val arch_lifecycle_extension = "android.arch.lifecycle:extensions:${Vers.arch_lifecycle_extension}"

    const val kotlin_version = "android.arch.lifecycle:extensions:${Vers.kotlinVersion}"

}