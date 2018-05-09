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
  git add .
  git commit -m"$COMMENT"
  git push
fi

