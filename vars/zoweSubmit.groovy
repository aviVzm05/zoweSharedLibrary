/** 
* submit a job for execution
* https://www.jenkins.io/doc/book/pipeline/shared-libraries/  - Defining custom step
**/

def  call(Map config) {
    RETURN_CODE = sh (returnStdout: true,
                      script: "zowe jobs submit ds \'${config.jobname}\' --wfo --rft string --rff retcode").trim().split()
    
    if (!(RETURN_CODE[1] in config.successfulRC)) {
        error ("${config.jobname} failed with return code ${RETURN_CODE[1]}")
    }
}
