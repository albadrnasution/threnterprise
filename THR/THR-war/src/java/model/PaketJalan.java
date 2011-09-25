package model;


import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import util.Database;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PaketJalan {
    private int idp;
    private String paket_nama;
    private String description;
    private int total_price;
    private int nadult;
    private int nchild;
    private Date time;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getNadult() {
        return nadult;
    }

    public void setNadult(int nadult) {
        this.nadult = nadult;
    }

    public int getNchild() {
        return nchild;
    }

    public void setNchild(int nchild) {
        this.nchild = nchild;
    }

    public String getPaket_nama() {
        return paket_nama;
    }

    public void setPaket_nama(String paket_nama) {
        this.paket_nama = paket_nama;
    }

    public String getTime() throws ParseException {
        String s = formatDateToView(time.toString());
        return s;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
 
    
    public ArrayList<PaketJalan> getPaketP(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        try{
            sql="SELECT * FROM paket_jalan";
            db.setConnection();
            rs = db.executingQuery(sql) ;
            while (rs.next()) {
                PaketJalan pj = new PaketJalan();
                pj.setIdp(rs.getInt("idp"));
                pj.setPaket_nama(rs.getString("paket_name"));
                pj.setDescription(rs.getString("description"));
                pj.setTime(rs.getDate("time"));
                pj.setNadult(rs.getInt("nadult"));
                pj.setNchild(rs.getInt("nchild"));
                pj.setTotal_price(rs.getInt("total_price"));
                temp.add(pj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PaketJalan> getPaketP(int idp){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        try{
            sql="SELECT * FROM paket_jalan WHERE idp="+idp;
            db.setConnection();
            rs = db.executingQuery(sql) ;
            while (rs.next()) {
                PaketJalan pj = new PaketJalan();
                pj.setIdp(rs.getInt("idp"));
                pj.setPaket_nama(rs.getString("paket_name"));
                pj.setDescription(rs.getString("description"));
                pj.setTime(rs.getDate("time"));
                pj.setNadult(rs.getInt("nadult"));
                pj.setNchild(rs.getInt("nchild"));
                pj.setTotal_price(rs.getInt("total_price"));
                temp.add(pj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return temp ;
    }
     
    
    public String formatDateToView(String StrDate) throws ParseException {
        SimpleDateFormat formaterD = new SimpleDateFormat("yyyy-M-d");
        Date result = formaterD.parse(StrDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        int Day = cal.get(Calendar.DAY_OF_WEEK);
        int Month = cal.get(Calendar.MONTH);
        int Year = cal.get(Calendar.YEAR);
        int tanggal = cal.get(Calendar.DAY_OF_MONTH);
        StringBuilder Sb = new StringBuilder();
        StringBuilder append = Sb.append(DayOfTheWeek(Day)).append(", ").append(tanggal).append(" ").append(MonthOfTheYear(Month)).append(" ").append(Year);
        return append.toString();
    }

    /**
     * Get the day of the date
     * @param StrDate
     * @return
     * @throws ParseException 
     */
    public String getDayfromDate(String StrDate) throws ParseException {
        SimpleDateFormat formaterD = new SimpleDateFormat("dd-MM-yyyy ");
        Date result = formaterD.parse(StrDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        int Day = cal.get(Calendar.DAY_OF_WEEK);
        return DayOfTheWeek(Day);
    }

    /**
     * Mengambil Hari dalam satu minggu
     * @param i
     * @return 
     */
    public String DayOfTheWeek(int i) {
        String Day = new String();
        if (i == 1) {
            Day = "Minggu";
        } else if (i == 2) {
            Day = "Senin";
        } else if (i == 3) {
            Day = "Selasa";
        } else if (i == 4) {
            Day = "Rabu";
        } else if (i == 5) {
            Day = "Kamis";
        } else if (i == 6) {
            Day = "Jumat";
        } else if (i == 7) {
            Day = "Sabtu";
        }

        return Day;
    }

    /**
     * Mengembalikan String bulan dalam bahasa indonesia
     * @param i
     * @return 
     */
    public String MonthOfTheYear(int i) {
        String Month = new String();
        if (i == 0) {
            Month = "Januari";
        } else if (i == 1) {
            Month = "Februari";
        } else if (i == 2) {
            Month = "Maret";
        } else if (i == 3) {
            Month = "April";
        } else if (i == 4) {
            Month = "Mei";
        } else if (i == 5) {
            Month = "Juni";
        } else if (i == 6) {
            Month = "Juli";
        } else if (i == 7) {
            Month = "Agustus";
        } else if (i == 8) {
            Month = "September";
        } else if (i == 9) {
            Month = "Oktober";
        } else if (i == 10) {
            Month = "November";
        } else if (i == 11) {
            Month = "Desember";
        }

        return Month;
    }
    
    public static void main(String[] args) throws ParseException {
        PaketJalan pj = new PaketJalan();
        ArrayList<PaketJalan> apj = new ArrayList<PaketJalan>();
        apj = pj.getPaketP();
        
        for(int i=0;i<apj.size();++i){
            System.out.println(apj.get(i).getTime());
        }
    }
    
    
}
