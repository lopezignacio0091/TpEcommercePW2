/*
 * package java;
 * 
 * import static org.junit.Assert.assertEquals;
 * 
 * import java.util.List;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * 
 * 
 * import com.context.model.Cart; import com.context.model.Product; import
 * com.context.repository.CartRepository; import
 * com.context.repository.ProductRepository; import com.context.utils.Status;
 * 
 * @RunWith(SpringRunner.class)
 * 
 * @SpringBootTest public class IceCreamApplicationTests {
 * 
 * @Autowired private ProductRepository Product;
 * 
 * @Autowired private CartRepository cart;
 * 
 * @Test public void contextLoads() {
 * 
 * 
 * List<Cart> carts = cart.findAll(); List<Product> productList =
 * Product.findAll();
 * 
 * 
 * for(Cart cartActual : carts) {
 * 
 * assertEquals(carts.size(),1);
 * 
 * if(cartActual.getStatus() == Status.PROCESSED) {
 * assertEquals(carts.size(),1);
 * 
 * }else if(cartActual.getStatus() == Status.FAILED) {
 * 
 * assertEquals(carts.size(),0);
 * 
 * }
 * 
 * System.out.println(cartActual.getEmail() +
 * "// "+cartActual.getStatus()+"// "+cartActual.getfullName() +" //"+
 * cartActual.getId()); } int cont = 0;
 * 
 * for(Product productActual : productList) {
 * assertEquals(productList.size(),3); if(productActual.getStock() == null ||
 * productActual.getStock() == 0 ) { cont++; }
 * 
 * System.out.println(productActual.getId() +
 * "// "+productActual.getDescription()+ "// "+productActual.getStock()); }
 * assertEquals(cont,1); }
 * 
 * 
 * }
 * 
 */
