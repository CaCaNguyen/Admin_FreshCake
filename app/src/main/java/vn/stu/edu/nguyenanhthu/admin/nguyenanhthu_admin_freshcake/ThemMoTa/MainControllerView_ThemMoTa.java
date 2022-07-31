package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemMoTa;

import java.util.ArrayList;

import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc.DanhMuc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc.KichThuoc;
import vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham.SanPham;

public interface MainControllerView_ThemMoTa {
    public void showMessage(String message);

    public void updateUserModel(SanPham sanpham);

    public void showSpinerKichThuoc(ArrayList<KichThuoc> kichThuocArrayList);

    public void showSpinerSanPham(ArrayList<TenSanPham> tenSanPhamArrayList);

    public void showDialog();

    public void updateListView();
}
