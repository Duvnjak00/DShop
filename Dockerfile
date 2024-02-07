#
# Pakiranje aplikacije
#
FROM maven:3.8.7-openjdk-18 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Pokretanje aplikacije
#
FROM openjdk:18-jdk-slim
COPY --from=build /home/app/target/DShopApplication-0.0.1-SNAPSHOT.jar /usr/local/lib/dshop.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/usr/local/lib/webshop.jar"]
