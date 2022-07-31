package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;

public interface MainControllerView_DonHang {
    public void showMessage(String message);
    public void showListView(ArrayList<DonHang> donHangsUpdate);
    public void showDialog();
    public void updateListView();
}
