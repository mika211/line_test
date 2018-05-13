FROM google/cloud-sdk:alpine
RUN apk update
    apk add openjdk8
    gcloud config set project $PROJECT_ID
CMD ./gradlew appengineDeploy
