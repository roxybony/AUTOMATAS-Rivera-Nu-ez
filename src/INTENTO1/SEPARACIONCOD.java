package INTENTO1;



import java.util.*;

public class SEPARACIONCOD {
	public static void main(String[] args) {
		SEPARACIONCOD obj = new SEPARACIONCOD();
		obj.separara();
		obj.analizar();
	}

	String cad = "int a ; float b , c ; char h ; b = a + ( c * h ) ; $";
	String[] vect = cad.split(" ");
	String[] nobien = { "I7	I3	I4	I5											I1	I2		I6			",
			"													P0							",
			"I8																				",
			"P3																				",
			"P4																				",
			"P5																				",
			"													P2							",
			"										I9										",
			"				I11	I12											I10				",
			"I17											I16							I13	I14	I15",
			"													P1							",
			"I18																				",
			"I7	I3	I4	I5											I19	I2		I6			",
			"					I20	I21	I22													",
			"					P11	P11	P11	I23	I24			P11								",
			"					P14	P14	P14	P14	P14			P14								",
			"I17																		I25	I14	I15",
			"					P16	P16	P16	P16	P16			P16								",
			"				I11	I12											I26				",
			"													P7							",
			"													P8							",
			"I17											I16								I27	I15",
			"																			I28	I15",
			"I17											I16									I29",
			"I17											I16									I30",
			"						I21	I22					I31								",
			"													P6							",
			"					P9	P9	P9	I23	I24			P9								",
			"					P10	P10	P10	I23	I24			P10								",
			"					P12	P12	P12	P12	P12			P12								",
			"					P13	P13	P13	P13	P13			P13								",
			"					P15	P15	P15	P15	P15			P15								" };
	String igual[] = { ",", "(", ")", "$" };
	String termina = ";";
	String asigna = "=";
	String operandos[] = { "+", "-", "*", "/" };
	String tipo[] = { "int", "float", "char" };// "Palabras clave"

	String[] columna = { "id", "int", "float", "char", ",", ";", "+", "-", "*", "/", "=", "(", ")", "$", "P", "Tipo",
			"V", "A", "E", "T", "F" };
	String[] fila = { "I0", "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10", "I11", "I12", "I13", "I14",
			"I15", "I16", "I17", "I18", "I19", "I20", "I21", "I22", "I23", "I24", "I25", "I26", "I27", "I28", "I29",
			"I30", "I31" };

	String[] produciones={"P","Tipo","A","int","float","char",",",";","id","E","E", "T", "T","T", "F","(","id"};
	String[] agregaP={"P'","P","P","Tipo","Tipo","Tipo","V","V","A","E","E","E","T","T","T","F","F"};
	String[] auxP={"P0","P1","P2","P3","P4","P5","P6","P7","P8","P9","P10","P11","P12","P13","P14","P15","P16"};
	String[] especiales = {"","P9", "P10", "P12", "P13"};

	Vector<String[]> bien = new Vector<String[]>();
	Stack<String> pila = new Stack<String>();
	Stack<String[]> GuardarDatos = new Stack<String[]>();
	Stack<String[]> GuardarTiposDatos = new Stack<String[]>();
	Stack<String> PSem = new Stack<String>();

	String T1,T2;
	public void separara() {
		for (int i = 0; i < nobien.length; i++) {
			bien.add(i, nobien[i].split((char) 9 + "", 50));
		}
		/* System.out.println(bien.elementAt(0)[0]); mostrar posicion de la matriz */

		/*
		 * for(int i=0;i<vect.length;i++) { System.out.println(vect[i]); } mostrar
		 * cadena separada
		 */
	}

