pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages{
        stage('Build MuzixAPI'){
            steps{
                checkout scmGit(branches: [[name: '*/docker']], extensions: [], userRemoteConfigs: [[credentialsId: 'b0c2c3b1-2ac3-4ed1-9057-ff49ff3bb16e', url: 'https://gitlab.stackroute.in/sivasai.kuruva/muzixapi']])
                sh 'mvn clean install'
                // bat 'mvn clean install'
            }
        }
        stage('Deploy MuzixAPI Docker Compose'){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credits', passwordVariable: 'DOCKERHUB_PWD', usernameVariable: 'DOCKERHUB_USER')]) {
                        // bat 'docker login -u sivarayal -p s9014394249'
                        // // bat 'docker-compose down'
                        // bat 'docker images prune -a'
                        // bat 'docker-compose -f docker-compose.yml build'

                        // bat 'docker push sivarayal/muzix:muzix-eureka-v1'
                        // bat 'docker push sivarayal/muzix:muzix-gateway-v1'
                        // bat 'docker push sivarayal/muzix:muzix-userprofile-v1'
                        // bat 'docker push sivarayal/muzix:muzix-auth-service-v1'
                        // bat 'docker push sivarayal/muzix:muzix-wishlist-v1'
                        // bat 'docker push sivarayal/muzix:muzix-lasfm-service-v1'

                         sh 'docker login -u sivarayal -p s9014394249'
                        // sh 'docker-compose down'
                        sh 'docker images prune -a'
                        sh 'docker-compose -f docker-compose.yml build'
                        sh 'docker push sivarayal/muzix:muzix-eureka-v1'
                        sh 'docker push sivarayal/muzix:muzix-gateway-v1'
                        sh 'docker push sivarayal/muzix:muzix-userprofile-v1'
                        sh 'docker push sivarayal/muzix:muzix-auth-service-v1'
                        sh 'docker push sivarayal/muzix:muzix-wishlist-v1'
                        sh 'docker push sivarayal/muzix:muzix-lasfm-service-v1'
                    }
                    // sh 'docker-compose -f docker-compose.yml up --scale muzix-userprofile-v1=2'
                    // sh 'docker-compose -f docker-compose.yml up'
                }
            }
        }
    }
}