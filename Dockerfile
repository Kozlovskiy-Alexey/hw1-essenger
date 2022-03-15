FROM tomcat:8.5.76-jre11-temurin
COPY target/hw1-messenger-1.0 /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]