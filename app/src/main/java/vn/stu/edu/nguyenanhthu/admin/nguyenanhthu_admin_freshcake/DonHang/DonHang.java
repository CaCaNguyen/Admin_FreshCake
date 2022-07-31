package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DonHang;

import java.io.Serializable;
import java.util.Date;

public class DonHang implements Serializable {
    private int id_giohang;
    private String tenkhachhang;
    private String trangthai;
    private String ngaylap;
    private String ngaynhan;

    public DonHang(){
    }

    public DonHang(int id_giohang, String tenkhachhang, String trangthai, String ngaylap, String ngaynhan){
        this.id_giohang = id_giohang;
        this.tenkhachhang = tenkhachhang;
        this.trangthai = trangthai;
        this.ngaylap = ngaylap;
        this.ngaynhan = ngaynhan;
    }

    public int getId_giohang(){
        return id_giohang;
    }

    public void setId_giohang(int id_giohang){
        this.id_giohang = id_giohang;
    }

    public String getTenkhachhang(){
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang){
        this.tenkhachhang = tenkhachhang;
    }

    public String getTrangthai(){
        return trangthai;
    }

    public void setTrangthai(String trangthai){
        this.trangthai = trangthai;
    }

    public String getNgaylap(){
        return ngaylap;
    }

    public void setNgaylap(String ngaylap){
        this.ngaylap = ngaylap;
    }

    public String getNgaynhan(){
        return ngaynhan;
    }

    public void setNgaynhan(String ngaynhan){
        this.ngaynhan = ngaynhan;
    }

    @Override
    public String toString(){
        return  id_giohang +
                "-" + tenkhachhang +
                "-" + trangthai +
                "-" + ngaylap +
                "-" + ngaynhan;
    }
}
