package com.loctran.service.utils;

public class MessageUtil {
    public static class ResponseMessage {
        private static final String ENTITY_NOT_FOUND = "Không tìm thấy %s";

        public static final String USER_EMAIL_EXISTED = "Email này đã tồn tại, vui lòng chọn email khác";
        public static final String USER_LOGIN_FAILED = "Tài khoản hoặc mật khẩu không chính xác";

        public static final String USER_NOT_FOUND = String.format(ENTITY_NOT_FOUND, "người dùng");
        public static final String PRODUCT_NOT_FOUND = String.format(ENTITY_NOT_FOUND, "sản phẩm");
        public static final String POST_NOT_FOUND = String.format(ENTITY_NOT_FOUND, "bài viết");
        public static final String DATA_NOT_FOUND = String.format(ENTITY_NOT_FOUND, "dữ liệu");


        public static final String UPDATE_DATA_FORBIDDEN = "Không thể chỉnh sửa dữ liệu";
        public static final String DELETE_DATA_FORBIDDEN = "Không thể xóa dữ liệu";



        public static final String GET_DATA_SUCCESS = "Lấy thông tin thành công";
        public static final String CREATE_DATA_SUCCESS = "Tạo dữ liệu thành công";
        public static final String UPDATE_DATA_SUCCESS = "Chỉnh sửa dữ liệu thành công";
        public static final String DELETE_DATA_SUCCESS = "Xóa dữ liệu thành công";







    }
}
