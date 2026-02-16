#!/bin/bash
set -e

APP_NAME="coupon-api"
COMPOSE_FILE="docker-compose.dev.yml"

echo "🚀 Subindo $APP_NAME - ambiente DEV (sem JAR)"

echo "🧹 Derrubando containers antigos..."
docker-compose -f $COMPOSE_FILE down

echo "▶️ Subindo aplicação..."
docker-compose -f $COMPOSE_FILE up --build