JAVA_HOME := $(shell /usr/libexec/java_home -v 17)
PATH := ${JAVA_HOME}/bin:${PATH}

deploy:
		cd ./frontend-java && ./gradlew jar
		cd ./frontend-js && npm run build
		cd ./backend && ./gradlew bootJar
		set -a && source .env.internal && set +a && scp -r ./backend/build/libs/app.jar $${DEPLOY_HOST}:/app

start:
		java -jar ./backend/build/libs/app.jar

.PHONY: start