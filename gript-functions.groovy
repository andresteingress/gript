@Grapes( 
  @Grab(group='com.bloidonia', module='groovy-common-extensions', version='0.4.1') 
)

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

binding.setVariable('unzipGroovy', unzipGroovy)
