//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//import dao.VendorDAO;
//
//public class Main {
//    public static void main(String[] args) {
//
//        VendorDAO vendorDAO = new VendorDAO();
//        vendorDAO.getAllVendors();
//    }
//}
//import dao.PurchaseOrderDAO;

//public class Main {
//    public static void main(String[] args) {
//
//        PurchaseOrderDAO po = new PurchaseOrderDAO();
//
//        int orderId = po.createPurchaseOrder(1, 120000);
//
//        System.out.println("Purchase Order Created with ID: " + orderId);
//    }
//}

//import dao.PurchaseOrderDAO;
//import dao.OrderItemDAO;
//
//public class Main {
//    public static void main(String[] args) {
//
//        PurchaseOrderDAO poDAO = new PurchaseOrderDAO();
//        OrderItemDAO itemDAO = new OrderItemDAO();
//
//        int orderId = poDAO.createPurchaseOrder(1, 120000);
//
//        itemDAO.addOrderItem(orderId, 101, 5);
//        itemDAO.addOrderItem(orderId, 102, 3);
//
//        System.out.println("Order and items added successfully!");
//    }
//}

import dao.PurchaseOrderDAO;
import dao.OrderItemDAO;
import dao.InventoryDAO;

public class Main {
    public static void main(String[] args) {

        PurchaseOrderDAO poDAO = new PurchaseOrderDAO();
        OrderItemDAO itemDAO = new OrderItemDAO();
        InventoryDAO inventoryDAO = new InventoryDAO();

        // 1 Create purchase order
        int orderId = poDAO.createPurchaseOrder(1, 120000);

        // 2 Add order items + update inventory
        itemDAO.addOrderItem(orderId, 101, 5);
        if (!inventoryDAO.reduceStock(101, 5)) return;

        itemDAO.addOrderItem(orderId, 102, 3);
        if (!inventoryDAO.reduceStock(102, 3)) return;

        itemDAO.addOrderItem(orderId, 103, 2);
        if (!inventoryDAO.reduceStock(103, 2)) return;

        System.out.println("Order placed and inventory updated!");
    }
}
