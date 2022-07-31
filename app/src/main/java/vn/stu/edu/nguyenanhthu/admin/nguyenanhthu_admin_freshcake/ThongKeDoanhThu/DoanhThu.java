package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.ThongKeDoanhThu;

public class DoanhThu {
    private int thang;
    private int nam;
    private Double tien;

    public DoanhThu(){
    }

    public int getThang(){
        return thang;
    }

    public void setThang(int thang){
        this.thang = thang;
    }

    public int getNam(){
        return nam;
    }

    public void setNam(int nam){
        this.nam = nam;
    }

    public Double getTien(){
        return tien;
    }

    public void setTien(Double tien){
        this.tien = tien;
    }

    public DoanhThu(int thang, int nam, Double tien){
        this.thang = thang;
        this.nam = nam;
        this.tien = tien;
    }
}
