FROM openjdk:8
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/gestion-station-ski-1.0.jar"]
