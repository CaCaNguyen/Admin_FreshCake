package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.KichThuoc;

import java.io.Serializable;

public class KichThuoc implements Serializable {
    private int id_size;
    private String size;

    public KichThuoc(){
    }

    public KichThuoc(int id_size, String size){
        this.id_size = id_size;
        this.size = size;
    }

    public int getId_size(){
        return id_size;
    }

    public void setId_size(int id_size){
        this.id_size = id_size;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    @Override
    public String toString(){
        return id_size +
                "-" + size;
    }
}
