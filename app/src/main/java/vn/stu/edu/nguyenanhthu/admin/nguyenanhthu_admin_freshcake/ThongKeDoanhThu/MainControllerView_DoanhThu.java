package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeDoanhThu;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua.ThongKe;

public interface MainControllerView_DoanhThu {
    public void showMessage(String message);
    public void showListView(ArrayList<DoanhThu> thongKeDoanhThusUpdate);
    public void showDialog();
    public void updateListView();
}
