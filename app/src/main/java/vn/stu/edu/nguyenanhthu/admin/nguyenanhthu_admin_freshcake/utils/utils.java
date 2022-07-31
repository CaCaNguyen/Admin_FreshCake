package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.utils;

public class utils {
    //Khai báo đường dẫn trong folder API
    public static String ip = "http://192.168.1.74";

    //kiem tra dang nhap vao tra ve true
    public static String URL_CheckAdmin = ip + ":4444/admin/detail/";

    public static String URL_CreateAdmin = ip + ":4444/admin/add/";

    //lay danh sach tat ca danh muc tra ve results
    public static String URL_GetAllDanhMuc = ip + ":4444/danhmuc/list/";

    public static String URL_InsertDanhMuc = ip + ":4444/danhmuc/add/";

    public static String URL_UpdateDanhMuc = ip + ":4444/danhmuc/update/";

//    public static String URL_DeleteDanhMuc = ip + ":4444/danhmuc/delete/";

    public static String URL_DeleteDanhMuc (int id_danhmuc) {
        return ip + ":4444/danhmuc/delete/" + id_danhmuc;
    }
    //lay danh sach tat ca san pham tra ve results
    public static String URL_GetAllSanPham = ip + ":4444/sanpham/list/";

    public static String URL_GetAllTenSanPham = ip+ ":4444/sanpham/ten";

    public static String URL_InsertSanPham = ip + ":4444/sanpham/add/";

    public static String URL_DeleteSanPham (int id_sanpham) {
        return ip + ":4444/sanpham/delete/" + id_sanpham;
    }

    public static String URL_UpdateSanPham = ip + ":4444/sanpham/update/";

    //kiem tra tao tai khoan va tra ve true
    public static String URL_CreateDangKy = ip + ":4444/dangky/add/";

    public static String URL_GetAllSize = ip + ":4444/kichthuoc/list/";

    public static String URL_InsertSize = ip + ":4444/kichthuoc/add/";

    public static String URL_UpdateSize = ip + ":4444/kichthuoc/update/";

    public static String URL_DeleteSize (int id_size) {
        return ip + ":4444/kichthuoc/delete/" + id_size;
    }

    public static String URL_GetSizeById = ip + ":4444/mota/detail/";

    public static String URL_GetAllMoTa = ip + ":4444/mota/list";

    public static String URL_InsertMoTa = ip + ":4444/mota/add/";

    public static String URL_DeleteMoTa (int id_mota) {
        return ip + ":4444/mota/delete/" + id_mota;
    }

//    public static String URL_UpdateMoTa = ip + ":4444/mota/update/";

    public static String URL_GetAllGioHang = ip + ":4444/giohang/list";

    public static String URL_UpdateGioHang = ip + ":4444/giohang/update/";

    public static String URL_GetAllDangKy = ip + ":4444/dangky/list";

    public static String URL_GetAllAdmin = ip + ":4444/admin/list";

    public static String URL_DeleteAdmin (int id_admin) {
        return ip + ":4444/admin/delete/" + id_admin;
    }

    public static String URL_GetChiTietGioHangByIdGioHang (int id_giohang){
        return ip + ":4444/chitietgiohang/detail/" + id_giohang;
    }

    public static String URL_GetThongKeMua = ip + ":4444/chitietgiohang/slban/";

    public static String URL_GetThongKeBan = ip + ":4444/chitietgiohang/thongke";

    public static String URL_GetAbout = ip + ":4444/about/list";

    public static String URL_GetThongKeDoanhThu = ip + ":4444/chitietgiohang/doanhthu";
}
