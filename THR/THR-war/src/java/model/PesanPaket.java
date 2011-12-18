package model;


import com.google.gson.Gson;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import util.Database;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PesanPaket {
    private int ido;
    private int idp;
    private int idc;
    private int idi;
    private boolean pay_status;
    private int jumlah_paket;
    private Date order_date;
    private String order_dateS;
    private Date due_date;
    private String customer_name;
    private String paket_name;
    private String item_name;
    private int jumlah_item;
    private int total_pendapatan;
    private  String pay_date;
    private String first_name;
    private int harga_paket;
    private int uang_terbayar;

    public int getUang_terbayar() {
        return uang_terbayar;
    }

    public void setUang_terbayar(int uang_terbayar) {
        this.uang_terbayar = uang_terbayar;
    }


    public int getHarga_paket() {
        return harga_paket;
    }

    public void setHarga_paket(int harga_paket) {
        this.harga_paket = harga_paket;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    private String last_name;

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getJumlah_paket() {
        return jumlah_paket;
    }

    public void setJumlah_paket(int jumlah_paket) {
        this.jumlah_paket = jumlah_paket;
    }

    public Date getOrder_dateD() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getOrder_dateS() throws ParseException {
        return DateFormater.formatDateToView(order_date.toString());
    }

    public void setOrder_dateS(String order_dateS) throws ParseException {
        this.order_dateS = DateFormater.formatDateToDBFormat(order_dateS);
    }

    public boolean isPay_status() {
        return pay_status;
    }
    
   

    public void setPay_status(boolean pay_status) {
        this.pay_status = pay_status;
    }
    
    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPaket_name() {
        return paket_name;
    }

    public void setPaket_name(String paket_name) {
        this.paket_name = paket_name;
    }

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getJumlah_item() {
        return jumlah_item;
    }

    public void setJumlah_item(int jumlah_item) {
        this.jumlah_item = jumlah_item;
    }

    public int getTotal_pendapatan() {
        return total_pendapatan;
    }

    public void setTotal_pendapatan(int total_pendapatan) {
        this.total_pendapatan = total_pendapatan;
    }
    
    public String addPesanPaket() throws ParseException
    {
        Database db = new Database();
        String sql = null;
        PaketJalan paket = new PaketJalan();
        ArrayList<PaketJalan> jalan = paket.getPaket(Integer.toString(this.getIdp()));
        paket = jalan.get(0);
        String due_date = DateFormater.formatDateToCalFormat(paket.getTimeD());
         due_date = DateFormater.nextNDate( due_date,(-1));
         due_date = DateFormater.formatDateToDBFormat( due_date);
        System.out.println( due_date+"/n");
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(Calendar.getInstance().getTime());
            sql="INSERT INTO pesan_paket (idp,idc,jumlah_paket,order_date,due_date,pay_status) VALUES "
                   /*+ "('aa','las','add','phon','emil','pla','00-00-0000','asd')";*/
                    + "("+this.getIdp()+","+this.getIdc()+","+this.getJumlah_paket()+",'"
                    + now +"','"+ due_date+"',"+0+")";
            System.out.println(sql);
            db.setConnection();
            db.updatingQuery(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            db.unsetConnection();
        }
        return sql;
    }
    
    public ArrayList<PesanPaket> getPesanPaketbyIdc(String idc){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql;
        try{
            sql="SELECT * FROM pesan_paket AS pp INNER JOIN paket_jalan AS pj ON pp.idp=pj.idp WHERE idc="+idc;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setDue_date(rs.getDate("due_date"));
                c.setPaket_name(rs.getString("paket_name"));
                c.setPay_date(rs.getString("pay_date"));
                c.setHarga_paket(rs.getInt("total_price"));
                if (rs.getInt("pay_status")==1)
                    c.setPay_status(true);
                else c.setPay_status(false);
                temp.add(c);
                System.out.println(sql);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public String getPesanPaketbyIdc_asJSON(String cu) {
        String json = "";
        ArrayList<PesanPaket> arrayPesan = getPesanPaketbyIdc(cu);
        json = new Gson().toJson(arrayPesan);
        return json;
    }
    
    public ArrayList<PesanPaket> getPesanPaket(String orderby, String s, String end){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql = null;
        String date = null;
        try{
            if(s.equals("")||end.equals("")){
                date = "";
            }else{
                date = " and order_date between '"+s+"' and '"+end+"' ";
            }
            if(orderby.equals("")){
                sql="select ido,pesan_paket.idp,pesan_paket.idc, paket_name, order_date, pay_status, jumlah_paket, (jumlah_paket*total_price) as price "
                        + "from pesan_paket, paket_jalan where pesan_paket.idp=paket_jalan.idp "+ date +" group by ido";
            }else{
                sql="select ido,pesan_paket.idp,pesan_paket.idc, paket_name, order_date, pay_status, jumlah_paket, (jumlah_paket*total_price) as price "
                        + "from pesan_paket, paket_jalan where pesan_paket.idp=paket_jalan.idp "+ date +" group by ido order by "+orderby+ " desc";
            }
            System.out.println(sql);
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setPaket_name(rs.getString("paket_name"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setTotal_pendapatan(rs.getInt("price"));
                if (rs.getInt("pay_status")==1){
                    c.setPay_status(true);
                }else { 
                    c.setPay_status(false); 
                }
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PesanPaket> getPesanPaketbyItem(String orderby, String s, String end){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql = null;
        String date = null;
        try{
            if(s.equals("")||end.equals("")){
                date = "";
            }else{
                date = " and order_date between '"+s+"' and '"+end+"' ";
            }
            if(orderby.equals("")){
                sql="select distinct ido, item_jalan.idi, pesan_paket.idp, item_jalan.name, sum(jumlah_paket) as jumlah "
                        + "from pesan_paket, item_jalan, ip_jalan where pesan_paket.idp=ip_jalan.idp and ip_jalan.idi=item_jalan.idi "+ date +" group by idi";
            }else{
                sql="select distinct ido, item_jalan.idi, pesan_paket.idp, item_jalan.name, sum(jumlah_paket) as jumlah "
                        + "from pesan_paket, item_jalan, ip_jalan where pesan_paket.idp=ip_jalan.idp and ip_jalan.idi=item_jalan.idi "+ date +" group by idi order by "+orderby+ " desc";
            }
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idi"));
                c.setItem_name(rs.getString("name"));
                c.setJumlah_item(rs.getInt("jumlah"));
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PesanPaket> getAllPesanPaket(){
        ResultSet rs;
        ArrayList<PesanPaket> temp =new ArrayList<PesanPaket>();
        String sql;
        try{
            sql="SELECT * FROM pesan_paket AS pp INNER JOIN paket_jalan AS pj ON pp.idp=pj.idp INNER JOIN customer AS c ON c.idc=pp.idc WHERE pay_date IS NOT NULL ORDER BY pay_status,ido DESC";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanPaket c = new PesanPaket();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idc"));
                c.setFirst_name(rs.getString("first_name"));
                c.setLast_name(rs.getString("last_name"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setDue_date(rs.getDate("due_date"));
                c.setPaket_name(rs.getString("paket_name"));
                c.setPay_date(rs.getString("pay_date"));
                c.setHarga_paket(rs.getInt("total_price"));
                c.setUang_terbayar(rs.getInt("uang_pembayaran"));
                if (rs.getInt("pay_status")==1)
                    c.setPay_status(true);
                else c.setPay_status(false);
                temp.add(c);
                System.out.println(c.getIdc());
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public void changePayStatus(String aksi, String ido)
    {
        String sql;
        if(aksi.equals("konfirm"))
        {
            sql = "UPDATE pesan_paket SET pay_status=1 WHERE ido="+ido;
            Database.setConnection();
            Database.updatingQuery(sql);
        }
        else
        {
            sql = "UPDATE pesan_paket SET pay_status=0 WHERE ido="+ido;
            Database.setConnection();
            Database.updatingQuery(sql);
        }
    }
    
    public void confirmPay(String ido, String pay_date, String no_rekening)
    {
        String sql;
        sql = "UPDATE pesan_paket SET pay_date='"+pay_date+"', no_rekening='"+ no_rekening+"' WHERE ido="+ido;
        System.out.println(sql);
            Database.setConnection();
            Database.updatingQuery(sql);
    }
    
    public static void main(String[] args) throws ParseException {
        PesanPaket p = new PesanPaket();
        ArrayList<PesanPaket> ap = new ArrayList<PesanPaket>();
        
        ap = p.getPesanPaketbyItem("","2011-09-23", "2011-09-26");
        System.out.println(ap.size());
        for(int i=0; i<ap.size();i++){
            //System.out.println(ap.get(i).getOrder_dateS());
            //System.out.println(ap.get(i).getPaket_name());        
            System.out.println(ap.get(i).getItem_name());  
        }
        
    }
    
    
    
}


