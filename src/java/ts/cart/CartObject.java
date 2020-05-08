/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ts.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import ts.tblBook.TblBookDTO;

/**
 *
 * @author SE130447
 */
public class CartObject implements Serializable {

    private Map<TblBookDTO, Integer> books;

    public Map<TblBookDTO, Integer> getBooks() {
        return books;
    }

    private float total;

    public float getTotal() {
        float total = 0;
        for (TblBookDTO dto : books.keySet()) {
            total += dto.getPrice() * books.get(dto);
        }
        return total;
    }

    public void addItemToCart(TblBookDTO book) {
        if (book == null) {
            return;
        }
        if (this.books == null) {
            this.books = new HashMap<>();
        }
        int quantity = 1;
        for (TblBookDTO dto : books.keySet()) {
            if (dto.equals(book)) {
                quantity = this.books.get(dto) + 1;
                this.books.put(dto, quantity);
                return;
            }
        }
        this.books.put(book, quantity);
    }

    public void updateQuantity(TblBookDTO book, int quantity) {
        if (book == null) {
            return;
        }
        for (TblBookDTO dto : books.keySet()) {
            if (dto.equals(book)) {
                this.books.put(dto, quantity);
                return;
            }
        }
    }

    public void removeItem(TblBookDTO book) {
        if (book == null) {
            return;
        }
        for (TblBookDTO dto : books.keySet()) {
            if (dto.equals(book)) {
                this.books.remove(dto);
                return;
            }
        }
    }
}
