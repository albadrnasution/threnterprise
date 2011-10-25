package model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 
    
    public ArrayList<PaketJalan> getPaket(){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        try{
            sql="SELECT * FROM paket_jalan";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
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
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PaketJalan> getPaket(String idp){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        try{
            sql="SELECT * FROM paket_jalan WHERE idp="+idp;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
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
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    
    public ArrayList<PaketJalan> getSearchResult(String h, String op, String n, String d){
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql = null;
        String harga = null,nama = null;
        String con;
        try{
            if(h.equals("") ||  op.equals("")){
                harga = "";
            }else if(!h.equals("") &&  !op.equals("")){
                if(n.equals("")){
                    harga = " WHERE total_price " + op + " '" + h + "'";
                }else if(d.equals("")){
                    harga = " WHERE total_price " + op + " '" + h + "'";
                }else{
                    harga = " WHERE total_price " + op + " '" + h + "'";
                }
            }
            if(n.equals("") &&  d.equals("")){
                nama = "";
            }else if(!n.equals("") ||  !d.equals("")){
                if(h.equals("") ||  op.equals("")){
                    con = " WHERE";
                }else{ 
                    con = " AND";
                }
                if(n.equals("")){
                    nama = con + " description like \"%"+ d +"%\"";
                }else if(d.equals("")){
                    nama = con + " paket_name like \"%"+ n +"%\"";
                }else{
                    nama = con + " paket_name like \"%"+ n +"%\" or description like \"%"+ d +"%\"";
                }
            }
            if(!h.equals("") &&  !op.equals("")){
                if(!n.equals("") ||  !d.equals("")){
                    sql="SELECT * FROM paket_jalan" + harga + nama;
                }else{
                    sql="SELECT * FROM paket_jalan" + harga;
                }
            }else{
                sql="SELECT * FROM paket_jalan" + nama;
            }
            System.out.println(sql);
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
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
        }
        finally{
            Database.unsetConnection();
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
    
        public void setPaket(String nama, String desk, String harga, String na, String nc, String t){
        Database db = new Database();
        String sql;
        try{
            Database.setConnection();
            sql = "INSERT INTO paket_jalan (`paket_name`,`description`,`total_price`, `nadult`, `nchild`, `time`) VALUES('"+nama+"','"+desk+"','"+harga+"','"+na+"','"+nc+"','"+t+"')";
            Database.updatingQuery(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
    }
    
    public int lastID(){
        int i = 0;
        Database db = new Database();
        String sql;
        ResultSet rs;
        try {
            Database.setConnection();
            sql = "select idp from paket_jalan order by idp desc limit 1";
            rs = Database.executingQuery(sql);
            if(rs.next()){
                i = rs.getInt(1);
            }
        }catch(Exception e){}
        finally{
            Database.unsetConnection();
        }
        return i;
    }
    
    public String deleteP(String id){
        Database db = new Database();
        String sql = null;
        try{
            sql="DELETE FROM paket_jalan WHERE idp='"+id+"'";
            Database.setConnection();
            Database.updatingQuery(sql);
            System.out.println(sql);
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return sql;
    }
    
    public PaketJalan getPaketbyid(String id){
        ResultSet rs;
        PaketJalan pb = new PaketJalan();
        String Sql;
        Sql="SELECT * FROM paket_jalan WHERE idp='"+id+"'";
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while(rs.next()){
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_nama(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setTotal_price(rs.getInt("price"));
                pb.setNadult(rs.getInt("nadult"));
                pb.setNchild(rs.getInt("nchild"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pb;
    }
    
    public static void main(String[] args) throws ParseException {
        PaketJalan pj = new PaketJalan();
        ArrayList<PaketJalan> apj = new ArrayList<PaketJalan>();
        //apj = pj.getSearchResult("", "", "", "tiket");
        apj = pj.getPaket("3");
        
        for(int i=0;i<apj.size();++i){
            System.out.println(apj.get(i).getPaket_nama());
        }
    }
    
    
}
