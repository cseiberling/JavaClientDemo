#!/usr/bin/env bash
BRANCH=feature/DEMO-1901
sed -i "s/BRANCHVERSION/$BRANCH/g" pom.xml
echo "test"

#PRSCANSPACE="Default"
# For Pull requests:
PRSCANSPACE="https://github.com/cseiberling/JavaClientDemo|feature/DEMO-1901"
sed -i "s/SCANSPACE/$PRSCANSPACE/g" settings-override.xml

#mvn clean package -DskipTests
mvn -X -U clean package com.codelogic.agent.java:jcape-maven-plugin:23.12.7:codelogic-scan-csv -am -e -U -s settings-override.xml -DskipTests
