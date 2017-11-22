#!/bin/bash
# just deploy war file to a independent tomcat instance running
# tomcat maven plugin doc: http://tomcat.apache.org/maven-plugin-2.2/tomcat7-maven-plugin/usage.html
mvn clean tomcat7:deploy
