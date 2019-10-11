apply(plugin = "kotlin-allopen")

allOpen {
    annotation("com.capraro.business.UseCase")
}

dependencies {
    api(Libraries.kalidation)
}