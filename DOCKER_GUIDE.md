# Hướng dẫn sử dụng Docker cho CineBook

## 📋 Mục lục
1. [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
2. [Cách 1: Chạy toàn bộ với Docker](#cách-1-chạy-toàn-bộ-với-docker)
3. [Cách 2: Chạy môi trường Development](#cách-2-chạy-môi-trường-development)
4. [Các lệnh Docker hữu ích](#các-lệnh-docker-hữu-ích)
5. [Troubleshooting](#troubleshooting)

---

## Yêu cầu hệ thống

- **Docker Desktop** (Windows/Mac) hoặc **Docker Engine** (Linux)
- **Docker Compose** v2.0+
- Ít nhất 4GB RAM khả dụng
- Ít nhất 10GB dung lượng ổ cứng

### Cài đặt Docker

**Windows/Mac:**
- Tải Docker Desktop: https://www.docker.com/products/docker-desktop

**Linux:**
```bash
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
```

---

## Cách 1: Chạy toàn bộ với Docker

### Bước 1: Clone dự án
```bash
git clone <repository-url>
cd CineBook
```

### Bước 2: Cấu hình biến môi trường (Tùy chọn)
```bash
# Sao chép file mẫu
cp .env.example .env

# Chỉnh sửa file .env nếu cần
# Ví dụ: thay đổi email, JWT secret, v.v.
```

### Bước 3: Khởi động toàn bộ hệ thống
```bash
docker-compose up -d
```

Lệnh này sẽ:
- ✅ Tải các Docker images (PostgreSQL, Redis)
- ✅ Build ứng dụng Spring Boot
- ✅ Khởi động 3 containers: postgres, redis, app
- ✅ Tự động tạo database và tables
- ✅ Tạo tài khoản Super Admin mặc định

### Bước 4: Kiểm tra trạng thái
```bash
docker-compose ps
```

Kết quả mong đợi:
```
NAME                  STATUS    PORTS
cinebook-postgres     Up        0.0.0.0:5432->5432/tcp
cinebook-redis        Up        0.0.0.0:6379->6379/tcp
cinebook-app          Up        0.0.0.0:8080->8080/tcp
```

### Bước 5: Truy cập ứng dụng
- **API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **Tài khoản mặc định**:
  - Username: `superadmin`
  - Password: `Admin@123`

### Dừng ứng dụng
```bash
# Dừng containers (giữ lại dữ liệu)
docker-compose stop

# Dừng và xóa containers (giữ lại dữ liệu)
docker-compose down

# Dừng và xóa tất cả (bao gồm dữ liệu)
docker-compose down -v
```

---

## Cách 2: Chạy môi trường Development

Phù hợp khi bạn muốn:
- Chỉnh sửa code và test ngay lập tức
- Debug ứng dụng từ IDE
- Chỉ cần PostgreSQL và Redis

### Bước 1: Khởi động PostgreSQL và Redis
```bash
docker-compose -f docker-compose.dev.yml up -d
```

### Bước 2: Cấu hình biến môi trường
**Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/cinebook_db"
$env:DB_USERNAME="cinebook_user"
$env:DB_PASSWORD="cinebook_password"
$env:JWT_SECRET="your-secret-key-min-256-bits"
$env:JWT_EXPIRATION_MS="86400000"
$env:EMAIL_USERNAME="your-email@gmail.com"
$env:EMAIL_PASSWORD="your-app-password"
```

**Linux/Mac:**
```bash
export DB_URL="jdbc:postgresql://localhost:5432/cinebook_db"
export DB_USERNAME="cinebook_user"
export DB_PASSWORD="cinebook_password"
export JWT_SECRET="your-secret-key-min-256-bits"
export JWT_EXPIRATION_MS="86400000"
export EMAIL_USERNAME="your-email@gmail.com"
export EMAIL_PASSWORD="your-app-password"
```

### Bước 3: Chạy ứng dụng Spring Boot
```bash
mvn spring-boot:run
```

Hoặc chạy từ IDE (IntelliJ IDEA, Eclipse, VS Code)

---

## Các lệnh Docker hữu ích

### Xem logs
```bash
# Xem logs tất cả services
docker-compose logs -f

# Xem logs của một service cụ thể
docker-compose logs -f app
docker-compose logs -f postgres
docker-compose logs -f redis
```

### Rebuild ứng dụng
```bash
# Rebuild khi có thay đổi code
docker-compose up -d --build app
```

### Truy cập vào container
```bash
# Truy cập PostgreSQL
docker exec -it cinebook-postgres psql -U cinebook_user -d cinebook_db

# Truy cập Redis CLI
docker exec -it cinebook-redis redis-cli

# Truy cập container app
docker exec -it cinebook-app sh
```

### Xem resource usage
```bash
docker stats
```

### Dọn dẹp hệ thống
```bash
# Xóa tất cả containers đã dừng
docker container prune

# Xóa tất cả images không sử dụng
docker image prune -a

# Xóa tất cả volumes không sử dụng
docker volume prune

# Dọn dẹp toàn bộ
docker system prune -a --volumes
```

---

## Troubleshooting

### 1. Port đã được sử dụng
**Lỗi:** `Bind for 0.0.0.0:8080 failed: port is already allocated`

**Giải pháp:**
```bash
# Kiểm tra process đang dùng port
# Windows
netstat -ano | findstr :8080

# Linux/Mac
lsof -i :8080

# Thay đổi port trong docker-compose.yml
ports:
  - "8081:8080"  # Đổi từ 8080 thành 8081
```

### 2. Container không khởi động
```bash
# Xem logs chi tiết
docker-compose logs app

# Kiểm tra trạng thái
docker-compose ps

# Restart container
docker-compose restart app
```

### 3. Database connection failed
```bash
# Kiểm tra PostgreSQL đã sẵn sàng chưa
docker exec cinebook-postgres pg_isready -U cinebook_user

# Restart PostgreSQL
docker-compose restart postgres
```

### 4. Redis connection failed
```bash
# Kiểm tra Redis
docker exec cinebook-redis redis-cli ping

# Restart Redis
docker-compose restart redis
```

### 5. Build failed
```bash
# Xóa cache và rebuild
docker-compose build --no-cache app
docker-compose up -d
```

### 6. Out of memory
```bash
# Tăng memory cho Docker Desktop
# Settings > Resources > Memory > Tăng lên 4GB hoặc hơn
```

---

## Cấu trúc dự án

```
CineBook/
├── docker-compose.yml          # Chạy toàn bộ hệ thống
├── docker-compose.dev.yml      # Chỉ chạy DB và Redis
├── Dockerfile                  # Build Spring Boot app
├── .dockerignore              # Loại trừ files khi build
├── .env.example               # Mẫu biến môi trường
└── .env                       # Biến môi trường thực tế (không commit)
```

---

## Lưu ý quan trọng

1. **Không commit file `.env`** vào Git (đã có trong `.gitignore`)
2. **Thay đổi JWT_SECRET** trong production
3. **Backup dữ liệu** trước khi chạy `docker-compose down -v`
4. **Sử dụng strong password** cho database trong production
5. **Cấu hình email** đúng để nhận thông báo

---

## Liên hệ & Hỗ trợ

Nếu gặp vấn đề, vui lòng:
1. Kiểm tra logs: `docker-compose logs -f`
2. Xem phần Troubleshooting ở trên
3. Tạo issue trên GitHub repository
