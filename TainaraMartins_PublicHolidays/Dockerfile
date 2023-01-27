FROM openjdk:17-oracle
EXPOSE 8088
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} TainaraMartins_PublicHolidays-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/TainaraMartins_PublicHolidays-0.0.1-SNAPSHOT.jar"]
