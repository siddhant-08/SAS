import java.io.Serializable;

public class Product implements Serializable{
    private String prodID;
    private String name;
    private String manufacturer;
    private double cost;
    private double discount;
    private double quantity;
    private double MRP;

    public Product(String name_,String manufacturer_,String prodID_)
    {
        name = name_;
        manufacturer = manufacturer_;
        prodID = prodID_;
        quantity = 0;
    }
    public Product(String name_,String manufacturer_,String prodID_,
    		double cost_,double discount_, double quantity_,double MRP_)
    {
        name = name_;
        manufacturer = manufacturer_;
        prodID = prodID_;
        quantity = quantity_;
        cost=cost_;
        discount=discount_;
        MRP=MRP_;
        
    }
    
    public double getProfit(){
        return MRP*((100-discount))/100 - cost;
    }
    public String getProductID(){
        return prodID;
    }
    public String getName(){
        return name;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public double getMRP(){
        return MRP;
    }
    public double getCost(){
        return cost;
    }
    public double getDiscount(){
        return discount;
    }
    public double getQuantity(){
        return quantity;
    }
    
    //setters
    
    public void setCost(double costVal){
        cost = costVal;
    }
    public void setDiscount(double discount_){
        discount = discount_;
    }
    public void setQuantity(double quantity_){
        quantity = quantity_;
    }

    public void setMRP(double MRP_){
        MRP = MRP_;
    }
    
    // newQty is positive for addition and negative for deletion
    public void updateQuantity(double newQty){
      quantity += newQty;
    }
    
}