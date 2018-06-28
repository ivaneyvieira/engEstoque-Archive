#!/bin/bash

COMMENT="$1"

git config --global credential.helper 'cache --timeout=3600'
./gradlew --console=rich  clean

if [[ "$COMMENT" == "" ]]
then
  git add .
  git pull
  ./gradlew --console=rich  vaadinCompile
  ./gradlew --console=rich  vaadinThemeCompile
  bzcat  sql/engEstoque.sql.bz2 | mysql engEstoque 
else
  mysqldump engEstoque | bzip2 > sql/engEstoque.sql.bz2
  git add .
  git commit -m"$COMMENT"
  git push
fi

