export base_url=http://localhost:8090
export base_url=https://availability-service.vrbx.duckdns.org

curl -X 'POST' \
  "${base_url}/availabilities" \
  -H 'accept: application/hal+json' \
  -H 'Content-Type: application/json' \
  -d '{
  "hotel": "accor",
  "room": "3",
  "bookingStartDate": "2023-10-12",
  "bookingEndDate": "2023-10-18",
  "booked": true
}'

curl -X 'POST' \
  "${base_url}/check-availability" \
  -H 'accept: application/hal+json' \
  -H 'Content-Type: application/json' \
  -d '{
  "hotel": "accor",
  "room": "3",
  "bookingStartDate": "2023-10-10",
  "bookingEndDate": "2023-10-11"
}'