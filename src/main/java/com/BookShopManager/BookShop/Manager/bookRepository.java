package com.BookShopManager.BookShop.Manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;


@Repository
public class bookRepository {

    @Autowired
    JdbcTemplate jdbc;

    public List<String> getAllBookNames(){
        List<String> list=new ArrayList();
        list.addAll(jdbc.queryForList("Select BookName from books",String.class));

        return list;
    }

    public void addBook(int BookId,String BookName,String AutherName,int sprice,int cprice,int Count){
        jdbc.update("insert into books (BookId,BookName,AutherName,SellingPrice,CostPrice,Count) VALUES ("+BookId+",'"+BookName+"','"+AutherName+"',"+sprice+","+cprice+","+Count+")");

    }

    public List getBooks(){
        List<Object> l=new ArrayList<>();
        l.addAll(jdbc.queryForList("Select * from Books"));
        return l;
    }

    public List findBook(String Name){
        List<Object> l=new ArrayList();
        if(Name.equals("")){
            l.addAll(jdbc.queryForList("select * from books"));
        }else{
            l.addAll(jdbc.queryForList("select * from books where BookName LIKE '%"+Name+"%'"));
        }
        return l;
    }

    public void deleteBook(int BookID){
        jdbc.execute("Delete from books where BookID="+BookID+"");
    }

    public List sellbook(int BookID,int qty){
         String name=jdbc.queryForObject("select BookName from books where BookId="+BookID+"",String.class);
        int sprice=jdbc.queryForObject("select SellingPrice from books where BookId="+BookID+"",Integer.class);
        int fullAmount=sprice*qty;

        //calender date
        Calendar c=new GregorianCalendar();
        String date=""+c.get(c.YEAR)+"/"+(1+c.get(c.MONTH))+"/"+c.get(c.DAY_OF_MONTH);

        List list=new ArrayList();
        Map m=new HashMap();
        m.put("BookID",BookID);
        m.put("BookName",name);
        m.put("SellingPrice",sprice);
        m.put("qty",qty);
        m.put("date",date);
        m.put("amount",fullAmount);

        list.add(m);

        return list;
    }

    public void addtoProfit(List<Map> list){
        for(int i=0;i<list.size();i++){
            int costPrice=jdbc.queryForObject("Select CostPrice from books where BookID='"+list.get(i).get("BookID")+"'",Integer.class);
            int Count=jdbc.queryForObject("Select Count from books where BookID='"+list.get(i).get("BookID")+"'",Integer.class);
            int qty=Integer.parseInt(list.get(i).get("qty").toString());

            int spentMoney=costPrice*qty;
            int amount=Integer.parseInt(list.get(i).get("amount").toString());

            int restcount=Count-qty;
            jdbc.update("UPDATE books SET Count="+restcount+" WHERE BookID='"+list.get(i).get("BookID")+"'");

            int profit=amount-spentMoney;
            jdbc.execute("insert into profitsManager(Date,ID,QTY,Amount,Profit) values ('"+list.get(i).get("date")+"',"+list.get(i).get("BookID")+","+qty+","+amount+","+profit+")");

        }
    }

    public List profitInfo(){
        return jdbc.queryForList("Select * from profitsManager");
    }
    public List profitFromDate(String Date){
        if(Date.equals("")){
            return jdbc.queryForList("Select * from profitsManager");
        }else {
            return jdbc.queryForList("Select * from profitsManager where Date like '%" + Date + "%'");
        }
    }

    public void updateStockInfo(int BookID, Map list){
        jdbc.update("Update books set BookID='"+list.get("BookID")+"',BookName='"+list.get("BookName")+"',AutherName='"+list.get("AutherName")+"',SellingPrice='"+list.get("SellingPrice")+"',CostPrice='"+list.get("CostPrice")+"',Count='"+list.get("Count")+"' where BookID='"+BookID+"'");
        
    }

    public List findbyId(int BookID){
        if(BookID!=0){
            return jdbc.queryForList("Select * from books where BookID="+BookID+"");
        }else{
            return null;
        }


    }

}
