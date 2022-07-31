package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThemMoTa;

import java.io.Serializable;

public class TenSanPham implements Serializable {
    private int id_sanpham;
    private String tensanpham;

    public TenSanPham(){
    }

    public TenSanPham(int id_sanpham, String tensanpham){
        this.id_sanpham = id_sanpham;
        this.tensanpham = tensanpham;
    }

    public int getId_sanpham(){
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham){
        this.id_sanpham = id_sanpham;
    }

    public String getTensanpham(){
        return tensanpham;
    }

    public void setTensanpham(String tensanpham){
        this.tensanpham = tensanpham;
    }

    @Override
    public String toString(){
        return id_sanpham +
                "-" + tensanpham ;
    }
}
