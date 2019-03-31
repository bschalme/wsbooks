FROM openjdk:7-jdk-alpine
LABEL name="WS-Books" \
      version="0.0.17" \
      release="1" \
      architecture="x86_64" \
      vendor="Airspeed Consulting" \
      Summary="Updates QuickBooks with yesterday's TSheets timesheets. In addition, each month it generates an invoice for the previous month, and sends it to FreshBooks for delivery to your clients." \
      maintainer="Brian Schalme <bschalme@airspeed.ca>"
ADD target/wsbooks-*.war app.jar
EXPOSE 8080
ENTRYPOINT ["java","-XX:MaxPermSize=128m","-Djetty.host=0.0.0.0","-jar","/app.jar"]