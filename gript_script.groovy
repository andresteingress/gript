arguments = args[0].split(' ').collect { it.trim() }
if (arguments.size() == 0) return

def commandName = arguments[0]
arguments = arguments.size() > 1 ? arguments[1..<arguments.size()] : []

def home = new File(getClass().protectionDomain.codeSource.location.path).parent

/**
* ### COMMON
*/
def evaluate = { File file, boolean withArgs = false ->
	def isolatedBinding = new Binding([:])
	if (!file) return isolatedBinding
    
    if (withArgs) isolatedBinding.setVariable('arguments', arguments)

	GroovyShell shell = new GroovyShell(getClass().classLoader, isolatedBinding)
    shell.evaluate(file)

    return isolatedBinding
}

/**
* ### ALIASES
*/
def aliasBinding = evaluate(new File(home, 'gript-aliases.groovy'), true)
def executeAlias  = { command, args ->
    println "########## running command: $command"

    def proc = command.execute()
    proc.consumeProcessOutput(System.out, System.err)

    def returnValue = proc.waitFor()
    println "return value: $returnValue"
}

def a = aliasBinding.variables[commandName]
if (a && a instanceof CharSequence) {
	executeAlias.call(a, args)
	return    
} 

/**
* ### FUNCTIONS
*/
def functionsBinding = evaluate(new File(home, 'gript-functions.groovy'))

println functionsBinding.variables

def function = functionsBinding.variables[commandName]
if (function && function instanceof Closure) {
	function.call(args)
	return    
}
   
println "Could not find gript command '$commandName'"