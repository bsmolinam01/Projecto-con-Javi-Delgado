//  import java.util.Scanner;
//  
//  public class CifrasYLetras {
//  
//
//      static final Scanner TECLADO = new Scanner(System.in);
//  
//      static final boolean USAR_COLORES = true;
//      static final String RESET = "\033[0m";
//      static final String CIAN = "\033[1;36m";
//      static final String VERDE = "\033[1;32m";
//      static final String MAGENTA = "\033[1;35m";
//      static final String AMARILLO = "\033[1;33m";
//      static final String ROJO = "\033[1;31m";
//
//
//      static final String VOCALES = "AEIOU";
//      static final String CONSONANTES = "BCDFGHJKLMNÑPQRSTVWXYZ";
//      static final String ALFABETO = VOCALES + CONSONANTES;
//  
//      static int repeticiones = 1;
//      static int cantidadLetras = 10;
//      static int cantidadOperaciones = 5;
//        
//  
//      public static void main(String[] args) {
//          boolean salir = false;
//  
//          while (!salir) {
//              mostrarMenuPrincipal();
//  
//              switch (leerEntero("Opcion: ", 0, 3)) {
//                  case 1:
//                      iniciarPartida(true);
//                      break;
//  
//                  case 2:
//                      iniciarPartida(false);
//                      break;
//  
//                  case 3:
//                      configurar();
//                      break;
//  
//                  case 0:
//                      salir = true;
//                      break;
//  
//                  default:
//                      break;
//              }
//          }
//  
//          System.out.println("Hasta pronto.");
//      }
//  
//      static String color(String texto, String codigo) {
//          return USAR_COLORES ? codigo + texto + RESET : texto;
//      }
//  
//      static void mostrarMenuPrincipal() {
//          System.out.println();
//  
//          System.out.println(color(
//                  " █████  █████  █████  ████    ███    █████     ██ ██     ██     █████  █████  ████    ███    █████\n"
//                + "██        █    ██     ██ ██  ██ ██  ██        ██ ██     ██     ██       █    ██ ██  ██ ██  ██   \n"
//                + "██        █    ████   ████   █████   ███       ███      ██     ████     █    ████   █████   ███ \n"
//                + "██        █    ██     ██ ██  ██ ██     ██       █       ██     ██       █    ██ ██  ██ ██     ██\n"
//                + " █████  █████  ██     ██ ██  ██ ██  █████        █       █████  █████    █    ██ ██  ██ ██  █████",
//                  VERDE));
//  
//          System.out.println(color("\n=== CIFRAS Y LETRAS ===", CIAN));
//          System.out.println(color("1. Concurso", VERDE));
//          System.out.println(color("2. Partida rapida", MAGENTA));
//          System.out.println(color("3. Configuracion", AMARILLO));
//          System.out.println(color("0. Salir", ROJO));
//      }
//  
//      static void configurar() {
//          boolean volver = false;
//  
//          while (!volver) {
//              System.out.println(color("\n=== CONFIGURACION ===", CIAN));
//  
//              System.out.println(color(
//                      "1. Repeticiones por prueba: " + repeticiones,
//                      VERDE));
//  
//              System.out.println(color(
//                      "2. Letras: " + cantidadLetras,
//                      MAGENTA));
//  
//              System.out.println(color(
//                      "3. Operaciones de calculo mental: " + cantidadOperaciones,
//                      AMARILLO));
//  
//              System.out.println(color("0. Volver", ROJO));
//  
//              switch (leerEntero("Opcion: ", 0, 3)) {
//                  case 1:
//                      repeticiones = leerEntero(
//                              "Repeticiones (1-20): ", 1, 20);
//                      break;
//  
//                  case 2:
//                      cantidadLetras = leerEntero(
//                              "Letras (8-30): ", 8, 30);
//                      break;
//  
//                  case 3:
//                      cantidadOperaciones = leerEntero(
//                              "Operaciones (1-50): ", 1, 50);
//                      break;
//  
//                  case 0:
//                      volver = true;
//                      break;
//  
//                  default:
//                      break;
//              }
//          }
//      }
//  
//      static int seleccionarPrueba() {
//          System.out.println(color("\n1. La cifra exacta", VERDE));
//          System.out.println(color("2. La palabra mas larga", MAGENTA));
//          System.out.println(color("3. La calculadora humana", AMARILLO));
//  
//          System.out.println(color(
//                  "4. Codigo secreto (requiere aprobacion del profesor)", CIAN));
//  
//          return leerEntero("Prueba: ", 1, 4);
//      }
//  
//      /*
//       * =====
//       * JAVI
//       * =====
//       *
//       */
//     
//      static int rondaCifras(boolean empiezaUno) {
<<<<<<< HEAD

             int objetivo = aleatorio(AZAR, 100, 999);
             String auxiliares = generarAuxiliares(AZAR);
             double uno, dos;
             if (empiezaUno) {
                 uno = turnoCifras(nombreUno, objetivo, auxiliares);
                 dos = turnoCifras(nombreDos, objetivo, auxiliares);
             } else {
                 dos = turnoCifras(nombreDos, objetivo, auxiliares);
                 uno = turnoCifras(nombreUno, objetivo, auxiliares);
             }
             System.out.println(
                     "Distancias: " + nombreUno + " = " + distancia(uno, objetivo)
                     + "; " + nombreDos + " = " + distancia(dos, objetivo));
             return ganadorCifras(uno, dos, objetivo);
                 }

           static double turnoCifras(

                          String jugador, int objetivo, String auxiliares) {
                      System.out.println(
                              "\nTurno de " + jugador + ". Objetivo: " + objetivo);
                      mostrarAuxiliares(auxiliares);
                      System.out.println(
                              "Se permiten repeticiones y resultados decimales.");
                      int indice = leerEntero(
                              "Primer auxiliar (posicion 1-6): ", 1, 6) - 1;
                      double acumulado = valorAuxiliar(auxiliares, indice);
                      for (int paso = 1; paso <= 5; paso++) {
                          boolean valida = false;
                          while (!valida) {
                              System.out.println(
                                      "Operacion " + paso + "/5. Acumulado: " + acumulado);
                              char op = leerOperacion();
                              int posicion = leerEntero(
                                      "Auxiliar (posicion 1-6): ", 1, 6) - 1;
                              double segundo = valorAuxiliar(auxiliares, posicion);
                              valida = operacionValida(acumulado, segundo, op);
                              if (valida) {
                                  acumulado = calcular(acumulado, segundo, op);
                                  System.out.println("Resultado: " + acumulado);
                              } else {
                                  System.out.println(
                                          "Operacion no valida. Repite este paso.");
                              }
                          }
                      }
                      return acumulado;
                  }

           static void mostrarAuxiliares(String auxiliares) {
               for (int i = 0; i < auxiliares.length(); i++) {
                   System.out.println(
                           (i + 1) + ": " + valorAuxiliar(auxiliares, i));
               }
           }

           static String generarAuxiliares(java.util.Random generador) {

                    // Cada caracter representa un auxiliar.
                    // Los caracteres '1' a '9' representan esos numeros.
                    // El caracter 'A' representa el numero 10.

                    String auxiliares = "";
                    for (int i = 0; i < 6; i++) {
                        int valor = aleatorio(generador, 1, 10);
                        auxiliares += valor == 10 ? 'A' : (char) ('0' + valor);
                    }
                    return auxiliares;
                }

           static int valorAuxiliar(String auxiliares, int posicion) {

                    char simbolo = auxiliares.charAt(posicion);
                    return simbolo == 'A' ? 10 : simbolo - '0';
                }
           static double distancia(double resultado, int objetivo) {

                     return Math.abs(resultado - objetivo);
                }

           static int ganadorCifras(double uno, double dos, int objetivo) {
                     return compararMenor(
                             distancia(uno, objetivo),
                             distancia(dos, objetivo));
                 }
