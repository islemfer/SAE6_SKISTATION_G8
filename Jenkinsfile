pipeline {
environment {
        registry = "obr0613/projetdevops"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    agent any
    
    stages {

         stage('Checkout GIT') {
               steps{
                     echo 'Pulling...'
                            git branch: 'OussamaBENROMDHANE_5SAE6_G8',
                            url : 'https://github.com/islemfer/SAE6_SKISTATION_G8'
                    }
            }

        stage('Nettoyage et compilation Maven') {
            steps {
                // Cette étape va nettoyer et compiler le projet avec Maven
                sh 'mvn clean compile'
            }
        }


        stage('Mockito & JUnit test') {
            steps {
               script {
                sh 'mvn test'
              }
                
            }
        }


/*
                 stage('SonarQube analyse') {
                    steps {
                       script {
                     withSonarQubeEnv(installationName: 'sq1') {
                        sh 'mvn sonar:sonar'
                    }
                 }

                     }
                 }
*/

         stage('Nexus') {
           steps {
                script {
        //         sh 'mvn deploy'
      sh 'mvn deploy -DskipTests'

               }
                
             }
         }

                  stage('Docker Image') {
                     steps {
                         script {
                             dockerImage = docker.build registry + ":ImageSpringboot"
                         }
                     }
                 }
                stage('Deploy Image') {
                     steps {
                         script {
                             docker.withRegistry( '', registryCredential ) {
                                 dockerImage.push()
                             }
                         }
                     }
                 }

                 stage('Docker Compose') {
                     steps {
                             sh 'docker compose up -d'
                     }
                 }
  agent any
  triggers {
    cron('* * * * *')
  }
  stages {
    stage('grafana') {
      steps {
        echo 'Hello World - team-a - test'
        sleep 3
      }
    }
  }

        // stage('Quality Gate') {
        //     steps {
        //        timeout(time: 5, unit: 'MINUTES') {
        //            waitForQualityGate abortPipeline: true
        //        }
                
        //     }
        // }

    
    }
      }
