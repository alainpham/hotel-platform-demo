docker run --rm -i \
    grafana/k6:0.47.0 \
    run \
    - < script.js

docker run --rm -i \
    -e BASE_URL=http://booking-hub:8080 \
    --net o11y \
    grafana/k6:0.47.0 run  \
    --duration 5s \
    --vus 1 \
    - < script.js

docker run --rm -i \
    -e BASE_URL=http://booking-hub:8080 \
    --net o11y \
    grafana/k6:0.47.0 run  \
    - < script.js

kubectl apply -f k6.yaml