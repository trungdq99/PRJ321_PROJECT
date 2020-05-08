/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.tblCart;

import java.io.Serializable;

/**
 *
 * @author SE130447
 */
public class TblCartDTO implements Serializable{

    private int cartID;
    private String username;
    private float total;

    public TblCartDTO() {
    }

    public TblCartDTO(int cartID, String username, float total) {
        this.cartID = cartID;
        this.username = username;
        this.total = total;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

}
