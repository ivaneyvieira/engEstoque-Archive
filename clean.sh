#!/bin/bash

COMMENT="$1"

git config --global credential.helper 'cache --timeout=3600'
  ./gradlew clean

if [[ "$COMMENT" == "" ]]
then
  git pull
  ./gradlew vaadinCompile
  ./gradlew vaadinThemeCompile
else
  mysqldump engEstoque | bzip2 > sql/engEstoque.sql.bz2
  git add .
  git commit -m"$COMMENT"
  git push
fi

