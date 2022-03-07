@Library('zoweSharedLibrary') _
pipeline {
    agent any
    
    environment {
          successfulRC = "['0000','0004']"
        }
        
    stages {
        stage('create zowe profile') {
            steps{
                script {
                    withCredentials([usernamePassword(credentialsId: 'zoweMainframePassword', passwordVariable: 'password', usernameVariable: 'userid')]) {
                        try {
                            sh 'zowe profiles delete zosmf Avinash'
                            sh 'zowe profiles create zosmf Avinash --host $HOST --port $PORT --user $userid --password $password --reject-unauthorized false'   
                        } catch (Exception e) {
                            log.info 'profile not present...creating now'
                            sh 'zowe profiles create zosmf Avinash --host $HOST --port $PORT --user $userid --password $password --reject-unauthorized false'
                        }
                    }
                }
            }
        }
        stage ('Submit test job') {
            steps {
                zoweSubmit jobname: 'Z40728.JCL(TESTJOB)',successfulRC: successfulRC

            }
        }
    }
}