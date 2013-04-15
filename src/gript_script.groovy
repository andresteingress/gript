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
* ######### MAIN GRIPT INITIALIZATION SCRIPT ######### 
*
* @author Andre Steingress
*/
initGriptScript()

final String ALIASES = 'gript-aliases.groovy'
final String FUNCTIONS = 'gript-functions.groovy'

def aliasBinding = evaluate(griptHome(ALIASES))
def executeAlias  = { command, args ->
    println "########## running command: $command"

    def returnValue = cmdConsume(command)
    println "return value: $returnValue"
}

def a = aliasBinding.variables[commandName]
if (a && a instanceof CharSequence) {
	executeAlias.call(a, args)
	return    
} 

def functionsBinding = evaluate(griptHome(FUNCTIONS))
def function = functionsBinding.variables[commandName]
if (function && function instanceof Closure) {
	function.call(args)
	return    
}
   
println "Could not find gript command '$commandName'"