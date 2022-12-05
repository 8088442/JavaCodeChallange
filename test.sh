#!/bin/bash
#Testing script

PROD_IDS="M20324 AC7836 C77154 BB5476 B42000"

for j in $PROD_IDS
do
  for i in {1..5}
  do
    curl --location --request POST 'localhost:8081/review/' \
      --header 'Authorization: Basic dXNlcjoxMjM0' \
      --header 'Content-Type: application/json' \
      --data-raw '{"productId": "'$j'", "reviewScore": "'$((1 + $RANDOM % 5))'", "reviewText": "Review for '$j'"}'
  done
done

if ! [ -x "$(command -v jq)" ]; then
  echo 'Error: jq is not installed.' >&2
  echo 'JSON output will not be prettified'
fi

for prod in $PROD_IDS
do
  echo "Testing product $prod..."
  curl -s --location --request GET 'http://localhost:8082/product/'$prod | jq --color-output
  echo
  echo "------------------------------------------------------------------------------------"
done
