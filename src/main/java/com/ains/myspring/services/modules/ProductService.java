package com.ains.myspring.services.modules;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ains.myspring.models.jsontoclass.module.typeproduct.Jsonproduct;
import com.ains.myspring.models.modules.Product;
import com.ains.myspring.models.modules.Typeproduct;
import com.ains.myspring.repository.modules.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository _context;
  @Autowired
  private TypeproductService _serviceTypeproduct;

  public List<Product> getAllProduct() {
    return _context.findAll();
  }

  public Product Save(Jsonproduct product) throws Exception {
    return CreateNewProduct(product.getName(), product.getId());
  }

  public Product CreateNewProduct(String nameproduct, int idtypeproduct) throws Exception {
    if (_context.ProductIsExist(nameproduct) > 0) {
      throw new Exception("Product is already exist");
    } else {
      try {
        Optional<Typeproduct> typeproduct = _serviceTypeproduct.getTypeProductById(idtypeproduct);
        return _context.save(new Product(typeproduct.get(), nameproduct));
      } catch (Exception e) {
        throw new Exception(e.getMessage());
      }
    }
  }

  public Product UpdateProduct(Product newProduct) throws Exception {
    Optional<Product> LatestProduct = _context.findById(newProduct.getIdproduct());
    if (LatestProduct.isPresent() || _context.ProductIsExist(newProduct.getNameproduct()) > 0) {
      throw new Exception("Product not found or is already exist");
    } else {
      try {
        Optional<Typeproduct> typeproduct = _serviceTypeproduct.getTypeProductById(newProduct.getIdproduct());
        Product updateProduct = new Product(typeproduct.get(), newProduct.getNameproduct());
        return _context.save(updateProduct);
      } catch (Exception e) {
        throw new Exception(e.getMessage());
      }
    }
  }

  public void DeleteProduct(int idproduct) throws Exception {
    try {
      _context.deleteById(idproduct);
    } catch (Exception e) {
      throw new Exception("Product in conflict");
    }
  }

  public Product getProductbyId(int idproduct) throws Exception {
    Optional<Product> product = _context.findById(idproduct);
    if (product.isEmpty()) {
      throw new Exception("Product not found");
    }
    return product.get();
  }
}