=======
//
//             int objetivo = aleatorio(AZAR, 100, 999);
//             String auxiliares = generarAuxiliares(AZAR);
//             double uno, dos;
//             if (empiezaUno) {
//                 uno = turnoCifras(nombreUno, objetivo, auxiliares);
//                 dos = turnoCifras(nombreDos, objetivo, auxiliares);
//             } else {
//                 dos = turnoCifras(nombreDos, objetivo, auxiliares);
//                 uno = turnoCifras(nombreUno, objetivo, auxiliares);
//             }
//             System.out.println(
//                     "Distancias: " + nombreUno + " = " + distancia(uno, objetivo)
//                     + "; " + nombreDos + " = " + distancia(dos, objetivo));
//             return ganadorCifras(uno, dos, objetivo);
//                 }
//
//           static double turnoCifras(
//
//                          String jugador, int objetivo, String auxiliares) {
//                      System.out.println(
//                              "\nTurno de " + jugador + ". Objetivo: " + objetivo);
//                      mostrarAuxiliares(auxiliares);
//                      System.out.println(
//                              "Se permiten repeticiones y resultados decimales.");
//                      int indice = leerEntero(
//                              "Primer auxiliar (posicion 1-6): ", 1, 6) - 1;
//                      double acumulado = valorAuxiliar(auxiliares, indice);
//                      for (int paso = 1; paso <= 5; paso++) {
//                          boolean valida = false;
//                          while (!valida) {
//                              System.out.println(
//                                      "Operacion " + paso + "/5. Acumulado: " + acumulado);
//                              char op = leerOperacion();
//                              int posicion = leerEntero(
//                                      "Auxiliar (posicion 1-6): ", 1, 6) - 1;
//                              double segundo = valorAuxiliar(auxiliares, posicion);
//                              valida = operacionValida(acumulado, segundo, op);
//                              if (valida) {
//                                  acumulado = calcular(acumulado, segundo, op);
//                                  System.out.println("Resultado: " + acumulado);
//                              } else {
//                                  System.out.println(
//                                          "Operacion no valida. Repite este paso.");
//                              }
//                          }
//                      }
//                      return acumulado;
//                  }
//       
//           static void mostrarAuxiliares(String auxiliares) {
//               for (int i = 0; i < auxiliares.length(); i++) {
//                   System.out.println(
//                           (i + 1) + ": " + valorAuxiliar(auxiliares, i));
//               }
//           }
//
//           static String generarAuxiliares(java.util.Random generador) {
//                    
//                    // Cada caracter representa un auxiliar.
//                    // Los caracteres '1' a '9' representan esos numeros.
//                    // El caracter 'A' representa el numero 10.
//                    
//                    String auxiliares = "";
//                    for (int i = 0; i < 6; i++) {
//                        int valor = aleatorio(generador, 1, 10);
//                        auxiliares += valor == 10 ? 'A' : (char) ('0' + valor);
//                    }
//                    return auxiliares;
//                }
//
//           static int valorAuxiliar(String auxiliares, int posicion) {
//
//                    char simbolo = auxiliares.charAt(posicion);
//                    return simbolo == 'A' ? 10 : simbolo - '0';
//                }
//           static double distancia(double resultado, int objetivo) {
//
//                     return Math.abs(resultado - objetivo);
//                }
//
//           static int ganadorCifras(double uno, double dos, int objetivo) {
//                     return compararMenor(
//                             distancia(uno, objetivo),
//                             distancia(dos, objetivo));
//                 }
>>>>>>> main
//
//  /*
//   * ======
//   * JAVI: 
//   * ======
//   *
//   */
//  
//         static int rondaLetras(boolean empiezaUno, int cantidad) {
//             
//                     String letras = generarLetras(cantidad, AZAR);
//     
//                     System.out.println("Letras disponibles: " + letras);
//     
//                     int uno, dos;
//     
//                     if (empiezaUno) {
//                         uno = turnoLetras(nombreUno, nombreDos, letras);
//                         dos = turnoLetras(nombreDos, nombreUno, letras);
//                     } else {
//                         dos = turnoLetras(nombreDos, nombreUno, letras);
//                         uno = turnoLetras(nombreUno, nombreDos, letras);
//                     }
//     
//                     System.out.println("Longitudes validas: " + uno + " y " + dos);
//     
//                     return compararMayor(uno, dos);
//                 }
//     
//         static int turnoLetras(String jugador, String rival, String letras) {
//     
//                     System.out.println(
//                             "\nTurno de " + jugador + ". Letras: " + letras);
//     
//                     String palabra = normalizar(
//                             leerTexto("Palabra (o - para pasar): "));
//     
//                     if (palabra.equals("-")) {
//                         return 0;
//                     }
//     
//                     System.out.println(
//                             rival + ", comprueba en un diccionario la palabra: "
//                             + palabra);
//     
//                     boolean aceptada = leerSiNo(
//                             "Existe y es valida en espanol? (s/n): ");
//     
//                     boolean letrasValidas = puedeFormarse(palabra, letras);
//     
//                     if (!letrasValidas) {
//                         System.out.println("No respeta las letras disponibles.");
//                     }
//     
//                     if (!aceptada) {
//                         System.out.println("El rival no ha validado la palabra.");
//                     }
//     
//                     return puntuarPalabra(palabra, letras, aceptada);
//                 }
//     
//         static String generarLetras(
//     
//                         int cantidad, java.util.Random generador) {
//     
//                     if (cantidad < 8) {
//                         throw new IllegalArgumentException(
//                                 "Se necesitan al menos 8 letras.");
//                     }
//     
//                     String letras = "";
//     
//                     for (int i = 0; i < 4; i++) {
//                         letras += VOCALES.charAt(
//                                 aleatorio(generador, 0, VOCALES.length() - 1));
//     
//                         letras += CONSONANTES.charAt(
//                                 aleatorio(generador, 0, CONSONANTES.length() - 1));
//                     }
//     
//     
//                     while (letras.length() < cantidad) {
//                         letras += ALFABETO.charAt(
//                                 aleatorio(generador, 0, ALFABETO.length() - 1));
//                     }
//     
//                     return mezclar(letras, generador);
//                 }
//     
//         static String mezclar(String texto, java.util.Random generador) {
//                     String mezclado = "";
//     
//                     while (!texto.isEmpty()) {
//                         int posicion = aleatorio(
//                                 generador, 0, texto.length() - 1);
//     
//                         mezclado += texto.charAt(posicion);
//     
//                         texto = texto.substring(0, posicion)
//                                 + texto.substring(posicion + 1);
//                     }
//     
//                     return mezclado;
//                 }
//     
//         static String normalizar(String palabra) {
//                     return palabra.toUpperCase(java.util.Locale.ROOT)
//                             .replace('Á', 'A')
//                             .replace('É', 'E')
//                             .replace('Í', 'I')
//                             .replace('Ó', 'O')
//                             .replace('Ú', 'U')
//                             .replace('Ü', 'U');
//                 }
//     
//         static boolean puedeFormarse(String palabra, String letras) {
//                     if (palabra.isEmpty()) {
//                         return false;
//                     }
//     
//                     palabra = normalizar(palabra);
//                     String restantes = normalizar(letras);
//     
//                     for (int i = 0; i < palabra.length(); i++) {
//                         char letra = palabra.charAt(i);
//     
//                         if (ALFABETO.indexOf(letra) < 0) {
//                             return false;
//                         }
//     
//                         int posicion = restantes.indexOf(letra);
//     
//                         if (posicion < 0) {
//                             return false;
//                         }
//     
//                         // Elimina cada letra utilizada para respetar sus repeticiones.
//                         restantes = restantes.substring(0, posicion)
//                                 + restantes.substring(posicion + 1);
//                     }
//     
//                     return true;
//                 }
//     
//         static int puntuarPalabra(
//                     String palabra, String letras, boolean aceptada) {
//     
//                 return aceptada && puedeFormarse(palabra, letras)
//                         ? normalizar(palabra).length()
//                         : 0;
//                 }
//     
//  /*
//   * =====
//   * JAVI:
//   * =====
//   *
//   */
//
//        static int leerEntero(String mensaje, int minimo, int maximo) {
//                    while (true) {
//                        System.out.print(mensaje);
//                        String texto = siguienteLinea().trim();
//    
//                        try {
//                            int valor = Integer.parseInt(texto);
//    
//                            if (enRango(valor, minimo, maximo)) {
//                                return valor;
//                            }
//                        } catch (NumberFormatException e) {
//                            // Una entrada incorrecta no termina la partida.
//                        }
//    
//                        System.out.println(
//                                "Introduce un entero entre "
//                                + minimo + " y " + maximo + ".");
//                    }
//                }
//    
//        static double leerDecimal(String mensaje) {
//                    while (true) {
//                        System.out.print(mensaje);
//    
//                        try {
//                            double valor = Double.parseDouble(
//                                    siguienteLinea().trim().replace(',', '.'));
//    
//                            if (numeroFinito(valor)) {
//                                return valor;
//                            }
//                        } catch (NumberFormatException e) {
//                            // Se acepta coma decimal y se rechaza el formato invalido.
//                        }
//    
//                        System.out.println("Introduce un numero finito valido.");
//                    }
//                }
//    
//        static String siguienteLinea() {
//                    if (!TECLADO.hasNextLine()) {
//                        System.out.println(
//                                "\nEntrada finalizada. Se cierra el programa.");
//    
//                        System.exit(0);
//                    }
//    
//                    return TECLADO.nextLine();
//                }
//    
//        static String leerTexto(String mensaje) {
//                    while (true) {
//                        System.out.print(mensaje);
//                        String texto = siguienteLinea().trim();
//    
//                        if (!texto.isEmpty()) {
//                            return texto;
//                        }
//    
//                        System.out.println("No puedes dejar la entrada vacia.");
//                    }
//                }
//    
//        static boolean leerSiNo(String mensaje) {
//                    while (true) {
//                        String respuesta = leerTexto(mensaje);
//    
//                        if (respuesta.equalsIgnoreCase("s")) {
//                            return true;
//                        }
//    
//                        if (respuesta.equalsIgnoreCase("n")) {
//                            return false;
//                        }
//    
//                        System.out.println("Responde s o n.");
//                    }
//                }
//    
//        static char leerOperacion() {
//                    while (true) {
//                        String texto = leerTexto("Operacion (+, -, *, /): ");
//    
//                        if (simboloOperacionValido(texto)) {
//                            return texto.charAt(0);
//                        }
//    
//                        System.out.println("Operacion desconocida.");
//                    }
//                }
//    
//        static boolean simboloOperacionValido(String texto) {
//                    return texto.length() == 1
//                            && "+-*/".indexOf(texto.charAt(0)) >= 0;
//                }
//    
//        /*
//         * =====
//         * JAVI:
//         * =====
//         */