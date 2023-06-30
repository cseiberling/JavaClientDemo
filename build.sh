#!/usr/bin/env bash
BRANCH=feature/enhancement2
sed -i "s/BRANCHVERSION/$BRANCH/g" pom.xml

#PRSCANSPACE="Default"
# For Pull requests:
PRSCANSPACE="https://github.com/cseiberling/JavaClientDemo|feature/enhancement2"
sed -i "s/SCANSPACE/$SCANSPACE/g" settings-override.xml

#mvn clean package -DskipTests
mvn -X -U clean package com.codelogic.agent.java:jcape-maven-plugin:23.11.17:codelogic-scan-csv -am -e -U -s settings-override.xml -DskipTests
