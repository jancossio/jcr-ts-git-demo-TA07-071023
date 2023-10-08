import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ex3StockControlApp {
	
	public static void main(String[] args) {
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
			String act = JOptionPane.showInputDialog("Introduce la accion que deseas realizar: (a: agregar/c: consultar/m: modificar/s: stock/e: eliminar)");
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
		
		String output = countStock(list);
		JOptionPane.showMessageDialog(null, output);
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
	
	public static void addShopProduct(Hashtable<String, String[]> list, String name, String stock, String price) {
		
		String[] tmp = new String[2];
		tmp[0] = stock;
		tmp[1] = price;
		list.put(name, tmp);
	}
}
