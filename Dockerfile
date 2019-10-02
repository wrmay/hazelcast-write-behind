FROM adoptopenjdk/openjdk8:alpine
RUN apk add maven
ADD . /opt/project
WORKDIR /opt/project
RUN mvn install
