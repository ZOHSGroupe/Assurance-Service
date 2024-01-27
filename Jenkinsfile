pipeline {
    agent any

    environment {
        // Customize these variables based on your project
        DOCKER_HUB_REPO = 'https://github.com/ZOHSGroupe/Assurance-Service'
        SPRING_PROFILES_ACTIVE = 'production'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Check out the source code from your GitHub repository
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the Spring Boot application using Maven
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image and tag it with the version
                    sh "docker build -t ${DOCKER_HUB_REPO}:${env.BUILD_NUMBER} ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    // Log in to Docker Hub
                    withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'DOCKER_HUB_PASSWORD'), string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'DOCKER_HUB_USERNAME')]) {
                        sh "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"
                    }
                    // Push the Docker image to Docker Hub
                    sh "docker push ${DOCKER_HUB_REPO}:${env.BUILD_NUMBER}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {

                }
            }
        }
    }

    post {
        always {
            // Clean up: Remove the Docker image locally
            cleanup {
                sh "docker rmi ${DOCKER_HUB_REPO}:${env.BUILD_NUMBER}"
            }
        }
    }
}
