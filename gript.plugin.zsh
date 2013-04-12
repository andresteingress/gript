function command_not_found_handler() {
    local function=$*[1]
    local args=$*[2,-1]
    local bootstrapScript="gript_bootstrap.groovy"

    #probing for gript location
    if [[ -f "${ZSH}/plugins/gript/${bootstrapScript}" ]]; then
    	groovy -cp "${ZSH}/plugins/gript/" "-Duser.dir=${ZSH}/plugins/gript" -Dwd=$(pwd) "${ZSH}/plugins/gript/${bootstrapScript}" "${function} ${args}"
    elif [[ -f "~/.zsh/gript/${bootstrapScript}" ]]; then
        groovy -cp "~/.zsh/gript/" "-Duser.dir=~/.zsh/gript" -Dwd=$(pwd) "~/.zsh/gript/${bootstrapScript}" "${function} ${args}"
    elif [[ -f "~/gript/${bootstrapScript}" ]]; then
        groovy -cp "~/gript/" "-Duser.dir=~/gript" -Dwd=$(pwd) "~/gript/${bootstrapScript}" "${function} ${args}"
    else
        echo "Could not find the gript installation directory, please modify gript.plugin.zsh"	
    fi
}

# disable auto correct mode
unsetopt correct