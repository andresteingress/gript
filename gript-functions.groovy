unzipGroovy = { args ->
    def autoJumpDir = cmdRetString('autojump groovy-core').trim() ?: WORKING_DIRECTORY
    def groovyDirectory = new File(autoJumpDir as String)
    println "Groovy installation directory: $groovyDirectory"

    def dist = new File("${groovyDirectory.absolutePath}/target/distributions/")
    println "Dist directory: $dist"

    def zip  = new File(dist, "groovy-binary-2.2.0-SNAPSHOT.zip")
    if (!zip) return

    println "Unzipping: $zip ..."
    println "Target directory: $dist"
    zip.unzip(dist)

    proc = "cd $dist".execute()
    proc.waitFor()
}