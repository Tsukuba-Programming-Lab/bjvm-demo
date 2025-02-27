JAVA_HOME := $(shell /usr/libexec/java_home -v 17)
PATH := ${JAVA_HOME}/bin:${PATH}

run:
		set -a && source .env.internal && set +a && ssh $${DEPLOY_HOST} "java -jar /app/app.jar"

deploy:
		set -a && source .env.internal && set +a && scp -r ./backend/build/libs/app.jar $${DEPLOY_HOST}:/app

build:
		cd ./frontend-java && ./gradlew jar
		cd ./frontend-js && npm run build
		cd ./backend && ./gradlew bootJar

up:
		make build && make deploy && make run

.PHONY: build