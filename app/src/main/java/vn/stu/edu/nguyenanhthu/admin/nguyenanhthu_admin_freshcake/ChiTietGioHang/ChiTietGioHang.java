package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ChiTietGioHang;

import java.io.Serializable;

public class ChiTietGioHang implements Serializable {
    private int id_chitietgiohang;
    private int magiohang;
    private String tensanpham;
    private int soluong;
    private String size;

    public ChiTietGioHang(){
    }

    public ChiTietGioHang(int id_chitietgiohang, int magiohang, String tensanpham, int soluong, String size){
        this.id_chitietgiohang = id_chitietgiohang;
        this.magiohang = magiohang;
        this.tensanpham = tensanpham;
        this.soluong = soluong;
        this.size = size;
    }

    public int getId_chitietgiohang(){
        return id_chitietgiohang;
    }

    public void setId_chitietgiohang(int id_chitietgiohang){
        this.id_chitietgiohang = id_chitietgiohang;
    }

    public int getMagiohang(){
        return magiohang;
    }

    public void setMagiohang(int magiohang){
        this.magiohang = magiohang;
    }

    public String getTensanpham(){
        return tensanpham;
    }

    public void setTensanpham(String tensanpham){
        this.tensanpham = tensanpham;
    }

    public int getSoluong(){
        return soluong;
    }

    public void setSoluong(int soluong){
        this.soluong = soluong;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    @Override
    public String toString(){
        return   id_chitietgiohang +
                "-" + magiohang +
                "-" + tensanpham +
                "-" + soluong +
                "-'" + size ;
    }
}

