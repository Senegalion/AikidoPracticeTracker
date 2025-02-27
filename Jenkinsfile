pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Senegalion/AikidoPracticeTracker.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
