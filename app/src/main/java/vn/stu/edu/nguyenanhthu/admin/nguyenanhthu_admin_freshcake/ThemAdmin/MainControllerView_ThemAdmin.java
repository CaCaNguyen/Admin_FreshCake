package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemAdmin;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin.Admin;

public interface MainControllerView_ThemAdmin {
    public void showMessage(String message);
    public void showListView(ArrayList<Admin> AsUpdate);
    public void showDialog();
    public void updateListView();
}
