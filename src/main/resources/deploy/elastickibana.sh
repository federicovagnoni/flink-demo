sudo docker run -d -p 9200:9200 -p 9300:9300 --name elasticnode -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.12.1
sudo docker run --link elasticnode:elasticsearch -d -p 5601:5601 docker.elastic.co/kibana/kibana:7.12.1
