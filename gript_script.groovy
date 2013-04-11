def home = new File(getClass().protectionDomain.codeSource.location.path).parent
arguments = args[0].split(' ').collect { it.trim() }

evaluate(new File(home, 'gript-aliases.groovy'))

def alias  = { command, args ->
    println "########## running command: $command"

    def proc = command.execute()
    proc.consumeProcessOutput(System.out, System.err)

    def returnValue = proc.waitFor()
    println "return value: $returnValue"
}

if (arguments.size() == 0) return

def commandName = arguments[0]
def command =  binding.variables.findResult(null) { it.key == commandName ? it.value : null }
if (!command) {
    println "Could not find gript command '$commandName'"
    return
} 
   

alias.call(command, args)
