pipeline {
    agent any

    environment {
        APP_PORT = "8080"
        OUT_DIR = "out"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Rakesh-7881/SpellCorrectionProject.git'
            }
        }

        stage('Compile') {
            steps {
                bat """
                mkdir %OUT_DIR%
                javac -d %OUT_DIR% SpellCorrectionServer.java
                """
            }
        }

        stage('Deploy') {
            steps {
                bat """
                taskkill /F /IM java.exe || exit 0
                start /B java -cp %OUT_DIR% SpellCorrectionServer %APP_PORT%
                """
            }
        }
    }
}
