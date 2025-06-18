#!/bin/zsh

PROJECT_BASE=/Users/xcx2988/projects/meins
SERVER_PATH=${PROJECT_BASE}/timesheet
CLIENT_PATH=${PROJECT_BASE}/timesheet-client
SCRIPTS_TIMESHEET_JAR_PATH=/Users/xcx2988/scripts/timesheet/jar

TARGET_APP_PATH=$SERVER_PATH/src/main/resources/public/

echo "=== BUILD APP ==="
cd $CLIENT_PATH || exit 1
ng build #--base-href=/ui/

echo "=== COPY BUILD FILES ==="
rm -r ${TARGET_APP_PATH:?}/*
cp -r $CLIENT_PATH/dist/timesheet-client/browser/* $TARGET_APP_PATH/

echo "=== MAVEN BUILD ==="
cd $SERVER_PATH || exit 1
mvn package

echo "=== COPY TO SCRIPTS ==="
mkdir -p $SCRIPTS_TIMESHEET_JAR_PATH
cp $SERVER_PATH/target/timesheet-0.0.1-SNAPSHOT.jar $SCRIPTS_TIMESHEET_JAR_PATH/timesheet.jar