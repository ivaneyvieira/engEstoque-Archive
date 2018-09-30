#!/usr/bin/env bash

VERSAO="$1"

DIR="engEstoque"
ARQ_VERSAO="src/main/resources/versao.txt"

if [[ "$VERSAO" = "" ]]
then
  V1=`cat ${ARQ_VERSAO} | cut -d"." -f1`
  V2=`cat ${ARQ_VERSAO} | cut -d"." -f2`
  V3=$(( "$V2 + 1" ))
  VERSAO="${V1}.${V3}"
fi

echo ${VERSAO} > ${ARQ_VERSAO}

./clean.sh "deploy $VERSAO"

sed -i 's:localhost:192.168.1.1:g' src/main/resources/ebean.properties

./gradlew --console=rich  clean
./gradlew --console=rich  build

#sshpass -pengecopi2017 rsync -avP \
#      web/src/main/resources/engecopi/*.sql root@192.168.1.14:/root/$DIR/

sshpass -pengecopi2017 rsync -avP \
       build/libs/engEstoque-1.0-SNAPSHOT.war root@192.168.1.14:/root/${DIR}/engEstoque.war

sshpass -pengecopi2017 ssh root@192.168.1.14 /root/${DIR}/build_docker.sh

sed -i 's:192.168.1.1:localhost:g' src/main/resources/ebean.properties

cp ./deploy.sh ~/Dropbox/temp/