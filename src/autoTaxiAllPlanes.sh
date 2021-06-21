#!/bin/bash

planes=("npt09h" "dLh404" "Klm308" "sas122" "wzz543" "auA2Ty")
for plane in "${planes[@]}"
do
  curl "http://localhost:8080/taxi/$plane/35"
  echo
done

curl "http://localhost:8080/taxi/execute"