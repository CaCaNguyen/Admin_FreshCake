package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemSanPham;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;

public interface MainControllerView_ThemSanPham {
    public void showMessage(String message);
    public void updateUserModel(SanPham sanpham);
    public void showSpiner(ArrayList<DanhMuc> danhMucArrayList);
    public void showDialog();
    public void updateListView();
}
