package generics.cart;

import java.util.*;

public class ShoppingCart<T extends CartItem> {

    private List<T> products = new ArrayList<>();
    private List<Double> discounts = new ArrayList<>();

    public void add(T item) {
        if (getProductCount(item.getId()) == 0){
            products.add(item);
        }else{
            increaseQuantity(item.getId());
        }
    }

    public void removeById(String id) {
        for (T product : products) {
            if (product.getId().equals(id)){
                products.remove(product);
                return;
            }
        }
    }

    private Double totalDiscount(Double price){
        if (discounts.isEmpty()){
            return price;
        }

        Double result = price;
        for (Double discount : discounts) {
            result = result * ((100 - discount)/100);
        }
        return result;
    }

    public Double getTotal() {
        Double totalPrice = 0.0;


        for (T product : products) {
            totalPrice += totalDiscount(product.getPrice());
        }

        return totalPrice;
    }

    public void increaseQuantity(String id) {
        Set<T> set = new LinkedHashSet<>(products);

        for (T product : set) {
            if (product.getId().equals(id)){
                products.add(product);
                return;
            }
        }
    }

    public void applyDiscountPercentage(Double discount) {
        discounts.add(discount);
    }

    public void removeLastDiscount() {
        discounts.remove(discounts.size()-1);
    }

    public void addAll(List<? extends T> items) {
        for (T item : items) {
            add(item);
        }
    }

    private int getProductCount(String id){
        int counter = 0;
        for (T product : products) {
            if (product.getId().equals(id)){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        Set<T> set = new LinkedHashSet<>(products);

        System.out.println(products);

        String result = "";

        int index = 0;

        for (T o : set) {
            result += "(" + o.getId() + ", " + o.getPrice() + ", " + getProductCount(o.getId()) + ")";
            if (index != set.size() - 1){
                result += ", ";
            }
            index++;
        }

        return result;
    }

}
