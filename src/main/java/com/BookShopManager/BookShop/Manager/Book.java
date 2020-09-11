package com.BookShopManager.BookShop.Manager;

public class Book {

    private int BookId;
    private String BookName;
    private String AutherName;
    private int SellingPrice;
    private int CostPrice;
    private int Count;

    public Book(){

    }

    public Book(int bookId, String bookName, String autherName, int SellingPrice,int CostPrice,int count) {
        this.BookId = bookId;
        this.BookName = bookName;
        this.AutherName = autherName;
        this.Count = count;
        this.SellingPrice=SellingPrice;
        this.CostPrice=CostPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public void setCostPrice(int costPrice) {
        CostPrice = costPrice;
    }

    public int getSellingPrice() {
        return SellingPrice;
    }

    public int getCostPrice() {
        return CostPrice;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public void setAutherName(String autherName) {
        AutherName = autherName;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getBookId() {
        return BookId;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAutherName() {
        return AutherName;
    }

    public int getCount() {
        return Count;
    }
}
