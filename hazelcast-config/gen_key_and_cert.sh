#!/bin/bash

openssl req -newkey rsa:2048 -nodes -keyout test.key -x509 -days 365 -out test.crt
