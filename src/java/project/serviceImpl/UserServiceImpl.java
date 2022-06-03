package project.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import project.model.Cart;
import project.model.Product;
import project.model.ResponseModel;
import project.model.User;
import project.model.UserProduct;
import project.repository.UserProductRepository;
import project.repository.UserRepository;
import project.service.UserService;
import project.session.UserSession;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepository();
    private UserProductRepository userProductRepository = new UserProductRepository();

    @Override
    public ResponseModel<Boolean> purchase() {
        ResponseModel responsemodel = new ResponseModel();
        Cart cart = UserSession.getUser().getCart();
        Double sum = 0.0;
        for (int i = 0; i < cart.getList().size(); i++) {
            sum += cart.getList().get(i).getPrice();
        }
        //if (sum <= UserSession.getUser().getBalance()) {
        try {
            for (Product product : cart.getList()) {
                userProductRepository.insertAssets(product.getProductId());
            }
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Payment done!");
            responsemodel.setResponseObject(true);
            return responsemodel;

        } catch (Exception ex) {
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("Transaction failed!");
            responsemodel.setResponseObject(false);
            return responsemodel;
        }
        /*} else {
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("Insufficient balance!");
            responsemodel.setResponseObject(false);
            return responsemodel;
        }*/
    }

    @Override
    public ResponseModel<Boolean> addToCart(int productId) {
        ResponseModel responsemodel = new ResponseModel();
        Product product = new Product();
        product = userRepository.findByProductId(productId);
        if (product == null) {
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("Transaction failed!");
            responsemodel.setResponseObject(false);
            return responsemodel;
        }
        if (!UserSession.getUser().getCart().getList().contains(product)) {
            UserSession.getUser().getCart().getList().add(product);
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Transaction successful!");
            responsemodel.setResponseObject(true);
            return responsemodel;
            /*if (userRepository.addToCart(UserSession.getUser(), product) == true) {
                UserSession.getUser().getCart().getList().add(product);
                responsemodel.setIsSuccess(true);
                responsemodel.setMessage("Transaction successful!");
                responsemodel.setResponseObject(true);
                return responsemodel;
            } else {
                responsemodel.setIsSuccess(false);
                responsemodel.setMessage("Transaction failed!");
                responsemodel.setResponseObject(false);
                return responsemodel;
            }*/
        } else {
            responsemodel.setIsSuccess(false);
            responsemodel.setMessage("This product is already at your cart!");
            responsemodel.setResponseObject(false);
            return responsemodel;
        }
    }

    @Override
    public Double displayBalance() {
        return UserSession.getUser().getBalance();
    }

    @Override
    public ResponseModel<Boolean> rating(int rate) {

        return null;
    }

    @Override
    public ResponseModel<Boolean> deleteFromCart(int productId) {
        ResponseModel responsemodel = new ResponseModel();
        Product product = new Product();
        if (product != null) {
            for (Product product1 : UserSession.getUser().getCart().getList()) {
                if (product1.getProductId() == productId) {
                    product = product1;
                    break;
                }
            }
            UserSession.getUser().getCart().getList().remove(product);
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Deleted");
            responsemodel.setResponseObject(true);
            return responsemodel;

        } else {
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Transaction failed!");
            responsemodel.setResponseObject(null);
            return responsemodel;
        }
    }

    @Override
    public Cart displayCart(User user) {
        return UserSession.getUser().getCart();
    }

    @Override
    public ResponseModel<List<Product>> displayProducts() {
        ResponseModel responsemodel = new ResponseModel();
        List<Product> productList = new ArrayList<>();
        productList.addAll(userRepository.findProducts());
        if (UserSession.getUser() == null) {
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Successful!");
            responsemodel.setResponseObject(productList);
            return responsemodel;
        } else {
            List<UserProduct> list = new ArrayList<>();
            List<Product> productList2 = new ArrayList<>();
            list.addAll(userProductRepository.findByUserId(UserSession.getUser().getId()));
            for (Product product : productList) {
                for (UserProduct userProduct : list) {
                    if (product.getProductId() == userProduct.getProductId()) {
                        productList2.add(product);
                    }
                }
            }
            productList.removeAll(productList2);
            responsemodel.setIsSuccess(true);
            responsemodel.setMessage("Successful!");
            responsemodel.setResponseObject(productList);
            return responsemodel;
        }
    }

    @Override
    public ResponseModel<List<Product>> displayAssets() {
        ResponseModel responsemodel = new ResponseModel();
        List<UserProduct> assetList = new ArrayList<>();
        assetList.addAll(userProductRepository.findByUserId(UserSession.getUser().getId()));
        List<Product> assetList2 = new ArrayList<>();
        for (UserProduct userProduct : assetList) {
            assetList2.add(userRepository.findByProductId(userProduct.getProductId()));
        }
        responsemodel.setIsSuccess(true);
        responsemodel.setMessage("Succesfull");
        responsemodel.setResponseObject(assetList2);
        return responsemodel;
    }
}
