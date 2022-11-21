init: clean build deploy

clean:
	- docker kill cra_api
	- docker rm cra_api

build:
	mvn install

deploy:
	docker run -d --name=cra_api -p 8098:8098 slobx/cra