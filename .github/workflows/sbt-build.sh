#!/bin/bash -e

set -x

if [ -z "$1" ]
  then
    echo "Scala version is missing. Please enter the Scala version."
    echo "sbt-build.sh 2.13.5"
    exit 1
else
  SCALA_VERSION=$1
  echo "============================================"
  echo "Build projects"
  echo "--------------------------------------------"
  echo ""
  if [[ "$CI_BRANCH" == "main" || "$CI_BRANCH" == "release" ]]
  then
    sbt -d -J-Xmx2048m \
      ++${SCALA_VERSION}! \
      clean \
      packagedArtifacts
  else
    sbt -d -J-Xmx2048m \
      ++${SCALA_VERSION}! \
      clean \
      package
  fi

  echo "============================================"
  echo "Building projects: Done"
  echo "============================================"
fi
