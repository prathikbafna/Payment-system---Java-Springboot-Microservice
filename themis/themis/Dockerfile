FROM maven:3.8.4-openjdk-17 as maven
WORKDIR /themis
COPY . /themis
RUN mvn package

FROM openjdk:17.0
ARG JAR_FILE=themis-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app

RUN mkdir -p /usr/local/tomcat/newrelic
#ADD ./newrelic/newrelic.jar /usr/local/tomcat/newrelic/newrelic.jar
#ADD ./newrelic/newrelic.yml /usr/local/tomcat/newrelic/newrelic.yml
#ADD ./newrelic/newrelic.pem /usr/local/tomcat/newrelic/newrelic.pem
#ENV JAVA_OPTS="$JAVA_OPTS -javaagent:/usr/local/tomcat/newrelic/newrelic.jar"

######----vmware integration
# RUN mkdir -p /usr/local/tomcat/vmware
# ADD ./vmware/opentelemetry-javaagent.jar /usr/local/tomcat/vmware/javaagent.jar
# RUN chmod -R go+r /usr/local/tomcat/vmware/javaagent.jar
# ENV JAVA_OPTS="$JAVA_OPTS -javaagent:/usr/local/tomcat/vmware/javaagent.jar"

#ENV export NEW_RELIC_CA_BUNDLE_PATH=DOES_NOT_EXIST

EXPOSE 8080
#ARG trustStorePassword
#ENV TRUST_STORE_PASSWORD=$trustStorePassword
COPY --from=maven /themis/target/${JAR_FILE} /opt/app/
#COPY rds-truststore.jks /opt/app/rds-truststore.jks


#RUN keytool -importcert -alias newrelic -file /usr/local/tomcat/newrelic/newrelic.pem -keystore /opt/app/rds-truststore.jks -storepass N98358@nit -noprompt

#CMD java $JAVA_OPTS -Djavax.net.debug=all -Djavax.net.ssl.trustStore=/opt/app/rds-truststore.jks -Djavax.net.ssl.trustStorePassword=N98358@nit -jar themis-0.0.1-SNAPSHOT.jar

CMD java -jar themis-0.0.1-SNAPSHOT.jar
