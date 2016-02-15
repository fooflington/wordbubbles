#!/usr/bin/env bash -x

make wordbubbles.jar
mkdir -p war/WEB-INF/lib war/WEB-INF/classes
cp wordbubbles.jar war/WEB-INF/lib
# cp -r uk war/WEB-INF/classes
cd war
zip -r ../wordbubbles.war *.jsp WEB-INF
