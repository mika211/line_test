FROM google/cloud-sdk:alpine

RUN echo "now building..."

CMD echo "now running..."

# CMD ./gradlew appengineDeploy

CMD pwd | echo

CMD ls -la | echo
