# COVID-19 Tracker — Android App

Đồ án xây dựng ứng dụng Android hỗ trợ theo dõi thông tin COVID-19 theo quốc gia. Ứng dụng tích hợp REST API để lấy dữ liệu và hiển thị trên giao diện di động.

## Công nghệ sử dụng

- **Ngôn ngữ ứng dụng:** Java.
- **Giao diện:** Android XML Layout.
- **Dữ liệu:** REST API.
- **Thư viện hình ảnh:** Glide.
- **Công cụ:** Android Studio, Gradle, Git.

## Chức năng

- Đăng ký và đăng nhập tài khoản.
- Hiển thị danh sách quốc gia và thông tin COVID-19.
- Tìm kiếm quốc gia.
- Xem thông tin chi tiết: tổng ca nhiễm, số ca tử vong, hồi phục và ca mới theo dữ liệu API cung cấp.
- Hiển thị cờ quốc gia.

## Vai trò và đóng góp

**Lưu Hoàng Phúc — Trưởng nhóm**

- Phân tích yêu cầu, xác định các chức năng và lập kế hoạch triển khai cho nhóm.
- Tham gia xây dựng cấu trúc ứng dụng và tích hợp API bên ngoài.
- Tham gia xử lý dữ liệu trả về từ API và hiển thị lên giao diện.
- Phối hợp kiểm tra chức năng, xử lý lỗi và hoàn thiện đồ án.

## Cấu trúc mã nguồn

- `app/src/main/java/`: Các lớp xử lý giao diện, mô hình dữ liệu và kết nối API.
- `app/src/main/res/layout/`: Các layout XML.
- `app/src/main/res/`: Hình ảnh, biểu tượng, chuỗi văn bản và giao diện.
- `app/src/main/AndroidManifest.xml`: Khai báo thành phần và quyền của ứng dụng.
- `app/build.gradle.kts`: Cấu hình build và thư viện của module ứng dụng.

## Cách mở và chạy

1. Clone repository:

   ```bash
   git clone https://github.com/Bunahs/covid19-android-app.git
   ```

2. Mở thư mục dự án bằng Android Studio.
3. Cài đặt Android SDK và cấu hình Gradle JDK phù hợp với cấu hình dự án.
4. Chờ Gradle Sync hoàn tất.
5. Kết nối thiết bị Android hoặc khởi động máy ảo.
6. Chọn module `app` và nhấn Run.

Các chức năng lấy dữ liệu từ API cần kết nối Internet. Khả năng truy xuất và độ mới của dữ liệu phụ thuộc vào dịch vụ API bên ngoài.

## Kiến thức tích lũy

- Phát triển giao diện và xử lý tương tác trên Android bằng Java.
- Tích hợp REST API, xử lý và hiển thị dữ liệu.
- Truyền dữ liệu giữa các Activity.
- Kiểm tra, xử lý lỗi và phối hợp làm việc nhóm.

## Thông tin

- **Loại dự án:** Đồ án môn học.
- **Thời gian:** 10/2024 – 12/2024.
- **Trường:** Đại học Công nghệ Sài Gòn (STU).
- **Liên hệ:** luuhoangphuc318@gmail.com
