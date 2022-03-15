/**
* Create a new zosmf profile
**/

def  call(Map config) {
    sh (returnStdout: true,
        script: 'zowe profiles create zosmf ${config.profileName} --host $HOST --port $PORT --user ${config.userId} --password ${config.password} --reject-unauthorized false')
}