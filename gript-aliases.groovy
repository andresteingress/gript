gri="gradle install"
grci="gradle clean install"
grt="gradle test"
grt_sub="gradle :${arguments[0]}:test"
grc="gradle clean"
grts_sub="gradle -Dsingle.test=${arguments[0]} :${arguments[1]}:test" 
