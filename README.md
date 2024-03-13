# Project Metamusic

## connect to cluster elasticsearch
- get the certificate fingerprint (view certificate in browser) or use openssl command:
`openssl x509 -fingerprint -sha256 -noout -in /path/to/http_ca.crt` file can be fetched by `cat` and `copy`
- add 'quickstart-es-http.default.es.local' to /etc/hosts


## elasticsearch query for fields
```
POST /search-metamusic-tracks/_search?pretty
{
    "query": {
        "match": {
            "medadata.BPM": "126"
        }
    }
}
```


# TODO
- attach debugger automatically
- strict formattting rules on save
- get facets for fields like BPM https://www.elastic.co/guide/en/app-search/current/facets-guide.html
- configure analyzers https://www.elastic.co/guide/en/elasticsearch/reference/current/analysis-standard-analyzer.html
- separate processing into directories and threads with streams


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

## build docker container
docker build -f src/main/docker/Dockerfile.jvm -t meta-search .
docker run -it -p 8080:8080 --env-file .env  -v /home/droeder/dev/meta-search/src/test/resources:/data  meta-search



