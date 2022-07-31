package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin.Admin;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;

public interface MainControllerView_About {
    public void showMessage(String message);
    public void showListView(ArrayList<About> aboutsUpdate);
    public void showDialog();
    public void updateListView();
}
