import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ex1StudentAverageGradeApp {
	
	public static void main(String[] args) {
		
		//int nStudents = 3;
	
		Hashtable<String, Double> grades = new Hashtable<String, Double>();
		
		String nStud = JOptionPane.showInputDialog("¿A cuantos alumnos deseas introducir?");
	    Integer nStudents = Integer.parseInt(nStud);
		
		String nGrad = JOptionPane.showInputDialog("¿Cuantas notas deseas introducir?");
		Integer nGrades = Integer.parseInt(nGrad);
	    
		while(nStudents > 0) {
			
			String name = JOptionPane.showInputDialog("Introduce el nombre del alumno: ");
			
			boolean exists = checkStudent(name, grades);
			
			//System.out.println(exists);
			
			if(exists) {
				String resp = JOptionPane.showInputDialog("Este alumno ya esta registrado, ¿Deseas modificar su nota?: (si/no)");
			    String response = resp.toLowerCase();
				
				if(response.equals("si")) {
				double average = setGrades(name, nGrades);
				grades.put(name, average);
				}
			}else {
				double average = setGrades(name, nGrades);
				grades.put(name, average);
				nStudents--;
			}
		}
		
		System.out.println(grades);
	}
	
	public static double setGrades(String name, Integer numGrades) {
		
		double avrGrade = 0;
		
		for(int i=0; i<numGrades; i++) {
			String val = JOptionPane.showInputDialog("Introduce el valor de la nota "+(i+1)+" : ");
			Double grade = Double.parseDouble(val);
			
			avrGrade += grade;
		}
		return (avrGrade/3);
	}
	
	public static boolean checkStudent(String name, Hashtable students) {
		
		Hashtable<String, Double> temp = students;
		Enumeration<String> names = students.keys();
		int i = 0;

		System.out.println("Name: "+name);
		
		while(names.hasMoreElements()) {
			if(name.equals(names.nextElement())) {
				return true;
			}
			i++;
		}
		return false;
	}
}
