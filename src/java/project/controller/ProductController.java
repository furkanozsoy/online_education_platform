
package project.controller;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import project.model.Product;
import project.model.ResponseModel;
import project.serviceImpl.UserServiceImpl;
import project.session.UserSession;

@Named
@SessionScoped
public class ProductController implements Serializable{
    
    private List<Product> productList = new ArrayList<>();
    private List<Product> assetList = new ArrayList<>();
    private UserServiceImpl userServiceImpl = new UserServiceImpl();
    private LoginController loginController = new LoginController();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Product> assetList) {
        this.assetList = assetList;
    }
       
    
    public String displayProducts(){
        ResponseModel responsemodel=new ResponseModel();
        responsemodel=userServiceImpl.displayProducts();        
        if(responsemodel.getIsSuccess()==true) {
            setProductList((List<Product>) responsemodel.getResponseObject());
            if(UserSession.getUser()==null){
                return "kurslar";
            }else{
                return "L-kurslar";
            }
       }else{
            responsemodel.getMessage();
            return null;
       }               
    }
    
    public String displayAssets(){
        ResponseModel responsemodel=new ResponseModel();
        responsemodel=userServiceImpl.displayAssets();     
        if(responsemodel.getIsSuccess()==true){
            setAssetList((List<Product>) responsemodel.getResponseObject());
            return "L-kurslarım";
        }else{
            responsemodel.getMessage();
            return null;      
        }
    }
    
    public String watchCourse(int productId){       
        Product product = new Product();
        for(int i=0 ; i<assetList.size();i++){
            if(assetList.get(i).getProductId()==productId){
                product=assetList.get(i);
                break;
        }      
    }
        if(product.getProductId()==1){
            return "L-video1";
        }
        if(product.getProductId()==2){
            return "L-video2";
        }
        if(product.getProductId()==3){
            return "L-video3";
        }
        if(product.getProductId()==4){
            return "L-video4";
        }
        if(product.getProductId()==5){
            return "L-video5";
        }
        if(product.getProductId()==6){
            return "L-video6";
        }
        if(product.getProductId()==7){
            return "L-video7";
        }
        if(product.getProductId()==8){
            return "L-video8";
        }
        if(product.getProductId()==9){
            return "L-video9";
        }else{
            return "L-kurslarım";
        }     
    }
}