#Clones the repository
FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/FedericoFernandezBarra/iptracker

#Builds from maven
FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/iptracker /app
RUN mvn install

#Runs the web application on port 3783 (defined in application.properties)
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/iptracker-1.0.0-jar-with-dependencies.jar /app
EXPOSE 3783
CMD ["java", "-jar", "iptracker-1.0.0-jar-with-dependencies.jar"]