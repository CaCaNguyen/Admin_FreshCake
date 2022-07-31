package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeMua;

import java.io.Serializable;

public class ThongKe implements Serializable {
    private String tensanpham;
    private int soluong;

    public ThongKe(){
    }

    public ThongKe(String tensanpham, int soluong){
        this.tensanpham = tensanpham;
        this.soluong = soluong;
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

    @Override
    public String toString(){
        return
                tensanpham +
                "/" + soluong;
    }
}
