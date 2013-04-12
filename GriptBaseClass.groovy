/**
* Base class for the main gript script.
*
* @author Andre Steingress
*/
import static groovy.transform.TypeCheckingMode.*

@groovy.transform.CompileStatic
abstract class GriptBaseClass extends Script {

    File GRIPT_HOME

    String commandName
	List arguments = []

	void initArguments() {
		List<String> args = binding.variables.args as List<String>
		arguments = args[0].split(' ').collect { String s -> s.trim() }
		if (arguments.size() == 0) 
			throw new Exception('No command specified')

		commandName = arguments[0]
		arguments = arguments.size() > 1 ? arguments[1..<arguments.size()] : []
	}

	void initGriptHome() {
		GRIPT_HOME = new File(getClass().protectionDomain.codeSource.location.path).parentFile
	}

	Binding evaluate(File griptFile, boolean withArgs = false)  {
		def isolatedBinding = new Binding([:])
		if (!griptFile) return isolatedBinding
    
    	if (withArgs) isolatedBinding.setVariable('arguments', arguments)

		GroovyShell shell = new GroovyShell(getClass().classLoader, isolatedBinding)
    	shell.evaluate(griptFile)

    	return isolatedBinding
	}	

    def initGriptScript() {
		initArguments()
		initGriptHome()
	}
}
