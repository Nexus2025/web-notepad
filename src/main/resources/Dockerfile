FROM tomcat:9.0
RUN cd /webnotepad
COPY target/webnotepad.war /usr/local/tomcat/webapps/ROOT.war
RUN mv /usr/local/tomcat/webapps/webnotepad.war /usr/local/tomcat/webapps/ROOT.war