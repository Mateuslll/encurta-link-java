#!/bin/bash
set -e

# Navigate to the app directory where template.yml is located
cd "$(dirname "$0")/.."

NETWORK_NAME="sam-local-net"

echo "Building SAM application..."
sam build

echo "Starting SAM local API..."
sam local start-api --docker-network $NETWORK_NAME --warm-containers EAGER
