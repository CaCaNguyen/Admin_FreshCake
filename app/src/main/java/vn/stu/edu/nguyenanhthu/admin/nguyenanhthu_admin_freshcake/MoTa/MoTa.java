package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.MoTa;

import java.io.Serializable;

public class MoTa implements Serializable {
    private int id_mota;
    private String tensanpham;
    private String size;
    private Double giasp;

    public MoTa(){
    }

    public MoTa(int id_mota,String tensanpham, String size, Double giasp){
        this.id_mota = id_mota;
        this.tensanpham = tensanpham;
        this.size = size;
        this.giasp = giasp;
    }

    public int getId_mota(){
        return id_mota;
    }

    public void setId_mota(int id_mota){
        this.id_mota = id_mota;
    }

    public String getTensanpham(){
        return tensanpham;
    }

    public void setTensanpham(String tensanpham){
        this.tensanpham = tensanpham;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    public Double getGiasp(){
        return giasp;
    }

    public void setGiasp(Double giasp){
        this.giasp = giasp;
    }

    @Override
    public String toString(){
        return id_mota +
                "-" + tensanpham + '\'' +
                "-" + size + '\'' +
                "-" + giasp ;
    }
}
