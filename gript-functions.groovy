unzipGroovy = { args ->
    def proc = "autojump groovy-core".execute()
    proc.waitFor()

    def groovyDirectory = new File(proc.text)
    println "Groovy installation directory: $groovyDirectory"

    def dist = new File("${groovyDirectory.absolutePath}/target/distributions/")
    println "Dist directory: $dist"

    def zip  = new File(dist, "groovy-binary-2.2.0-SNAPSHOT.zip")
    if (!zip) return

    File.unzip(dist)
}
