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
        	emailext (
            	subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            	body: """
            	<h2 style="color:green;">Build Successful</h2>

            	<b>Job Name:</b> ${env.JOB_NAME} <br>
            	<b>Build Number:</b> ${env.BUILD_NUMBER} <br>
            	<b>Build URL:</b> ${env.BUILD_URL} <br>
            	<b>Git Branch:</b> ${env.GIT_BRANCH} <br>

            	<p>The build completed successfully.</p>
            	""",
            	attachLog: true,
            	to: "bavariadevanshi@gmail.com"
        	)
    		}

    	failure {
        	emailext (
            	subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
            	body: """
            	<h2 style="color:red;">Build Failed</h2>

            	<b>Job Name:</b> ${env.JOB_NAME} <br>
            	<b>Build Number:</b> ${env.BUILD_NUMBER} <br>
            	<b>Build URL:</b> ${env.BUILD_URL} <br>

            	<p>Please check the console output.</p>
            	""",
            	attachLog: true,
            	to: "bavariadevanshi@gmail.com"
        	)
    		}
	}

}
