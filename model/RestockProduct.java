package model;

public class RestockProduct {

  private String rpID;
  private String rpQuantity;
  private String rpTotalPrice;
  private String rpStatus;
  private String rpdate;
  
  public String getRpID() {
    return rpID;
  }
  public void setRpID(String rpID) {
    this.rpID = rpID;
  }
  public String getRpQuantity() {
    return rpQuantity;
  }
  public void setRpQuantity(String rpQuantity) {
    this.rpQuantity = rpQuantity;
  }
  public String getRpTotalPrice() {
    return rpTotalPrice;
  }
  public void setRpTotalPrice(String rpTotalPrice) {
    this.rpTotalPrice = rpTotalPrice;
  }
  public String getRpStatus() {
    return rpStatus;
  }
  public void setRpStatus(String rpStatus) {
    this.rpStatus = rpStatus;
  }
  
  public String getRpDate() {
	    return rpdate;
	  }
  
  public void setRpDate(String rpdate) {
	    this.rpdate = rpdate;
	  }
      
}