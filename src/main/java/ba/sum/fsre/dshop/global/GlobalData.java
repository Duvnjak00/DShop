package ba.sum.fsre.dshop.global;

import ba.sum.fsre.dshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static{
        cart = new ArrayList<Product>();
    }
}
