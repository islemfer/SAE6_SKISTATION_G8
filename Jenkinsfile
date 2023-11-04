pipeline {
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
                // stage('SonarQube analyse') {
                //     steps {
                //        script {
                //     withSonarQubeEnv(installationName: 'sq1') {
                //         sh 'mvn sonar:sonar'
                //     }
                // }

                //     }
                // }


        // stage('Nexus') {
        //     steps {
        //        script {
        //         sh 'mvn deploy'
        //       }
                
        //     }
        // }
        // stage('Quality Gate') {
        //     steps {
        //        timeout(time: 5, unit: 'MINUTES') {
        //            waitForQualityGate abortPipeline: true
        //        }
                
        //     }
        // }

    
    }
      }
