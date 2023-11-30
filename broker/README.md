test local

```
docker run -e ANONYMOUS_LOGIN=true --detach --rm --name activemq-artemis -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:2.31.2
```


export CONTAINER_REGISTRY=apache
export PROJECT_ARTIFACTID=activemq-artemis
export PROJECT_VERSION=2.31.2
export KUBE_INGRESS_ROOT_DOMAIN=vrbx.duckdns.org

kubectl create namespace broker

envsubst < broker.envsubst.yaml | kubectl delete -n broker -f -
envsubst < broker.envsubst.yaml | kubectl apply -n broker -f -