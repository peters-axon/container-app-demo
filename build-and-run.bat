docker buildx build --progress plain --no-cache --debug -t container-app-demo .
docker run --rm --name container-app-demo -p 8080:8080 container-app-demo