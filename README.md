# nac-service

# Requeriments
mysql docker container:
docker run --name=mysqlnac -p 3306:3306 --restart on-failure -e MYSQL_ROOT_PASSWORD=admin -d mysql

# swagger link
http://localhost:8080/swagger-ui/index.html

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
# POST upload csv to bulk data
curl --location --request POST 'http://localhost:8080/api/v1/nace/upload' \
--form 'file=@"/C:/Users/ARLY/Downloads/NACE_REV2_20220929_150839.csv"'
The csv file is located in /file path into the project
