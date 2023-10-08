import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class Ex4ShopManagementApp {
	public static void main(String[] args) {
		final double IVA = 0.21;
		Hashtable<String, String[]> articles = new Hashtable<String, String[]>();
		
		addShopProduct(articles, "pan", "8", "1.15");
		addShopProduct(articles, "leche", "17", "3.65");
		addShopProduct(articles, "huevos", "24", "6.75");
		addShopProduct(articles, "carne", "18", "8.05");
		addShopProduct(articles, "olivas", "8", "5.50");
		addShopProduct(articles, "zumo", "6", "4.00");
		addShopProduct(articles, "pescado", "5", "7.95");
		addShopProduct(articles, "yogur", "14", "3.75");
		addShopProduct(articles, "aceite", "8", "1.15");
		addShopProduct(articles, "azucar", "8", "1.15");
	    
		String goOn="";

		do {
			String act = JOptionPane.showInputDialog("Introduce la accion que deseas realizar: (a: agregar/c: consultar/m: modificar/s: stock/e: eliminar/v: venta)");
		    String action = act.toLowerCase();
		    
		    switch(action){
			
		    case "c":
				String prod = JOptionPane.showInputDialog("Introduce el nombre del producto que desees consultar: ");
			    String produc = prod.toLowerCase();
			    if(checkProductExists(articles, produc)) {
				    String[] result = articles.get(produc);
					JOptionPane.showMessageDialog(null, "El producto "+produc+" tiene "+result[0]+" unidad/es en stock a un precio de "+result[1]+" euros");
			    }else {
					JOptionPane.showMessageDialog(null, "El producto introducido no existe en el registro.");
			    }
				break;
			case "m":
				String output = countStock(articles);
				output = output+"Introduce el nombre del producto que desees modificar: ";
				String art = JOptionPane.showInputDialog(output);
			    String articl = art.toLowerCase();;
				if(checkProductExists(articles, articl)) {
				    modifyShopProduct(articles,articl);
			    }else {
			    	String resp = JOptionPane.showInputDialog("El producto que buscas no existe, ¿deseas añadirlo?: (s/n)");
				    String response = resp.toLowerCase();
				    if(response.equals("s")) 
				    	setNewShopProduct(articles, articl);
			    }
			    
				break;
			case "a":
				String outprint = countStock(articles);
				outprint = outprint+"Introduce el nombre del nuevo producto: ";
				String pro = JOptionPane.showInputDialog(outprint);
			    String produ = pro.toLowerCase();
				if(!checkProductExists(articles, produ)){
					setNewShopProduct(articles, produ);
					JOptionPane.showMessageDialog(null, "El producto ["+produ+"] ha quedado registrado en la base de datos.");
				}else {
					JOptionPane.showMessageDialog(null, "Este producto ya existe, no puedes hacer esta operacion.");
				}
				break;
			case "s":
			    showStock(articles);
				break;
			case "e":
				String outp = countStock(articles);
				outp = outp+"Introduce el nombre del producto que desees eliminar: ";
				String arti = JOptionPane.showInputDialog(outp);
			    String article = arti.toLowerCase();
				if(checkProductExists(articles, article)){
					ereaseShopProduct(articles, article);
				}else {
					JOptionPane.showMessageDialog(null, "Este producto no existe en la base de datos.");
				}
				break;
				
			case "v":
				ArrayList<String[]> shopList= new ArrayList<String[]>();
				
				fillShopList(shopList, articles);
				
				double totalPrice = showPurchase(shopList, IVA);
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "La accion introducida no se reconoce");
			}
		    String go = JOptionPane.showInputDialog("¿deseas continuar realizando otra accion?: (s/n)");
		    goOn = go.toLowerCase();
    	}while(goOn.equals("s"));
	}
	
	public static void modifyShopProduct(Hashtable<String, String[]> list, String name) {
		
		String[] cont = list.get(name);
		
		String stoc = JOptionPane.showInputDialog("Cuantas unidades del producto hay actualmente? ");
	    //Integer stock = Integer.parseInt(sto);
	    
	    String pric = JOptionPane.showInputDialog("Cual es el precio actual de cada unidad? ");
	    //Double price = Double.parseDouble(pri);
	    
	    cont[0] = stoc;
	    cont[1] = pric;
	    
		list.put(name, cont);
	}
	
	public static void setNewShopProduct(Hashtable<String, String[]> list, String name) {
		
	    String quant = JOptionPane.showInputDialog("¿Cuantas unidades quieres agregar de este nuevo producto?: ");
		//Integer quantity = Integer.parseInt(quant);
		String pri = JOptionPane.showInputDialog("Cual es el precio de cada unidad de este producto?: ");
		//Integer price = Integer.parseInt(pri);
		addShopProduct(list, name, quant, pri);
	}
	
	public static void ereaseShopProduct(Hashtable<String, String[]> list, String name) {
		
	   list.remove(name);
		JOptionPane.showMessageDialog(null, "El articulo ["+name+"] ha sido borrado del stock.");
	}
	
	public static boolean checkProductExists(Hashtable<String, String[]> list, String name) {
		
		boolean found = false;
		Enumeration<String> names = list.keys();
		while(names.hasMoreElements() && !found) {
			if(name.equals(names.nextElement())) {
				found = true;
			}
		}
		return found;
	}
	
	public static void showStock(Hashtable<String, String[]> list) {
		
		//Enumeration<String[]> enumeration = list.elements();
		Enumeration<String> names = list.keys();
		String tmpName="";
		String[] tmpContent;
		String output = "Stock\r\n";
		
		while(names.hasMoreElements()) {
			tmpName = names.nextElement();
			tmpContent = list.get(tmpName);
			//JOptionPane.showMessageDialog(null, "Del producto "+tmpName+" hay "+tmpContent[0]+" unidad/es en stock a un precio de "+tmpContent[1]+" euros");
			output = output +tmpName+" \t"+tmpContent[0]+" unidades a "+tmpContent[1] +"€\r\n";
		}
		JOptionPane.showMessageDialog(null, output);
	}
	
	public static void addShopProduct(Hashtable<String, String[]> list, String name, String stock, String price) {
		
		String[] tmp = new String[2];
		tmp[0] = stock;
		tmp[1] = price;
		list.put(name, tmp);
	}
	
	public static void sellShopProduct(Hashtable<String, String[]> list, String name, Integer numUnits) {
		
		String[] tmp = new String[2];
		
		tmp = list.get(name);
		Integer availStock = Integer.parseInt(tmp[0]);
		Integer newStock = availStock-numUnits;
		
		tmp[0]=newStock.toString();
		
		list.put(name, tmp);
	}
	
	public static void fillShopList(ArrayList<String[]> list, Hashtable<String, String[]> stock) {
		
		boolean repeat = true;
		int i = 0;
		
		do {
			String art = JOptionPane.showInputDialog("Introduce el articulo "+(i+1)+" de la compra: ");
		    String article = art.toLowerCase();
		    
		    if(checkProductExists(stock, article)) {
		    	
			    String[] result = stock.get(article);
		    	
			    Integer units = Integer.parseInt(result[0]);
			    
		    	String quant = JOptionPane.showInputDialog("Introduce la cantidad de ese articulo: ");
				Integer quantity = Integer.parseInt(quant);
				if(quantity<=units) {
					String[] product = {article, quant, result[1]};
					list.add(product);
				    sellShopProduct(stock,article,units);
				    
				    String deci = JOptionPane.showInputDialog("¿Deseas agregar otro producto a esta compra? (s/n)");
				    String decision = deci.toLowerCase();
				    
				    if(decision.equals("s")) {
				    	repeat = true;
				    }else if(decision.equals("n")) {
				    	repeat = false;
				    }

				}else {
					JOptionPane.showMessageDialog(null, "Lo sentimos pero, no se disponen de tantas unidades en stock.");
				}
				
		    }else {
				JOptionPane.showMessageDialog(null, "Lo sentimos pero, el producto que busca no se encuentra entre el stock actual.");
		    }
		}while(repeat);
	}
	
	public static String countStock(Hashtable<String, String[]> list){
		Enumeration<String> names = list.keys();
		String tmpName="";
		String[] tmpContent;
		String output = "Stock\r\n";
		
		while(names.hasMoreElements()) {
			tmpName = names.nextElement();
			tmpContent = list.get(tmpName);
			//JOptionPane.showMessageDialog(null, "Del producto "+tmpName+" hay "+tmpContent[0]+" unidad/es en stock a un precio de "+tmpContent[1]+" euros");
			output = output +tmpName+" \t"+tmpContent[0]+" unidades a "+tmpContent[1] +"€\r\n";
		}
		return output;
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
	
public static double showPurchase( ArrayList<String[]> list, double iva) {
		
		Iterator<String[]> it = list.iterator();
		
		String[] tmp = new String[3];
		double totalPrice = 0;
		
		String quant = "";
		String price = "";
		double result = 0;
		
		String output = "Stock\r\n";
		
		while(it.hasNext()) {
			
			tmp = it.next();
			quant = tmp[1];
			Integer quantity = Integer.parseInt(quant);
			price = tmp[2];
			Double priceProd = Double.parseDouble(price);
			output = output +tmp[0]+" \t"+tmp[1]+" unidades a "+tmp[2] +"€\r\n";
			
			result = priceProd * quantity;
			totalPrice += result;
		}
		output = output +" \t"+" Precio de la compra: "+totalPrice+"€\r\n";
		double ivaPrice = totalPrice+(totalPrice*iva);
		output = output +" \t"+" Precio total de la compra con IVA: "+ivaPrice+"€\r\n";
		JOptionPane.showMessageDialog(null, output);
		
		return totalPrice;
	}
}
