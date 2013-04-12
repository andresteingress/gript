/*******************************************************************************
 * Copyright (c) 2013, Andre Steingress
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that
 * the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

/**
* Base class for the main gript script.
*
* @author Andre Steingress
*/
import groovy.transform.CompileStatic

@CompileStatic
abstract class GriptBaseClass extends Script {

    File GRIPT_HOME
    File WORKING_DIRECTORY

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
		GRIPT_HOME = getHomeDirectory()	
	}

	void initWorkingDirectory() {
		WORKING_DIRECTORY = new File(System.properties['wd'] as String)
	}

	File getHomeDirectory () {
		new File(getClass().protectionDomain.codeSource.location.path).parentFile
	}

	Closure<File> file = { String fileName ->
		new File(fileName)
	}

	Closure<File> parentFile = { File parent, String fileName ->
		new File(parent, fileName)
	}

	Closure<File> griptHome = { String fileName ->
		new File(GRIPT_HOME, fileName)
	}

	Closure<File> workingDirectory = { String fileName ->
		new File(WORKING_DIRECTORY, fileName)
	}

	Closure<String> cmdRetString = { String command ->
		def proc = command.execute()
		proc.waitFor()
		return proc.text
	}

	Closure<Integer> cmdConsume = { String command ->
		def proc = command.execute()
		proc.consumeProcessOutput(System.out, System.err)
		return proc.waitFor()
	}

	Binding evaluate(File griptFile, boolean withArgs = false)  {
		def isolatedBinding = new Binding([:])
		if (!griptFile) return isolatedBinding
    
		isolatedBinding.setVariable('GRIPT_HOME', GRIPT_HOME)
		isolatedBinding.setVariable('WORKING_DIRECTORY', WORKING_DIRECTORY)

        isolatedBinding.setVariable('file', file)
        isolatedBinding.setVariable('parentFile', parentFile)
		isolatedBinding.setVariable('griptHome', griptHome)
		isolatedBinding.setVariable('workingDirectory', workingDirectory)

    	isolatedBinding.setVariable('arguments', arguments)

    	isolatedBinding.setVariable('cmdRetString', cmdRetString)
    	isolatedBinding.setVariable('cmdConsume', cmdConsume)

		GroovyShell shell = new GroovyShell(getClass().classLoader, isolatedBinding)
    	shell.evaluate(griptFile)

    	return isolatedBinding
	}	

    def initGriptScript() {
		initArguments()
		initGriptHome()
		initWorkingDirectory()
	}
}
