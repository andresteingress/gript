gript() {
    local function=$*[1]
    local args=$*[2,-1]

    groovy '/Users/andre/.oh-my-zsh/custom/gript_script.groovy' "${function} ${args}"
}
