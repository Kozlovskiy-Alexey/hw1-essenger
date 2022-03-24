FROM tomcat:8.5.75-jre11-temurin
COPY target/hw1-messenger-1.0.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]