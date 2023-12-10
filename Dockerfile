FROM openjdk:17-oracle
WORKDIR app

COPY . .

RUN ./mvnw package

ENV DB_HOST=database
ENV DB_PORT=3306
ENV DB_NAME=tmsystem_db

CMD java -jar target/tmsystem-0.0.1-SNAPSHOT.jar

EXPOSE 8080

