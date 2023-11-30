docker run --rm -i \
    grafana/k6:0.47.0 \
    run \
    - < script.js

docker run --rm -i \
    grafana/k6:0.47.0 run  \
    --duration 5s \
    --vus 1 \
    - < script.js


kubectl apply -f k6.yaml