pipeline {
    agent any
    tools {
        gradle 'gradleJnks'
        jdk 'java17'
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('kras-dockerhub')
        IMAGE_TAG = "jackredd/jwt:${env.BUILD_ID}"
    }
    stages {
        stage('ECHO') {
            steps {
                echo "Build number is ${currentBuild.number} was executing ${currentBuild.durationString}! Also ${env.BUILD_ID}"
                echo 'Hello World'
            }
        }
        stage('Build with Gradle') {
                    steps {
                        sh 'java -version'
                        sh 'chmod +x gradlew'
                        sh './gradlew build --no-daemon'
                    }
        }
        stage('Build Image') {
            steps {
                sh '''
                chmod +x ./gradlew
                docker build -t "$IMAGE_TAG" .
                '''
            }
        }
        stage('Push Image') {
            steps {
                  sh '''
                     echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                     docker push "$IMAGE_TAG"
                  '''
             }
        }
        stage('Deploy Kubernetes') {
            steps {
			    sh 'ls -ltr'
			    sh 'pwd'
			    sh "sed -i 's/tagversion/${env.BUILD_ID}/g' kube-deployment.yaml"
                script {
                  kubernetesDeploy(configs: "kube-deployment.yaml", kubeconfigId: "kubernetes")
                }
              }
            }
    }
    post {
        always {
             sh 'docker logout'
        }
    }

}
