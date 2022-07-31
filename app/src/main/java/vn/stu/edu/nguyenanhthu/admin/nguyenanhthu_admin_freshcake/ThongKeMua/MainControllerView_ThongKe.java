package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua;

import java.util.ArrayList;

public interface MainControllerView_ThongKe {
    public void showMessage(String message);
    public void showListView(ArrayList<ThongKe> thongKeMuasUpdate);
    public void showDialog();
    public void updateListView();
}
