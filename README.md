# create network

docker network create elastic

# create elasticsearch container

docker pull docker.elastic.co/elasticsearch/elasticsearch:8.12.2
docker run --name elasticsearch --net elastic -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" -t
docker.elastic.co/elasticsearch/elasticsearch:8.12.2

# create kibana container

docker pull docker.elastic.co/kibana/kibana:8.12.2
docker run --name kibana --net elastic -p 5601:5601 docker.elastic.co/kibana/kibana:8.12.2

# misc

calc fingerprint from cert file:
`openssl x509 -fingerprint -sha256 -noout -in /path/to/http_ca.crt'''

# current setup

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Elasticsearch security features have been automatically configured!
✅ Authentication is enabled and cluster connections are encrypted.

ℹ Password for the elastic user (reset with `bin/elasticsearch-reset-password -u elastic`):
rHOXra-heslHuHp9O61h

ℹ HTTP CA certificate SHA-256 fingerprint:
1bdca58f0e6a47b44b1a175d0d511ea0cd2dc070cfcba9ea0910b9e9a395a2c0

ℹ Configure Kibana to use this cluster:
• Run Kibana and click the configuration link in the terminal when Kibana starts.
• Copy the following enrollment token and paste it into Kibana in your browser (valid for the next 30 minutes):
eyJ2ZXIiOiI4LjEyLjIiLCJhZHIiOlsiMTcyLjE4LjAuMjo5MjAwIl0sImZnciI6IjFiZGNhNThmMGU2YTQ3YjQ0YjFhMTc1ZDBkNTExZWEwY2QyZGMwNzBjZmNiYTllYTA5MTBiOWU5YTM5NWEyYzAiLCJrZXkiOiJhaldxX28wQjRqTTlXMklKMVB1Tjp3R3RTSVladlNTLUtfMmF5NGpTWnRBIn0=

ℹ Configure other nodes to join this cluster:
• Copy the following enrollment token and start new Elasticsearch nodes
with `bin/elasticsearch --enrollment-token <token>` (valid for the next 30 minutes):
eyJ2ZXIiOiI4LjEyLjIiLCJhZHIiOlsiMTcyLjE4LjAuMjo5MjAwIl0sImZnciI6IjFiZGNhNThmMGU2YTQ3YjQ0YjFhMTc1ZDBkNTExZWEwY2QyZGMwNzBjZmNiYTllYTA5MTBiOWU5YTM5NWEyYzAiLCJrZXkiOiJiRFdxX28wQjRqTTlXMklKMVB1VDppdzducHUwWFI0U1NaV2Y5UXlKd0FnIn0=

If you're running in Docker, copy the enrollment token and run:
`docker run -e "ENROLLMENT_TOKEN=<token>" docker.elastic.co/elasticsearch/elasticsearch:8.12.2`
