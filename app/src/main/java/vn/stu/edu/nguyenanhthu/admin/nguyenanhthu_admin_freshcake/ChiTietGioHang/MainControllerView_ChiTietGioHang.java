package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang.DonHang;

public interface MainControllerView_ChiTietGioHang {
    public void showMessage(String message);
    public void showListView(ArrayList<ChiTietGioHang> chiTietGioHangsUpdate);
    public void showDialog();
    public void updateListView();
}
