FROM google/cloud-sdk:alpine
RUN apk update
RUN apk add openjdk8
RUN gcloud config set project $PROJECT_ID
CMD ./gradlew appengineDeploy
