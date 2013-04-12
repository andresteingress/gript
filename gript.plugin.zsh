function command_not_found_handler() {
    local function=$*[1]
    local args=$*[2,-1]
    local bootstrapScript="gript_bootstrap.groovy"

    if [[ -f "${ZSH}/plugins/gript/${bootstrapScript}" ]]; then
    	groovy "${ZSH}/plugins/gript/${bootstrapScript}" "${function} ${args}"
    elif [[ -f "~/.zsh/gript/${bootstrapScript}" ]]; then
        groovy "~/.zsh/gript/${bootstrapScript}" "${function} ${args}"
    elif [[ -f "~/gript/${bootstrapScript}" ]]; then
        groovy "~/gript/${bootstrapScript}" "${function} ${args}"
    else
        echo "Could not find the gript installation directory, please modify gript.plugin.zsh"	
    fi
}

# disable auto correct mode
unsetopt correct