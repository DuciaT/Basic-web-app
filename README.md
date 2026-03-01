# TÊN DỰ ÁN

## 1. Thông tin nhóm
- **Tên nhóm:**  
- **Thành viên:**
  - Lê Đức Tâm - 2213016
  - Huỳnh Đức Tài - 

---

## 2. Public URL của Web Service (Lab 5)
- **URL:**  

https://your-public-url-here


- **Mô tả ngắn:**  
Web Service được deploy từ Lab 5, cung cấp các API phục vụ cho ứng dụng.

---

## 3. Hướng dẫn chạy dự án
# 3.1. Yêu cầu môi trường

Java Development Kit (JDK): 17

Maven: 3.8 trở lên

Database: PostgreSQL

Docker: (không bắt buộc, chỉ dùng nếu chạy bằng container)

# 3.2. Cài đặt

Clone source code từ repository:

git clone <repository-url>
cd <project-folder>
# 3.3. Cấu hình môi trường

Tạo file .env tại thư mục gốc của project và cấu hình như sau:

POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=database
POSTGRES_USER=username
POSTGRES_PASSWORD=password

SPRING_DATASOURCE_URL=jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DB}
SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}

Lưu ý:

PostgreSQL phải đang chạy trước khi khởi động ứng dụng

Database cần được tạo sẵn trong PostgreSQL

# 3.4. Chạy dự án
Cách 1: Chạy bằng Maven
mvn clean spring-boot:run
Cách 2: Build và chạy file JAR
mvn clean package
java -jar target/student-0.0.1-SNAPSHOT.jar
(Tuỳ chọn) Chạy bằng Docker
docker build -t student-app .
docker run -p 8080:8080 --env-file .env student-app
# 3.5. Truy cập ứng dụng

Web UI:
http://localhost:8080

REST API:
http://localhost:8080/api/students

## 4. Câu trả lời lý thuyết (Lab)
Câu 1

Câu hỏi:

(ghi nguyên văn câu hỏi)

Trả lời:
(viết câu trả lời ở đây)

Câu 2

Câu hỏi:

(ghi nguyên văn câu hỏi)

Trả lời:
(viết câu trả lời ở đây)

Câu 3

(Tiếp tục nếu còn)

## 5. Screenshot các module (Lab 4)
Module 1: (Tên module)

Mô tả:
Mô tả ngắn chức năng của module.

Module 2: (Tên module)

Mô tả:
Mô tả ngắn chức năng của module.

Module 3: (Tên module)