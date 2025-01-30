#!/bin/bash

# Define the JAR file path and log file
JAR_NAME="Ahmed_Planetarium-1.0-SNAPSHOT.jar"
JAR_PATH="/var/lib/jenkins/workspace/Job1/target/$JAR_NAME"
LOG_PATH="/home/ec2-user/app.log"

echo "Checking if the application is already running..."

# Find the process running the JAR file (Updated Fix)
PID=$(ps aux | grep "$JAR_NAME" | grep -v "grep" | awk '{print $2}')

if [ ! -z "$PID" ]; then
    echo "Stopping existing application (PID: $PID)..."
    kill -9 $PID
    sleep 2
fi

echo "Starting new application..."
nohup java -jar "$JAR_PATH" > "$LOG_PATH" 2>&1 &

# Wait for a few seconds to ensure the app starts
sleep 5


# Verify if the application started successfully
NEW_PID=$(ps aux | grep "$JAR_NAME" | grep -v "grep" | awk '{print $2}')

if [ ! -z "$NEW_PID" ]; then
    echo "Application started successfully with PID: $NEW_PID"
else
    echo "Failed to start the application."
    exit 1
fi