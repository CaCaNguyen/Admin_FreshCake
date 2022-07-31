package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DangKy;

public class DangKy {
    private int id_dangky;
    private String tenkhachhang;
    private String email;
    private String sdt;

    public DangKy(){
    }

    public DangKy(int id_dangky, String tenkhachhang, String email, String sdt){
        this.id_dangky = id_dangky;
        this.tenkhachhang = tenkhachhang;
        this.email = email;
        this.sdt = sdt;
    }

    public int getId_dangky(){
        return id_dangky;
    }

    public void setId_dangky(int id_dangky){
        this.id_dangky = id_dangky;
    }

    public String getTenkhachhang(){
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang){
        this.tenkhachhang = tenkhachhang;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSdt(){
        return sdt;
    }

    public void setSdt(String sdt){
        this.sdt = sdt;
    }

    @Override
    public String toString(){
        return  id_dangky +
                "-" + tenkhachhang +
                "-" + email +
                "-" + sdt ;
    }
}
