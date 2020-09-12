package com.BookShopManager.BookShop.Manager;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class Controller {

    @Autowired
    bookRepository repository;

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/allbooknames")
    public List<String> BookNames(){
        return repository.getAllBookNames();
    }

    @GetMapping("/allbooks")
    public List Books(){
        return repository.getBooks();
    }
    @PostMapping("/addbook")
    public void addbook(@RequestParam("Id") int Id,@RequestParam("BookName") String BookName,@RequestParam("Auther") String Auther,@RequestParam("sprice") int sprice,@RequestParam("cprice") int cprice,@RequestParam("count") int count){
        repository.addBook(Id,BookName,Auther,sprice,cprice,count);
    }

    @RequestMapping("/deletebook/{BookId}")
    public List deleteBook(@PathVariable int BookId){
        repository.deleteBook(BookId);
        return repository.getBooks();
    }

    @GetMapping("/findbook")
    public List findBook(@RequestParam("BookName") String BookName){
        return repository.findBook(BookName);
    }

    @GetMapping("/addtocart")
    public List addToCart(@RequestParam("BookID") int BookID,@RequestParam("qty") int qty){
        return repository.sellbook(BookID,qty);
    }

    @PostMapping("/Bill")
    public void Billing(@RequestBody List<Map> list){
        repository.addtoProfit(list);
    }
    @GetMapping("/profits")
    public List profits(){
        return repository.profitInfo();
     }

     @GetMapping("/profitbydate")
    public List profitsbyDate(@RequestParam String Date){
        return repository.profitFromDate(Date);
     }

     @PostMapping("/updatestockinfo")
    public void updatestock(@RequestBody List<Map> list){
        Map m=new HashMap();
        m.put("BookID",list.get(0).get("BookID"));
         m.put("BookName",list.get(0).get("BookName"));
         m.put("AutherName",list.get(0).get("AutherName"));
         m.put("SellingPrice",list.get(0).get("SellingPrice"));
         m.put("CostPrice",list.get(0).get("CostPrice"));
         m.put("Count",list.get(0).get("Count"));
        repository.updateStockInfo(Integer.parseInt(list.get(0).get("BookID").toString()),m);
     }

     @GetMapping("/findupdatingbook")
     public List findbook(@RequestParam("BookID")int BookID){

            return repository.findbyId(BookID);

     }
}
