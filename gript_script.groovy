/**
* ######### MAIN GRIPT INITIALIZATION SCRIPT ######### 
*
* @author Andre Steingress
*/
initGriptScript()

def aliasBinding = evaluate(new File(GRIPT_HOME, 'gript-aliases.groovy'), true)
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

def functionsBinding = evaluate(new File(GRIPT_HOME, 'gript-functions.groovy'))
def function = functionsBinding.variables[commandName]
if (function && function instanceof Closure) {
	function.call(args)
	return    
}
   
println "Could not find gript command '$commandName'"