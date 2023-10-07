import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.JOptionPane;

public class Ex2SupermarketSellsApp {
	
	public static void main(String[] args) {
		
		final double IVA = 0.21;
				
		ArrayList<String[]> shopList= new ArrayList<String[]>();

		String num = JOptionPane.showInputDialog("Introduce la cantidad de articulos que hayas comprado: ");
		Integer numArticles = Integer.parseInt(num);
		
		fillShopList(numArticles, shopList);
		
		//System.out.println(shopList);
		
		double totalPrice = getTotalPrice(shopList);
		
		JOptionPane.showMessageDialog(null, "El precio bruto de la compra es: "+totalPrice);
		
		double totalIvaPrice = totalPrice + (totalPrice * IVA);
		
		JOptionPane.showMessageDialog(null, "El precio con IVA de la compra es: "+totalIvaPrice);
		
		Integer totalNumArticles = countArticles(shopList);
		
		JOptionPane.showMessageDialog(null, "La cantidad de articulos comprados es: "+totalNumArticles);

		double payMoney;
		
		do {
			String pay = JOptionPane.showInputDialog("Introduce la cantidad de dinero con la que quieras pagar: ");
			payMoney = Integer.parseInt(pay);
			if(payMoney < totalIvaPrice) {
				JOptionPane.showMessageDialog(null, "Cantidad de dinero insuficiente! ");
			}
		}while(payMoney < totalIvaPrice);
		
		double change = payMoney - totalIvaPrice;
		
		JOptionPane.showMessageDialog(null, "Has pagado: "+payMoney+" , por lo que se te devolverá: "+change);
	}
	
	public static void fillShopList(int nArticles, ArrayList<String[]> list) {
		
		for(int i = 0; i<nArticles; i++) {
			String art = JOptionPane.showInputDialog("Introduce el articulo "+(i+1)+" que hayas comprado: ");
		    String article = art.toLowerCase();
		    
			String quant = JOptionPane.showInputDialog("Introduce la cantidad de ese articulo que lleves: ");
			//Integer quanttity = Integer.parseInt(quant);
			
			String price = JOptionPane.showInputDialog("Introduce el precio de ese articulo: ");
			//Double priceArticle = Double.parseDouble(price);
			
			String[] product = {article, quant, price};
			list.add(product);
		}
	}
	
	public static double getTotalPrice(ArrayList<String[]> list) {
		double totalPrice = 0;
		
		Iterator<String[]> it = list.iterator();
		
		String[] tmp = new String[3];
		
		String quant = "";
		String price = "";
		double result = 0;
		
		while(it.hasNext()) {
			
			tmp = it.next();
			quant = tmp[1];
			Integer quantity = Integer.parseInt(quant);
			price = tmp[2];
			Double priceProd = Double.parseDouble(price);
			result = priceProd * quantity;
			totalPrice += result;
		}
		
		return totalPrice;
	}
	
	public static Integer countArticles(ArrayList<String[]> list) {
		Integer totalArticles = 0;
		
		Iterator<String[]> it = list.iterator();
		
		String[] tmp = new String[3];
		
		String name = "";
		String quant = "";
		
		while(it.hasNext()) {
			tmp = it.next();
			quant = tmp[1];
			Integer quantity = Integer.parseInt(quant);
			name = tmp[0];
			totalArticles += quantity;
			
			JOptionPane.showMessageDialog(null, "Has comprado: "+name+", en "+quantity+" unidades"+totalArticles);
		}
		
		return totalArticles;
	}
}
