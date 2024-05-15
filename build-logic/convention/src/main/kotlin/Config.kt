import org.gradle.api.JavaVersion.VERSION_17

object Config {

    const val ApplicationId = "com.archipelago.jobbsyy"
    const val CompileSdk = 34
    const val MinSdk = 24
    const val TargetSdk = 34

    val VersionCode = System.getenv()["VERSION_CODE"] as Int? ?: 1000
    const val VersionName = "0.0.1"

    val JavaVersion = VERSION_17
}
