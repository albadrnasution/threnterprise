package model;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import util.Database;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Didik
 */
public class PesanBingkisan {
    private int ido;
    private int idp;
    private int idc;
    private int idi;
    private boolean pay_status;
    private int jumlah_paket;
    private Date order_date;
    private String order_dateS;
    private Date due_date;
    private String due_date2;
    private String paket_name;
    private int total_pendapatan;
    private String item_name;
    private int jumlah_item;
    private String pay_date;
    private String first_name;
    private String last_name;
    private int harga_paket;
    private int uang_terbayar;
    

    public String getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(String no_rekening) {
        this.no_rekening = no_rekening;
    }
    private String no_rekening;

    public int getHarga_paket() {
        return harga_paket;
    }

    public void setHarga_paket(int harga_paket) {
        this.harga_paket = harga_paket;
    }

    public int getUang_terbayar() {
        return uang_terbayar;
    }

    public void setUang_terbayar(int uang_terbayar) {
        this.uang_terbayar = uang_terbayar;
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
    
    public void setDue_date2(String due_date) {
        this.due_date2 = due_date;
    }
    
    public String getDue_date2() {
        return due_date2 ;
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

    public Date getOrder_date() {
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

    public int getIdi() {
        return idi;
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public String getPaket_name() {
        return paket_name;
    }

    public void setPaket_name(String paket_name) {
        this.paket_name = paket_name;
    }

    public int getTotal_pendapatan() {
        return total_pendapatan;
    }

    public void setTotal_pendapatan(int total_pendapatan) {
        this.total_pendapatan = total_pendapatan;
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
    
    
    
    public String addPesanBingkisan() throws ParseException
    {
        Database db = new Database();
        String sql = null;
        String due_date = DateFormater.formatDateToCalFormat(this.getDue_date2());
        due_date = DateFormater.nextNDate(due_date,(-1));
        due_date = DateFormater.formatDateToDBFormat(due_date);
        try{
            sql= "INSERT INTO pesan_bingkisan (idp,idc,jumlah_paket,order_date,due_date,pay_status) VALUES "
                   
                    
                    + "("+this.getIdp()+","+this.getIdc() + ","+this.getJumlah_paket()+","
                    + "NOW()" +",'"+due_date+"',"+0+")";
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
    
    public int lastPesanBingkisan()
    {
        ResultSet rs;
        PesanBingkisan c = new PesanBingkisan();
        String Sql;
        Sql="SELECT MAX(ido) AS max FROM pesan_bingkisan ";
        int result = 0;
        Database.setConnection();
        rs = Database.executingQuery(Sql);
        try {
            while(rs.next()){
                result = rs.getInt("max");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ArrayList<PesanBingkisan> getPesanBingkisanbyIdc(String idc){
        ResultSet rs;
        ArrayList<PesanBingkisan> temp =new ArrayList<PesanBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM pesan_bingkisan AS pp INNER JOIN paket_bingkisan AS pb ON pp.idp=pb.idp WHERE idc="+idc;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                
                PesanBingkisan c = new PesanBingkisan();
                c.setIdo(rs.getInt("ido"));
              c.setIdp(rs.getInt("idp"));
               c.setIdc(rs.getInt("idc"));
                  c.setDue_date2(rs.getString("due_date"));
                //c.setOrder_dateS(rs.getString("order_date"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setPay_date(rs.getString("pay_date"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setPaket_name(rs.getString("paket_name"));
                c.setHarga_paket(rs.getInt("price"));
//                System.out.println(rs.getString("paket_name"));
//                System.out.println(rs.getString("pay_date"));
//                System.out.println(rs.getDate("pay_date"));
                if (rs.getInt("pay_status")==1)
                    c.setPay_status(true);
                else c.setPay_status(false);
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public String getPesanBingkisanbyIdc_asJSON(String cu) {
        String json = "";
        ArrayList<PesanBingkisan> arrayPesan = getPesanBingkisanbyIdc(cu);
        json = new Gson().toJson(arrayPesan);
        return json;
    }
    
    public ArrayList<PesanBingkisan> getAllPesanBingkisan(){
        ResultSet rs;
        int i =0;
        ArrayList<PesanBingkisan> temp =new ArrayList<PesanBingkisan>();
        String sql;
        try{
            sql="SELECT * FROM pesan_bingkisan AS pp INNER JOIN paket_bingkisan AS pb ON pp.idp=pb.idp INNER JOIN customer AS c ON c.idc=pp.idc WHERE pay_date IS NOT NULL ORDER BY pay_status,ido DESC";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                i++;
                PesanBingkisan c = new PesanBingkisan();
                c.setIdo(rs.getInt("ido"));
              c.setIdp(rs.getInt("idp"));
               c.setIdc(rs.getInt("idc"));
                  c.setDue_date2(rs.getString("due_date"));
                  c.setFirst_name(rs.getString("first_name"));
                  c.setLast_name(rs.getString("last_name"));
                //c.setOrder_dateS(rs.getString("order_date"));
                c.setOrder_date(rs.getDate("order_date"));
                c.setPay_date(rs.getString("pay_date"));
                c.setJumlah_paket(rs.getInt("jumlah_paket"));
                c.setPaket_name(rs.getString("paket_name"));
                c.setHarga_paket(rs.getInt("price"));
                c.setUang_terbayar(rs.getInt("uang_pembayaran"));
                c.setNo_rekening(rs.getString("no_rekening"));
//                System.out.println(rs.getString("paket_name"));
//                System.out.println(rs.getString("pay_date"));
//                System.out.println(rs.getDate("pay_date"));
                if (rs.getInt("pay_status")==1)
                    c.setPay_status(true);
                else c.setPay_status(false);
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public ArrayList<PesanBingkisan> getPesanBingkisan(String orderby, String s, String end){
        ResultSet rs;
        ArrayList<PesanBingkisan> temp =new ArrayList<PesanBingkisan>();
        String sql = null; 
        String date = null;
        try{
            if(s.equals("")||end.equals("")){
                date = "";
            }else{
                date = " and order_date between '"+s+"' and '"+end+"' ";
            }
            if(orderby.equals("")){
                sql="select ido,pesan_bingkisan.idp,pesan_bingkisan.idc, paket_name, order_date, pay_status, jumlah_paket, (jumlah_paket*price) as price "
                        + "from pesan_bingkisan, paket_bingkisan where pesan_bingkisan.idp=paket_bingkisan.idp "+ date +" group by ido";
            }else{
                sql="select ido,pesan_bingkisan.idp,pesan_bingkisan.idc, paket_name, order_date, pay_status, jumlah_paket, (jumlah_paket*price) as price "
                        + "from pesan_bingkisan, paket_bingkisan where pesan_bingkisan.idp=paket_bingkisan.idp "+ date +" group by ido order by "+orderby+ " desc";
            }
            System.out.println(sql);
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanBingkisan c = new PesanBingkisan();
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
    
    public ArrayList<PesanBingkisan> getPesanBingkisanbyItem(String orderby, String s, String end){
        ResultSet rs;
        ArrayList<PesanBingkisan> temp =new ArrayList<PesanBingkisan>();
        String sql = null;
        String date = null;
        try{
            if(s.equals("")||end.equals("")){
                date = "";
            }else{
                date = " and order_date between '"+s+"' and '"+end+"' ";
            }
            if(orderby.equals("")){
                sql="select distinct ido, item_bingkisan.idi, pesan_bingkisan.idp, item_bingkisan.name, sum(nitem*jumlah_paket) as jumlah,"
                        + " sum(nitem*jumlah_paket*basic_price) as harga from pesan_bingkisan, item_bingkisan, ip_bingkisan "
                        + "where pesan_bingkisan.idp=ip_bingkisan.idp and ip_bingkisan.idi=item_bingkisan.idi "+ date +" group by idi";
            }else{
                sql="select distinct ido, item_bingkisan.idi, pesan_bingkisan.idp, item_bingkisan.name, sum(nitem*jumlah_paket) as jumlah,"
                        + " sum(nitem*jumlah_paket*basic_price) as harga from pesan_bingkisan, item_bingkisan, ip_bingkisan "
                        + "where pesan_bingkisan.idp=ip_bingkisan.idp and ip_bingkisan.idi=item_bingkisan.idi "+ date +" group by idi order by "+orderby+ " desc";
            }
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                PesanBingkisan c = new PesanBingkisan();
                c.setIdo(rs.getInt("ido"));
                c.setIdp(rs.getInt("idp"));
                c.setIdc(rs.getInt("idi"));
                c.setItem_name(rs.getString("name"));
                c.setJumlah_item(rs.getInt("jumlah"));
                c.setTotal_pendapatan(rs.getInt("harga"));
                temp.add(c);
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        return temp ;
    }
    
    public void confirmPay(String ido, String pay_date, String no_rekening, String uang_pembayaran)
    {
        String sql;
        sql = "UPDATE pesan_bingkisan SET pay_date='"+pay_date+"', no_rekening='"+ no_rekening +"', uang_pembayaran="+ uang_pembayaran +" WHERE ido="+ido;
        System.out.println(sql);    
        Database.setConnection();
            Database.updatingQuery(sql);
    }
    
    public void changePayStatus(String aksi, String ido)
    {
        String sql;
        if(aksi.equals("konfirm"))
        {
            sql = "UPDATE pesan_bingkisan SET pay_status=1 WHERE ido="+ido;
            Database.setConnection();
            Database.updatingQuery(sql);
        }
        else
        {
            sql = "UPDATE pesan_bingkisan SET pay_status=0 WHERE ido="+ido;
            Database.setConnection();
            Database.updatingQuery(sql);
        }
    }
    
    class Barang {
        public int barang_id;
        public int jumlah;

        @Override
        public String toString() {
            return "id : " + barang_id + ", jumlah : " + jumlah;
        }
    }
    
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

        @Override
		public boolean shouldSkipField(FieldAttributes f) {
			return f.getName().equals("deskripsi") || f.getName().equals("harga_satuan") || f.getName().equals("kategori_id") || f.getName().equals("nama");
		}
    }).create();
    
    
    
    public void sendwebservice(String ido) throws IOException, SQLException {
        String sql;
        int i =0;
        ResultSet rs;
        String server = null ;
        String port = null ;
        String username = null ;
        String pass = null ;
        try{
            sql="SELECT * FROM `webservice`";
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            rs.next();
                i++;
                server = rs.getString("server");
                port = rs.getString("port");
                username = rs.getString("username");
                pass = rs.getString("password");
//                System.out.println(rs.getString("paket_name"));
//                System.out.println(rs.getString("pay_date"));
//                System.out.println(rs.getDate("pay_date"));
            }
        catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        
//        server = "localhost";
//                port = "8080";
//                username = "thr";
//                pass = "thr";
        String baseUrl = "http://"+ server +":"+port+"/Tempe/service/?operation=";
        String urlPostfix = "pesanan.add";
        String userName = "&username="+username;
        String password = "&password="+pass;
        String usePoint = "&use_point=0";
        System.out.println(baseUrl + urlPostfix + userName + password);
        ArrayList<Barang> itemsCart = new ArrayList<Barang>();     
        
        i =0;
        
        try{
            sql="SELECT * FROM `pesan_bingkisan` AS p INNER JOIN ip_bingkisan AS ip ON p.idp=ip.idp WHERE p.ido="+ido;
            Database.setConnection();
            rs = Database.executingQuery(sql) ;
            while (rs.next()) {
                i++;
                Barang barang = new Barang();
                barang.barang_id = rs.getInt("idi");
                barang.jumlah = rs.getInt("nitem");
                System.out.println(barang.toString());
                itemsCart.add(barang);
//                System.out.println(rs.getString("paket_name"));
//                System.out.println(rs.getString("pay_date"));
//                System.out.println(rs.getDate("pay_date"));
            }
        }catch(Exception e){
        }
        finally{
            Database.unsetConnection();
        }
        String jsonPesanan = gson.toJson(itemsCart);
        System.out.println(jsonPesanan);
        StringBuilder builder = new StringBuilder();
        HttpClient httpclient = new DefaultHttpClient();
        String responseString = "Gagal ini";
        HttpPost httppost = new HttpPost(baseUrl + urlPostfix + userName + password);
        
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>(1);  
            postParameters.add(new BasicNameValuePair("data", jsonPesanan)); 
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));
            
            
            
	        // Execute HTTP Post Request
	    	HttpResponse response = httpclient.execute(httppost);  
	        StatusLine statusLine = response.getStatusLine();
        
	}
    
    
    
    public static void main(String[] args) {
        PesanBingkisan p = new PesanBingkisan();
        ArrayList<PesanBingkisan> ap = new ArrayList<PesanBingkisan>();
        
        //ap = p.getPesanBingkisanbyItem("");
        p.getPesanBingkisanbyIdc("1");
        ap = p.getPesanBingkisanbyItem("", "2011-09-23", "2011-09-26");
        for(int i=0; i<ap.size();i++){
            //System.out.println(ap.get(i).getOrder_dateS());
            //System.out.println(ap.get(i).getPaket_name());                        
            System.out.println(ap.get(i).getItem_name());
        }
    }
    
    
}
