FROM tomcat:9.0
COPY target/webnotepad.war /usr/local/tomcat/webapps/ROOT.war