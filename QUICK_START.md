# 🚀 Quick Start Guide

## Cách nhanh nhất để chạy CineBook

### 1️⃣ Yêu cầu
- Docker Desktop đã cài đặt và đang chạy
- Git (để clone dự án)

### 2️⃣ Clone dự án
```bash
git clone <repository-url>
cd CineBook
```

### 3️⃣ Khởi động (chọn 1 trong 2 cách)

**Cách 1: Dùng script (Khuyến nghị)**
```bash
# Windows
start.bat

# Linux/Mac
chmod +x start.sh
./start.sh
```

**Cách 2: Dùng Docker Compose**
```bash
docker-compose up -d
```

### 4️⃣ Truy cập ứng dụng
- **API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

### 5️⃣ Đăng nhập
- **Username**: `superadmin`
- **Password**: `Admin@123`

---

## 📝 Các lệnh thường dùng

```bash
# Xem logs
docker-compose logs -f

# Dừng ứng dụng
docker-compose down

# Khởi động lại
docker-compose restart

# Xóa dữ liệu và khởi động lại
docker-compose down -v
docker-compose up -d
```

---

## 🔧 Cấu hình (Tùy chọn)

Nếu muốn thay đổi cấu hình:
1. Copy file `.env.example` thành `.env`
2. Chỉnh sửa các giá trị trong `.env`
3. Khởi động lại: `docker-compose up -d`

---

## 📚 Tài liệu chi tiết

- [DOCKER_GUIDE.md](DOCKER_GUIDE.md) - Hướng dẫn Docker đầy đủ
- [README.md](README.md) - Tài liệu dự án

---

## ❓ Gặp vấn đề?

1. Kiểm tra Docker đã chạy chưa
2. Xem logs: `docker-compose logs -f`
3. Đọc [DOCKER_GUIDE.md](DOCKER_GUIDE.md) phần Troubleshooting
