/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package in.gadgethub.pojo;

/**
 *
 * @author hp
 */
public class DemandPojo {
    private String useremail;
    private String prodId;
    private int demandQuantity;

    public DemandPojo(String useremail, String prodId, int demandQuantity) {
        this.useremail = useremail;
        this.prodId = prodId;
        this.demandQuantity = demandQuantity;
    }

    public DemandPojo() {
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public int getDemandQuantity() {
        return demandQuantity;
    }

    public void setDemandQuantity(int demandQuantity) {
        this.demandQuantity = demandQuantity;
    }
    
            
    
}
