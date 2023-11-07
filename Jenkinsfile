pipeline {
    agent any 

    triggers {
        pollSCM('* * * * *')
    }
    // Got permission denied while trying to connect to the Docker daemon socket at unix.
    // sudo usermod -a -G docker jenkins
    // restart jenkins server ->  sudo service jenkins restart

    environment
    {
        DBUN='root'
        DBPW='Myd@t@b@$3'
    }

    stages {
            
        stage('Maven Compile') {
            steps {
                echo '----------------- Compiling project ----------'
                sh 'mvn clean compile'
            }
        }
        
        //  stage('Maven Test') {
        //     steps {
        //         echo '----------------- Testing project ----------'
        //         sh 'mvn clean test'
        //     }
        // }
        
        stage('Maven Build') {
             steps {
                echo '----------------- Building project ----------'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo '----------------- Building docker image ----------'
                sh '''
                    docker image build -t booking-service .
                '''
            }
        }

        stage('Docker Deploy') {
            steps {
                echo '----------------- Deploying docker image ----------'
                sh '''
                 (if  [ $(docker ps -a | grep booking-service | cut -d " " -f1) ]; then \
                        echo $(docker rm -f booking-service); \
                        echo "---------------- successfully removed booking-service ----------------"
                     else \
                    echo OK; \
                 fi;);
            docker container run \
            --env DBUN=$DBUN \
            --env DBPW=$DBPW
            --restart always \
            --name booking-service \
            -p 8081:8081 \
            -d booking-service && \
            docker network connect travel-management-network booking-service
            '''
            }
        }
    }
}