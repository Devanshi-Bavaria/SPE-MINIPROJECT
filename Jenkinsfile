pipeline {
    agent any

    environment {
        IMAGE_NAME = "gasolinaaa/calculator"
        IMAGE_TAG = "latest"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'DockerHubCred',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    docker push $IMAGE_NAME:$IMAGE_TAG
                    '''
                }
            }
        }
	stage('Deploy') {
            steps {
                sh 'ansible-playbook ansible/deploy.yml -i ansible/inventory'
            }
        }
}
	post {
		success {
		mail(
            		subject: "Jenkins Build Success",
            		body: "Build completed successfully.",
            		to: "devubavaria.8502@gmail.com"
        		)
    		}

    		failure {
        	mail(
            		subject: "Jenkins Build Failed",
            		body: "Build failed. Please check Jenkins.",
            		to: "devubavaria.8502@gmail.com"
        	)
    		}
    	}
}
