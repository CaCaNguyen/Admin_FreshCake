package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.SanPham;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id_sanpham;
    private String tensanpham;
    private String masp;
    private int soluong;
    private String hinhanh;
    private String noidung;
    private String tendanhmuc;

    public SanPham(){
    }

    public SanPham(int id_sanpham, String tensanpham, String masp, int soluong, String hinhanh, String noidung, String tendanhmuc){
        this.id_sanpham = id_sanpham;
        this.tensanpham = tensanpham;
        this.masp = masp;
        this.soluong = soluong;
        this.hinhanh = hinhanh;
        this.noidung = noidung;
        this.tendanhmuc = tendanhmuc;
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

    public String getMasp(){
        return masp;
    }

    public void setMasp(String masp){
        this.masp = masp;
    }

    public int getSoluong(){
        return soluong;
    }

    public void setSoluong(int soluong){
        this.soluong = soluong;
    }

    public String getHinhanh(){
        return hinhanh;
    }

    public void setHinhanh(String hinhanh){
        this.hinhanh = hinhanh;
    }

    public String getNoidung(){
        return noidung;
    }

    public void setNoidung(String noidung){
        this.noidung = noidung;
    }

    public String getTendanhmuc(){
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc){
        this.tendanhmuc = tendanhmuc;
    }

    @Override
    public String toString(){
        return
                "\n" + id_sanpham +
                        "\n" + tensanpham +
                        "\n" + masp +
                        "\n" + soluong +
                        "\n" + hinhanh +
                        "\n" + noidung +
                        "\n" + tendanhmuc;
    }
}
