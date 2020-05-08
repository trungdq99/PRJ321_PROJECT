/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tblCartDetail;

import java.io.Serializable;

/**
 *
 * @author SE130447
 */
public class TblCartDetailDTO implements Serializable{

    private int cartID;
    private int bookID;
    private int quantity;

    public TblCartDetailDTO() {
    }

    public TblCartDetailDTO(int cartID, int bookID, int quantity) {
        this.cartID = cartID;
        this.bookID = bookID;
        this.quantity = quantity;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
