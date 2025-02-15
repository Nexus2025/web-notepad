FROM tomcat:9.0
RUN echo pwd
RUN cd /
COPY target/webnotepad.war /usr/local/tomcat/webapps/ROOT.war
RUN mv /usr/local/tomcat/webapps/webnotepad.war /usr/local/tomcat/webapps/ROOT.war