package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham;

import java.util.ArrayList;

public interface MainControllerView_SanPham {
    public void showMessage(String message);
    public void updateUserModel(SanPham sanpham);
    public void showListView(ArrayList<SanPham> sanPhamsUpdate);
    public void showDialog();
    public void updateListView();
}
