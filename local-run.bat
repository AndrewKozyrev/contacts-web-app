@ECHO OFF

REM 1. Start docker-compose
docker-compose -f ./docker/docker-compose.yaml up -d

REM 2. Sleep for 10 seconds
ping 127.0.0.1 -n 10 > NUL

REM 3. Run mvnw.cmd clean install (assuming build context is outside the docker directory)
if not exist ".\\target\\contacts-web-app.jar" (
    echo Jar file not found, building project...
    mvnw.cmd clean install -DskipTests
    REM 4. Sleep for 10 seconds
    ping 127.0.0.1 -n 10 > NUL
    REM 5. Run java -jar contacts-web-app.jar
    ECHO Starting JAR...
    java -jar ./target/contacts-web-app.jar
    ECHO JAR execution finished.
) else (
    REM 5. Run java -jar contacts-web-app.jar
    ECHO Starting JAR...
    java -jar ./target/contacts-web-app.jar
    ECHO JAR execution finished.
)

PAUSE