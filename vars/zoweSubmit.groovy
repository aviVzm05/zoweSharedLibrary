/** 
* submit a job for execution
* https://www.jenkins.io/doc/book/pipeline/shared-libraries/  - Defining custom step
**/

def  call(Map config) {
    echo "${config.jobname}"
    echo "${config.successfulRC}"

    RETURN_CODE = sh (returnStdout: true,
                      script: "zowe jobs submit ds \'${config.jobname}\' --wfo --rft string --rff retcode").trim().split()
    
    echo "${RETURN_CODE[1]}"

    if (!(config.successfulRC.contains(RETURN_CODE[1]))) {
        error ("${config.jobname} failed with return code ${RETURN_CODE[1]}")
    }
}
