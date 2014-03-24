package beans;

import java.io.IOException;
import java.math.BigDecimal;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AdministratorBean {
				
	
		 
		private static final Order[] orderList = new Order[] {
			 
			new Order("A0001", "Intel CPU", 
					new BigDecimal("700.00"), 1),
			new Order("A0002", "Harddisk 10TB", 
					new BigDecimal("500.00"), 2),
			new Order("A0003", "Dell Laptop", 
					new BigDecimal("11600.00"), 8),
			new Order("A0004", "Samsung LCD", 
					new BigDecimal("5200.00"), 3),
			new Order("A0005", "A4Tech Mouse", 
					new BigDecimal("100.00"), 10)
		};
	 
		public Order[] getOrderList() {
	 
			return orderList;
	 
		}
	 
		public static class Order{
	 
			String orderNo;
			String productName;
			BigDecimal price;
			int qty;
	 
			public Order(String orderNo, String productName, 
	                                BigDecimal price, int qty) {
	 
				this.orderNo = orderNo;
				this.productName = productName;
				this.price = price;
				this.qty = qty;
			}
	 
			//getter and setter methods
		}
		
		public void logout()
		{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		 ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
	        try {
				context.redirect("Login.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
	
	

