build docker image command: (in project root directory)
docker build -t safar/safar-trial-rest-api-docker .

build docker image with version tag command: (in project root directory)
docker build -t safar/safar-trial-rest-api-docker:v3.0.1 .
docker build -t safar/safar-trial-rest-api-docker:latest .

run docker image command on port 8080
docker run -p 8080:8080 safar/safar-trial-rest-api-docker:latest