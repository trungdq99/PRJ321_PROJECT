/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tblBook;

import java.io.Serializable;

/**
 *
 * @author SE130447
 */
public class TblBookDTO implements Serializable {

    private int bookID;
    private String title;
    private float price;

    public TblBookDTO() {
    }

    public TblBookDTO(int bookID) {
        this.bookID = bookID;
    }

    public TblBookDTO(int bookID, String title, float price) {
        this.bookID = bookID;
        this.title = title;
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TblBookDTO{" + "bookID=" + bookID + ", title=" + title + ", price=" + price + '}';
    }

    public boolean equals(TblBookDTO dto) {
        return this.bookID == dto.getBookID();
    }

}
