gript
=====

A ZSH Wrapper for Groovy files providing aliases and functions to be used at the command-line. Attempts to make 
working with ZSH groovier.

CURRENTLY UNDER HEAVY DEVELOPMENT!

Installation
----

Clone this repository into

* .oh-my-zshell/plugins or
* .zsh/ or
* ~/

If you do not use oh-my-zhell you need to make sure to source *gript.plugin.zsh* in your startup ZSH script.

Alias Configuration
----

Once installed, you can modify *gript-aliases.groovy* to add/remove aliases. This file is a Groovy file with an *arguments*
variable to be used for accessing the commands arguments (starting at 0 for the first argument).

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

You can modify *gript-functions.groovy* to add/remove functions available on the command-line.

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
➜  ~  loadAndUnzipGroovyDist
########## running command: loadAndUnzipGroovyDist
...
```

