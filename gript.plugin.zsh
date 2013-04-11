function "#"() {
    local function=$*[1]
    local args=$*[2,-1]

    if [[ -f "${ZSH}/plugins/gript/gript_script.groovy" ]]; then
    	groovy "${ZSH}/plugins/gript/gript_script.groovy" "${function} ${args}"
    else
        groovy "${ZSH}/gript/gript_script.groovy" "${function} ${args}"	
    fi
}
