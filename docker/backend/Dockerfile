# Maven

FROM maven:3.6.1-jdk-11 as maven_builder

USER root

WORKDIR /app

RUN mkdir query
COPY /query/ /app/query/

WORKDIR /app/query

RUN ls

RUN mvn compile
RUN mvn package

WORKDIR /app

RUN mkdir command
COPY /command/ /app/command/

WORKDIR /app/command

RUN mvn compile
RUN mvn package

WORKDIR /app

###################### Ubuntu ######################

FROM ubuntu:18.04

USER root

RUN apt-get update -y
RUN apt-get upgrade -y

RUN apt-get update --fix-missing

RUN apt-get install default-jdk -y
RUN apt-get install curl -y

RUN groupadd tomcat
RUN useradd -s /bin/false -g tomcat -d /opt/tomcat tomcat

WORKDIR /tmp
RUN curl -O http://ftp.unicamp.br/pub/apache/tomcat/tomcat-9/v9.0.36/bin/apache-tomcat-9.0.36.tar.gz

WORKDIR /
RUN mkdir /opt/tomcat/

WORKDIR /tmp
RUN tar xzvf apache-tomcat-9.0.36.tar.gz -C /opt/tomcat --strip-components=1

WORKDIR /opt/tomcat
RUN chgrp -R tomcat /opt/tomcat
RUN chmod -R g+r conf
RUN chmod g+x conf
RUN chown -R tomcat webapps/ work/ temp/ logs/

COPY /docker/tomcat.service /etc/systemd/system/

COPY --from=maven_builder /app/query/target/query.war /opt/tomcat/webapps
COPY --from=maven_builder /app/command/target/command.war /opt/tomcat/webapps

USER root

WORKDIR /app
RUN mkdir input
COPY /docker/input/input.csv /app/input/

EXPOSE 8080

CMD /opt/tomcat/bin/catalina.sh run