gript
=====

ZSH Wrapper for Executing Groovy Functions and Aliases

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

Execution
----

Once an alias is configured, it can be executed via the given alias on the command-line:

```
➜  ~  # gri
########## running command: gradle install
...
```

