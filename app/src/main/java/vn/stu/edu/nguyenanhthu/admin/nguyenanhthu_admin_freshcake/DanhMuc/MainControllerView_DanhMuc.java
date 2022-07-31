package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc;

import java.util.ArrayList;

public interface MainControllerView_DanhMuc {
    public void showMessage(String message);
    public void showListView(ArrayList<DanhMuc> danhMucsUpdate);
    public void showDialog();
    public void updateListView();
}
