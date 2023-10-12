./gradlew clean build 

# With docker-compose
docker compose up -d

# Or with minikube
## Start minikube
minikube start
## Build image
docker build -t task-image .
## create pod
kubectl create -f create-pod.yml
## Check that pod is running
kubectl get pods
