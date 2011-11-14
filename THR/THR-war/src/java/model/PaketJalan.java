package model;

//import com.google.gson.Gson;
import com.google.gson.Gson;
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
    private String time2;

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

    public Date getTimeD() {
        return time;
    }

    public String getTime() throws ParseException {
        String s = formatDateToView(time.toString());
        return s;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTime(String time) {
        this.time2 = time;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getPaket_asJSON() {
        String json = "";
        ArrayList<PaketJalan> arrayJalan = getPaketView();
        json = new Gson().toJson(arrayJalan);
        return json;
    }

    public String getPaket_asJSON(String idp) {
        String json = "";
        ArrayList<PaketJalan> arrayJalan = getPaket(idp);
        json = new Gson().toJson(arrayJalan);
        return json;
    }

    public ArrayList<PaketJalan> getPaket() {
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        try {
            sql = "SELECT * FROM paket_jalan";
            Database.setConnection();
            rs = Database.executingQuery(sql);
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
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return temp;
    }

    public ArrayList<PaketJalan> getPaketView() {
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String today = sdf.format(Calendar.getInstance().getTime());
            sql = "SELECT * FROM paket_jalan WHERE time >'" + today + "'";
            System.out.println(sql);
            Database.setConnection();
            rs = Database.executingQuery(sql);
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
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return temp;
    }

    public ArrayList<PaketJalan> getPaket(String idp) {
        Database db = new Database();
        ResultSet rs;
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        String sql;
        try {
            sql = "SELECT * FROM paket_jalan WHERE idp=" + idp;
            Database.setConnection();
            rs = Database.executingQuery(sql);
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
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return temp;
    }

    public ArrayList<PaketJalan> getSearchResult(String mark, String price, String name, int origins, int dests) {
        String query = "SELECT * FROM paket_jalan, item_jalan, ip_jalan WHERE paket_jalan.idp=ip_jalan.idp AND ip_jalan.idi=item_jalan.idi";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
        try {
            String today = sdf.format(Calendar.getInstance().getTime());
            query = query.concat(" AND time >'" + today + "'");
            Database.setConnection();
            if (origins != 0) {
                query = query.concat(" AND origin=" + origins);
            }
            if (dests != 0) {
                query = query.concat(" AND dest=" + dests);
            }
            if (!name.equals("")) {
                query = query.concat(" AND name \"%" + name + "%\"");
            }
            if (!price.equals("") && !mark.equals("")) {
                query = query.concat(" AND total_price" + mark + price);
            }
            System.out.println(query);
            ResultSet rs = Database.executingQuery(query);
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
        } catch (SQLException ex) {
            Logger.getLogger(PaketJalan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }

    public String getSearchInJSON(String mark, String price, String name, String origin, String dest) {
        String query = "SELECT * FROM paket_jalan, item_jalan, ip_jalan WHERE paket_jalan.idp=ip_jalan.idp AND ip_jalan.idi=item_jalan.idi";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String json = new String();
        try {
            String today = sdf.format(Calendar.getInstance().getTime());
            query = query.concat(" AND time >'" + today + "'");
            Database.setConnection();
            int origins = 0;
            int dests = 0;
            if (!origin.equals("")) {
                String querys = "SELECT * FROM city WHERE name like \"%" + origin + "%\"";
                ResultSet rs = Database.executingQuery(querys);
                while (rs.next()) {
                    origins = rs.getInt("idcity");
                }
            }
            if (!dest.equals("")) {
                String querys = "SELECT * FROM city WHERE name like \"%" + dest + "%\"";
                ResultSet rs = Database.executingQuery(querys);
                while (rs.next()) {
                    dests = rs.getInt("idcity");
                }
            }
            if (origins != 0) {
                query = query.concat(" AND origin=" + origins);
            }
            if (dests != 0) {
                query = query.concat(" AND dest=" + dests);
            }
            if (!name.equals("")) {
                query = query.concat(" AND name \"%" + name + "%\"");
            }
            if (!price.equals("") && !mark.equals("")) {
                query = query.concat(" AND total_price" + mark + price);
            }
            ArrayList<PaketJalan> temp = new ArrayList<PaketJalan>();
            ResultSet rs = Database.executingQuery(query);
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
                json = new Gson().toJson(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaketJalan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return json;
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

    public void setPaket(String nama, String desk, String harga, String na, String nc, String t) {
        Database db = new Database();
        String sql;
        try {
            Database.setConnection();
            sql = "INSERT INTO paket_jalan (`paket_name`,`description`,`total_price`, `nadult`, `nchild`, `time`) VALUES('" + nama + "','" + desk + "','" + harga + "','" + na + "','" + nc + "','" + t + "')";
            Database.updatingQuery(sql);
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
    }

    public int lastID() {
        int i = 0;
        Database db = new Database();
        String sql;
        ResultSet rs;
        try {
            Database.setConnection();
            sql = "select idp from paket_jalan order by idp desc limit 1";
            rs = Database.executingQuery(sql);
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return i;
    }

    public String deleteP(String id) {
        Database db = new Database();
        String sql = null;
        try {
            sql = "DELETE FROM paket_jalan WHERE idp='" + id + "'";
            Database.setConnection();
            Database.updatingQuery(sql);
            System.out.println(sql);
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return sql;
    }

    public String updatePaket(String idp, String n, String d, String h, String na, String nc, String t) {
        Database db = new Database();
        String sql = null;
        try {
            Database.setConnection();
            sql = "UPDATE paket_jalan SET  paket_name =  '" + n + "', description = '" + d + "', "
                    + "total_price = '" + h + "', nadult =  '" + na + "', nchild = '" + nc + "', "
                    + "time = '" + t + "' WHERE idp = " + idp;
            System.out.println(sql);
            Database.updatingQuery(sql);
        } catch (Exception e) {
        } finally {
            Database.unsetConnection();
        }
        return sql;
    }

    public PaketJalan getPaketbyid(String id) {
        ResultSet rs;
        PaketJalan pb = new PaketJalan();
        String Sql;
        Sql = "SELECT * FROM paket_jalan WHERE idp='" + id + "'";
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while (rs.next()) {
                pb.setIdp(rs.getInt("idp"));
                pb.setPaket_nama(rs.getString("paket_name"));
                pb.setDescription(rs.getString("description"));
                pb.setTotal_price(rs.getInt("total_price"));
                pb.setNadult(rs.getInt("nadult"));
                pb.setNchild(rs.getInt("nchild"));
                pb.setTime(rs.getDate("time"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pb;
    }

    public static void main(String[] args) throws ParseException {
        PaketJalan pj = new PaketJalan();
        //apj = pj.getPaket("3");
        System.out.println(pj.getSearchInJSON(">", "10000", "", "Jakarta", "Kalianda"));
    }
}
