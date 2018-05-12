FROM hronom/ubuntu1604-oraclejdk8

# Copy files
RUN mkdir application
COPY target/*.jar /application/app.jar

WORKDIR /application

CMD ["./app.jar"]
