package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa;

import java.util.ArrayList;

public interface MainControllerView_MoTa {
    public void showMessage(String message);
    public void updateUserModel(MoTa mota);
    public void showListView(ArrayList<MoTa> moTasUpdate);
    public void showDialog();
    public void updateListView();
}
