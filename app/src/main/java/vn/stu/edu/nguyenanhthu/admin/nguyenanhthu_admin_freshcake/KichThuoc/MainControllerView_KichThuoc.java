package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;

public interface MainControllerView_KichThuoc {
    public void showMessage(String message);
    public void showListView(ArrayList<KichThuoc> kichThuocsUpdate);
    public void showDialog();
    public void updateListView();
}
