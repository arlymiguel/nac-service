# nac-service

# swagger link
http://localhost:8080/swagger-ui.html

# POST CURL
curl --location --request POST 'http://localhost:8080/api/v1/nace' \
--header 'Content-Type: application/json' \
--data-raw '{
    "order": "aa",
    "level": "bb",
    "code": "cc",
    "parent": "dd",
    "description": "ee",
    "include": "ff",
    "rulings": "gg",
    "exclude": "hh",
    "reference": "ii"
}'

# GET by Order CURL
curl --location --request GET 'http://localhost:8080/api/v1/nace/order/398481'
