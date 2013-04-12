gript
=====

A ZSH Wrapper for adding custom aliases and functions written in the Groovy programming language. The aliases and functions can be
directly called via the command-line.

**CURRENTLY UNDER HEAVY DEVELOPMENT!**

Installation
----

Clone this repository into

* .oh-my-zshell/plugins or
* .zsh/ or
* ~/

If you do not use oh-my-zhell you need to make sure to source *gript.plugin.zsh* in your startup ZSH script.

Gript Scripts
----

Currently, Gript comes with two predefines places to put aliases and functions: *gript-aliases.groovy* and *gript-functions.groovy*.
Both files are scripts files written in the Groovy programming language.

Both Gript scripts are executed with the following pre-defined variables and functions:

* *GRIPT_HOME* refers to the Gript installation directory
* *WORKING_DIRECTORY* refers to the current working directory from which the alias or function was called
* *griptHome(String fileName)* used to lookup files in the installation directory
* *workingDirecory(String fileName)* used to lookup files in the working directory
* and the complete set of GDK/JDK methods
* and additional extension methods from <a href="http://github.com/timyates/groovy-common-extensions">Groovy common extensions</a>

Alias Configuration
----

Once installed, you can modify *gript-aliases.groovy* to add/remove aliases. This file is a Groovy file with an additional *arguments*
variable to be used for accessing the command arguments (starting at 0 for the first argument).

```groovy
gri="gradle install"
grci="gradle clean install"
grt="gradle test"
grt_sub="gradle :${arguments[0]}:test"
grc="gradle clean"
grts_sub="gradle -Dsingle.test=${arguments[0]} :${arguments[1]}:test" 
```

Notice, this is a regular Groovy file, we could use more complicated Groovy code if necessary.

Function Configuration
----

You can modify *gript-functions.groovy* to add/remove functions to be available on the command-line.

```groovy
unzipGroovy = { args ->
    def proc = "autojump groovy-core".execute()
    proc.waitFor()

    def groovyDirectory = new File(proc.text)
    println "Groovy installation directory: $groovyDirectory"

    def dist = new File("${groovyDirectory.absolutePath}/target/distributions/")
    println "Dist directory: $dist"

    def zip  = new File(dist, "groovy-binary-2.2.0-SNAPSHOT.zip")
    if (!zip) return

    zip.unzip(dist)
}
```

Please note, the example above shows Gript coming with <a href="http://github.com/timyates/groovy-common-extensions">Groovy common extensions</a> enabled by default.

Execution
----

Once an alias is configured, it can be executed via the given alias on the command-line:

```
➜  ~  gri
########## running command: gradle install
...
```

```
➜  ~  grt_sub library-core
########## running command: gradle :library-core:test
...
```

If functions are configured, you can call them by funcion name (including optional arguments) on the command-line:

```
➜  ~  unzipGroovy
########## running command: unzipGroovy
...
```

Gript is distributed under a BSD license. If you want to use Gript in any form you have to agree to this license:

> Copyright (c) 2013, Andre Steingress
> 
> All rights reserved.
> 
> Redistribution and use in source and binary forms, with or without modification, are permitted provided that
> the following conditions are met:
> 
> Redistributions of source code must retain the above copyright notice, this list of conditions
> and the following disclaimer.
> Redistributions in binary form must reproduce the above copyright notice, this list of conditions
> and the following disclaimer in the documentation and/or other materials provided with the distribution.
>
> THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
> WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
> PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
> ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
> TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
> HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
> NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
> POSSIBILITY OF SUCH DAMAGE.
