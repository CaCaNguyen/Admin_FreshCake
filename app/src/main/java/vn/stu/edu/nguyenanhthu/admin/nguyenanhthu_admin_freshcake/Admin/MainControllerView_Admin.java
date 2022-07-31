package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;

public interface MainControllerView_Admin {
    public void showMessage(String message);
    public void showListView(ArrayList<Admin> adminsUpdate);
    public void showDialog();
    public void updateListView();
}
