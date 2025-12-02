#!/bin/bash

echo "🚀 Starting CineBook Application..."
echo ""

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker first."
    exit 1
fi

# Check if .env exists
if [ ! -f .env ]; then
    echo "⚠️  .env file not found. Creating from .env.example..."
    cp .env.example .env
    echo "✅ .env file created. Please update it with your configuration."
    echo ""
fi

# Start services
echo "📦 Starting Docker containers..."
docker-compose up -d

# Wait for services to be ready
echo ""
echo "⏳ Waiting for services to be ready..."
sleep 10

# Check status
echo ""
echo "📊 Container Status:"
docker-compose ps

echo ""
echo "✅ CineBook is running!"
echo ""
echo "🌐 Access points:"
echo "   - API: http://localhost:8080"
echo "   - Swagger UI: http://localhost:8080/swagger-ui/index.html"
echo ""
echo "👤 Default credentials:"
echo "   - Username: superadmin"
echo "   - Password: Admin@123"
echo ""
echo "📝 View logs: docker-compose logs -f"
echo "🛑 Stop: docker-compose down"
