pipeline {
    agent any
    tools {
        gradle 'gradleJnks'
        jdk 'java17'
    }
//     environment {
//         PROJECT_ID = 'apt-rope-381319'
//         CLUSTER_NAME = 'cluster-1'
//         LOCATION = 'us-central1-c'
//         CREDENTIALS_ID = 'kubeGkeCreds'
//     }
    stages {
        stage('ECHO') {
            steps {
                echo "Build number is ${currentBuild.number} was executing ${currentBuild.durationString}! Also ${env.BUILD_ID}"
                echo 'Hello World'
            }
        }

//        stage('Build Docker') {
//            agent any
//            steps {
//                sh 'whoami'
//                sh 'docker -v'
//            }
//        }
        stage('Build') {
            steps {
                sh 'java -version'
                sh 'chmod +x gradlew'
                sh './gradlew build --no-daemon'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'whoami'
                sh 'docker -v'
                script {
                    myimage = docker.build("jackredd/jwt:${env.BUILD_ID}")
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        myapp.push("latest")
                        myapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }
    }
}
