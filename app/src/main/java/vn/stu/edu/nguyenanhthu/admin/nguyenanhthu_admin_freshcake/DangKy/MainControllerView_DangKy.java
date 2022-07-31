package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DangKy;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;

public interface MainControllerView_DangKy {
    public void showMessage(String message);
    public void showListView(ArrayList<DangKy> dangKysUpdate);
    public void showDialog();
    public void updateListView();
}
