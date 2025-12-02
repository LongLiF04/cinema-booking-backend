@echo off
echo.
echo ========================================
echo   Starting CineBook Application
echo ========================================
echo.

REM Check if Docker is running
docker info >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Docker is not running. Please start Docker Desktop first.
    pause
    exit /b 1
)

REM Check if .env exists
if not exist .env (
    echo [WARNING] .env file not found. Creating from .env.example...
    copy .env.example .env
    echo [SUCCESS] .env file created. Please update it with your configuration.
    echo.
)

REM Start services
echo [INFO] Starting Docker containers...
docker-compose up -d

REM Wait for services
echo.
echo [INFO] Waiting for services to be ready...
timeout /t 10 /nobreak >nul

REM Check status
echo.
echo ========================================
echo   Container Status
echo ========================================
docker-compose ps

echo.
echo ========================================
echo   CineBook is running!
echo ========================================
echo.
echo Access points:
echo   - API: http://localhost:8080
echo   - Swagger UI: http://localhost:8080/swagger-ui/index.html
echo.
echo Default credentials:
echo   - Username: superadmin
echo   - Password: Admin@123
echo.
echo Commands:
echo   - View logs: docker-compose logs -f
echo   - Stop: docker-compose down
echo.
pause