	// Sintactico
	public void analizar() {
		pila.push("I0");
		String cadenita = "";

		int f = 0;
		int c = 0;
		int i = 0;
		String PoI = "";
//		System.out.println(Bposicion(especiales, "P9"));
		do {
			cadenita = Retornar(vect[i]);/*
											 * retornar el carcater analizado hacer metodo/ expresion regular para
											 * validar variable
											 */
			f = Bposicion(fila, pila
					.elementAt(pila.size() - 1));/* buscar el ultimo estado que esta en la pila en su ultima posicion */
			c = Bposicion(columna, cadenita);/* analiza la posicion del caracter a analizar en columna */
			PoI = bien.elementAt(f)[c].charAt(0) + "";
			if(cadenita.equalsIgnoreCase("$")&&bien.elementAt(f)[c].equalsIgnoreCase("P0")){
				System.out.println("cadenita aceptada");
				break;
			}
			System.out.println(" Fila:"+f+" Columna:"+c+" Caracter:"+cadenita+" Tabla:"+bien.elementAt(f)[c]);
			System.out.println(pila);

			if (PoI.equals("I")) { /* ES UN DESPLAZAMIENTO */
				pila.push(cadenita);
				pila.push(bien.elementAt(f)[c]);/* la pila agrega la cadenita con su estado */
				i++;
				System.out.println(" Fila:"+f+" Columna:"+c+" Caracter:"+cadenita+" Tabla:"+bien.elementAt(f)[c]);
				System.out.println(pila);

			} else if (PoI.equals("P")) {/* es produccion/reduccion */
				System.out.println(" Fila:"+f+" Columna:"+c+" Caracter:"+cadenita+" Tabla:"+bien.elementAt(f)[c]);
				System.out.println(pila);
				String roxyboni=bien.elementAt(f)[c];
				System.out.println(roxyboni);
				do{
					pila.pop();
					System.out.println(" Fila:"+f+" Columna:"+c+" Caracter:"+cadenita+" Tabla:"+bien.elementAt(f)[c]);
					System.out.println(pila);
				}while(!produciones[Bposicion(auxP, bien.elementAt(f)[c])].equalsIgnoreCase(pila.elementAt(pila.size()-1)));
						//hasta donde se elimina					de la pila 
				pila.pop();
				pila.push(agregaP[Bposicion(auxP, bien.elementAt(f)[c])]);
							//se le agrega a la pila la produccion
				f=Bposicion(fila, pila.elementAt(pila.size()-2));
				c=Bposicion(columna, pila.elementAt(pila.size()-1));
				pila.push(bien.elementAt(f)[c]);
				System.out.println(" Fila:"+f+" Columna:"+c+" Caracter:"+cadenita+" Tabla:"+bien.elementAt(f)[c]);
				System.out.println(pila);

				if (roxyboni == "P16") {
                    System.out.println(PSem);
                } else if (Bposicion(especiales,roxyboni)!=0) {
                    
                    T1 = PSem.pop();
                    T2 = PSem.pop();
                    
                    if (!T1.equalsIgnoreCase("char") && !T2.equalsIgnoreCase("char")) {
                        if (!T1.equalsIgnoreCase(T2)) {
                            PSem.push("float");
                            System.out.println(PSem);
                        } else {
                            PSem.push(T1);
							System.out.println(PSem);
                        }
                    }else{
                        //error semantico
						System.out.println("error semantico");
                        break;
                    }
                }else if(roxyboni.equalsIgnoreCase("P8")){
                    T1 = PSem.pop();
                    T2 = PSem.pop();
                    if (!T1.equalsIgnoreCase(T2)) {
                        System.out.println("error semantico");
                        break;
                    }
                    System.out.println(PSem);
                }else{
                    System.out.println(PSem);
                }


			}
		} while (i <= vect.length);
	}

	String dato = "";
	boolean ban_asigna = false;

	// Lexico
	public String Retornar(String palabra) {
		// verifica si viene un tipo de dato en teoria palabras clave
		if (!BvecString(tipo, palabra).equalsIgnoreCase("")) {
			dato = palabra;
			return palabra;
		} else {
			// int a;
			if (palabra.equalsIgnoreCase(termina)) {
				dato = "";
				if (ban_asigna) {
					// al terminar la linea ya no se pueden hacer asignaciones y se limpia el tipo
					// de dato
					ban_asigna = false;
					//aqui se controlara el if y do-while
				}
			} else if (!BvecString(operandos, palabra).equalsIgnoreCase("")) {
				// encuentra al operando potencial borrado
				return palabra;
			} else if (palabra.equalsIgnoreCase(asigna)) {
				ban_asigna = true;
				return palabra;
				// si BvecString retorna algo diferente de ""
			} else if (!BvecString(igual, palabra).equalsIgnoreCase("")) {
				return palabra;// No hace nada potencial borrado

			} else if (palabra.matches("[A-z]+[0-9]*[A-z]*")) {// Es un id
				if(dato.equalsIgnoreCase("")){
					T1=BuscarDatos(palabra);
					PSem.push(T1);
				}
				String[] auxvec = { palabra, dato, "0" };
				GuardarDatos.push(auxvec);
				return "id";
			} else {
				return palabra;
			}
		}

		return palabra;
	}

	public String BuscarDatos(String dat){
		for (int i = 0; i < GuardarDatos.size(); i++) {
			if(GuardarDatos.elementAt(i)[0].equalsIgnoreCase(dat)){
				return GuardarDatos.elementAt(i)[1];
			}
		}
		return "";
	}

	public int Bposicion(String cdenas[], String busco) {
		for (int i = 0; i < cdenas.length; i++) {
			if (cdenas[i].equalsIgnoreCase(busco)) {
				return i;
			}
		}
		return 0;
	}

	// Tipo
	public String BvecString(String cdenas[], String busco) {
		for (int i = 0; i < cdenas.length; i++) {
			if (cdenas[i].equalsIgnoreCase(busco)) {
				return cdenas[i];
			}
		}
		return "";
	}
}






