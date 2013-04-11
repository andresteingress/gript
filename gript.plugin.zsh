function "#"() {
    local function=$*[1]
    local args=$*[2,-1]

    if [[ -f "${ZSH}/plugins/gript/gript_script.groovy" ]]; then
    	groovy "${ZSH}/plugins/gript/gript_script.groovy" "${function} ${args}"
    elif [[ -f "~/.zsh/gript/gript_script.groovy" ]]; then
        groovy "~/.zsh/gript/gript_script.groovy" "${function} ${args}"
    elif [[ -f "~/gript/gript_script.groovy" ]]; then
        groovy "~/gript/gript_script.groovy" "${function} ${args}"
    else
        echo "Could not find the gript installation directory, please modify gript.plugin.zsh"	
    fi
}
