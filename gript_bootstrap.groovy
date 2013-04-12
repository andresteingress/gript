/**
* ######### GRIPT BOOTSTRAP ######### 
*
* @author Andre Steingress
*/
@Grapes(Grab(group='com.bloidonia', module='groovy-common-extensions', version='0.4.1'))
import org.codehaus.groovy.control.CompilerConfiguration

def config = new CompilerConfiguration() 
config.scriptBaseClass = 'GriptBaseClass'

def shell  = new GroovyShell(this.binding, config)
def mainScript = new File('gript_script.groovy')
if (!mainScript) throw new Exception('gript_script.groovy not found!')

shell.evaluate(mainScript)