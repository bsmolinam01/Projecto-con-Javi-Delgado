import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class CifrasYLetras {

    // =====================================================
    // CONSTANTES Y VARIABLES GLOBALES
    // =====================================================

    static final Scanner TECLADO = new Scanner(System.in);
    static final Random AZAR = new Random();

    static final boolean USAR_COLORES = true;

    static final String RESET = "\033[0m";
    static final String CIAN = "\033[1;36m";
    static final String VERDE = "\033[1;32m";
    static final String MAGENTA = "\033[1;35m";
    static final String AMARILLO = "\033[1;33m";
    static final String ROJO = "\033[1;31m";

    static final String VOCALES = "AEIOU";
    static final String CONSONANTES = "BCDFGHJKLMNÑPQRSTVWXYZ";
    static final String ALFABETO = VOCALES + CONSONANTES;
    static final String LETRAS_CODIGO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static final int INTENTOS_CODIGO = 5;

    static int repeticiones = 1;
    static int cantidadLetras = 10;
    static int cantidadOperaciones = 5;

    static String nombreUno;
    static String nombreDos;

    static int puntosGlobalesUno;
    static int puntosGlobalesDos;

    static int puntosPruebaUno;
    static int puntosPruebaDos;

    // =====================================================
    // PROGRAMA PRINCIPAL
    // =====================================================

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();

            int opcion = leerEntero("Opcion: ", 0, 3);

            switch (opcion) {
                case 1:
                    iniciarPartida(true);
                    break;

                case 2:
                    iniciarPartida(false);
                    break;

                case 3:
                    configurar();
                    break;

                case 0:
                    salir = true;
                    break;

                default:
                    break;
            }
        }

        System.out.println("Hasta pronto.");
    }

    // =====================================================
    // MENUS Y CONFIGURACION
    // =====================================================

    static String color(String texto, String codigo) {
        return USAR_COLORES ? codigo + texto + RESET : texto;
    }

    static void mostrarMenuPrincipal() {
        System.out.println(color(
                "\n===================================="
                + "\n          CIFRAS Y LETRAS"
                + "\n====================================", CIAN));

        System.out.println(color("1. Concurso", VERDE));
        System.out.println(color("2. Partida rapida", MAGENTA));
        System.out.println(color("3. Configuracion", AMARILLO));
        System.out.println(color("0. Salir", ROJO));
    }

    static void configurar() {
        boolean volver = false;

        while (!volver) {
            System.out.println(color(
                    "\n=== CONFIGURACION ===", CIAN));

            System.out.println(
                    "1. Repeticiones por prueba: " + repeticiones);

            System.out.println(
                    "2. Cantidad de letras: " + cantidadLetras);

            System.out.println(
                    "3. Operaciones de calculo mental: "
                    + cantidadOperaciones);

            System.out.println("0. Volver");

            int opcion = leerEntero("Opcion: ", 0, 3);

            switch (opcion) {
                case 1:
                    repeticiones = leerEntero(
                            "Repeticiones (1-20): ", 1, 20);
                    break;

                case 2:
                    cantidadLetras = leerEntero(
                            "Letras (8-30): ", 8, 30);
                    break;

                case 3:
                    cantidadOperaciones = leerEntero(
                            "Operaciones (1-50): ", 1, 50);
                    break;

                case 0:
                    volver = true;
                    break;

                default:
                    break;
            }
        }
    }

    static int seleccionarPrueba() {
        System.out.println(color(
                "\n=== SELECCIONAR PRUEBA ===", CIAN));

        System.out.println("1. La cifra exacta");
        System.out.println("2. La palabra mas larga");
        System.out.println("3. La calculadora humana");
        System.out.println("4. Codigo secreto");

        return leerEntero("Prueba: ", 1, 4);
    }

    static String nombrePrueba(int prueba) {
        switch (prueba) {
            case 1:
                return "La cifra exacta";

            case 2:
                return "La palabra mas larga";

            case 3:
                return "La calculadora humana";

            case 4:
                return "Codigo secreto";

            default:
                return "Prueba desconocida";
        }
    }

    // =====================================================
    // CONTROL DE PARTIDA Y MARCADORES
    // =====================================================

    static void iniciarPartida(boolean concurso) {
        nombreUno = leerTexto("Nombre del jugador 1: ");
        nombreDos = leerTexto("Nombre del jugador 2: ");

        puntosGlobalesUno = 0;
        puntosGlobalesDos = 0;

        // Se guardan los ajustes al empezar la partida.
        int repeticionesPartida = repeticiones;
        int letrasPartida = cantidadLetras;
        int operacionesPartida = cantidadOperaciones;

        if (concurso) {
            for (int prueba = 1; prueba <= 4; prueba++) {
                jugarPrueba(
                        prueba,
                        repeticionesPartida,
                        letrasPartida,
                        operacionesPartida);

                int ganador = compararMayor(
                        puntosPruebaUno, puntosPruebaDos);

                if (ganador > 0) {
                    puntosGlobalesUno++;
                } else if (ganador < 0) {
                    puntosGlobalesDos++;
                }

                mostrarMarcadorGlobal();
            }

            System.out.println(color(
                    "\n=== RESULTADO DEL CONCURSO ===", CIAN));

            anunciarGanador(
                    compararMayor(
                            puntosGlobalesUno,
                            puntosGlobalesDos));

        } else {
            int prueba = seleccionarPrueba();

            jugarPrueba(
                    prueba,
                    repeticionesPartida,
                    letrasPartida,
                    operacionesPartida);

            System.out.println(color(
                    "\n=== RESULTADO DE LA PARTIDA RAPIDA ===", CIAN));

            anunciarGanador(
                    compararMayor(
                            puntosPruebaUno,
                            puntosPruebaDos));
        }
    }

    static void jugarPrueba(
            int prueba, int rondas, int letras, int operaciones) {

        puntosPruebaUno = 0;
        puntosPruebaDos = 0;

        System.out.println(color(
                "\n=== " + nombrePrueba(prueba) + " ===", MAGENTA));

        for (int ronda = 1; ronda <= rondas; ronda++) {
            System.out.println(
                    "\nRonda " + ronda + " de " + rondas);

            boolean empiezaUno = ronda % 2 == 1;
            int resultado;

            switch (prueba) {
                case 1:
                    resultado = rondaCifras(empiezaUno);
                    break;

                case 2:
                    resultado = rondaLetras(empiezaUno, letras);
                    break;

                case 3:
                    resultado = rondaCalculadora(
                            empiezaUno, operaciones);
                    break;

                case 4:
                    resultado = rondaCodigoSecreto(empiezaUno);
                    break;

                default:
                    resultado = 0;
                    break;
            }

            // Cada ronda ganada aporta un punto a la prueba.
            if (resultado > 0) {
                puntosPruebaUno++;
            } else if (resultado < 0) {
                puntosPruebaDos++;
            }

            anunciarGanador(resultado);

            System.out.println(
                    "Marcador de la prueba: "
                    + nombreUno + " " + puntosPruebaUno
                    + " - " + puntosPruebaDos + " " + nombreDos);
        }
    }

    static void mostrarMarcadorGlobal() {
        System.out.println(color(
                "\nMarcador global: "
                + nombreUno + " " + puntosGlobalesUno
                + " - " + puntosGlobalesDos + " " + nombreDos,
                AMARILLO));
    }

    static void anunciarGanador(int resultado) {
        if (resultado > 0) {
            System.out.println(color(
                    "Gana " + nombreUno + ".", VERDE));
        } else if (resultado < 0) {
            System.out.println(color(
                    "Gana " + nombreDos + ".", VERDE));
        } else {
            System.out.println(color("Empate.", AMARILLO));
        }
    }

    // =====================================================
    // PRUEBA 1: LA CIFRA EXACTA
    // =====================================================

    static int rondaCifras(boolean empiezaUno) {
        int objetivo = aleatorio(AZAR, 100, 999);
        String auxiliares = generarAuxiliares();

        double resultadoUno;
        double resultadoDos;

        if (empiezaUno) {
            resultadoUno = turnoCifras(
                    nombreUno, objetivo, auxiliares);

            resultadoDos = turnoCifras(
                    nombreDos, objetivo, auxiliares);
        } else {
            resultadoDos = turnoCifras(
                    nombreDos, objetivo, auxiliares);

            resultadoUno = turnoCifras(
                    nombreUno, objetivo, auxiliares);
        }

        System.out.println(
                "\nDistancia de " + nombreUno + ": "
                + distancia(resultadoUno, objetivo));

        System.out.println(
                "Distancia de " + nombreDos + ": "
                + distancia(resultadoDos, objetivo));

        return compararMenor(
                distancia(resultadoUno, objetivo),
                distancia(resultadoDos, objetivo));
    }

    static double turnoCifras(
            String jugador, int objetivo, String auxiliares) {

        System.out.println(color(
                "\nTurno de " + jugador, CIAN));

        System.out.println("Objetivo: " + objetivo);
        mostrarAuxiliares(auxiliares);

        System.out.println(
                "Puedes repetir auxiliares y obtener decimales.");

        int posicion = leerEntero(
                "Primer auxiliar (posicion 1-6): ", 1, 6) - 1;

        double acumulado = valorAuxiliar(auxiliares, posicion);

        for (int paso = 1; paso <= 5; paso++) {
            boolean valida = false;

            while (!valida) {
                System.out.println(
                        "\nOperacion " + paso + "/5"
                        + ". Acumulado: " + acumulado);

                char operacion = leerOperacion();

                posicion = leerEntero(
                        "Auxiliar (posicion 1-6): ", 1, 6) - 1;

                double segundo = valorAuxiliar(
                        auxiliares, posicion);

                valida = operacionValida(
                        acumulado, segundo, operacion);

                if (valida) {
                    acumulado = calcular(
                            acumulado, segundo, operacion);

                    System.out.println(
                            "Resultado: " + acumulado);
                } else {
                    System.out.println(
                            "Operacion no valida. Repite este paso.");
                }
            }
        }

        return acumulado;
    }

    static String generarAuxiliares() {
        String auxiliares = "";

        for (int i = 0; i < 6; i++) {
            int valor = aleatorio(AZAR, 1, 10);

            // A representa el numero 10.
            auxiliares += valor == 10
                    ? 'A' : (char) ('0' + valor);
        }

        return auxiliares;
    }

    static int valorAuxiliar(String auxiliares, int posicion) {
        char simbolo = auxiliares.charAt(posicion);

        return simbolo == 'A' ? 10 : simbolo - '0';
    }

    static void mostrarAuxiliares(String auxiliares) {
        System.out.println("Auxiliares disponibles:");

        for (int i = 0; i < auxiliares.length(); i++) {
            System.out.println(
                    (i + 1) + ": " + valorAuxiliar(auxiliares, i));
        }
    }

    static double distancia(double resultado, int objetivo) {
        return Math.abs(resultado - objetivo);
    }

    // =====================================================
    // PRUEBA 2: LA PALABRA MAS LARGA
    // =====================================================

    static int rondaLetras(boolean empiezaUno, int cantidad) {
        String letras = generarLetras(cantidad);

        int longitudUno;
        int longitudDos;

        System.out.println("\nLetras disponibles: " + letras);

        if (empiezaUno) {
            longitudUno = turnoLetras(
                    nombreUno, nombreDos, letras);

            longitudDos = turnoLetras(
                    nombreDos, nombreUno, letras);
        } else {
            longitudDos = turnoLetras(
                    nombreDos, nombreUno, letras);

            longitudUno = turnoLetras(
                    nombreUno, nombreDos, letras);
        }

        System.out.println(
                nombreUno + ": " + longitudUno + " letras validas.");

        System.out.println(
                nombreDos + ": " + longitudDos + " letras validas.");

        return compararMayor(longitudUno, longitudDos);
    }

    static int turnoLetras(
            String jugador, String rival, String letras) {

        System.out.println(color(
                "\nTurno de " + jugador, CIAN));

        System.out.println("Letras: " + letras);

        String palabra = normalizar(
                leerTexto("Palabra (o - para pasar): "));

        if (palabra.equals("-")) {
            return 0;
        }

        if (!puedeFormarse(palabra, letras)) {
            System.out.println(
                    "La palabra no respeta las letras disponibles.");
            return 0;
        }

        System.out.println(
                rival + ", comprueba en un diccionario: " + palabra);

        boolean aceptada = leerSiNo(
                "Existe y es valida en espanol? (s/n): ");

        if (!aceptada) {
            System.out.println("El rival no ha validado la palabra.");
            return 0;
        }

        return palabra.length();
    }

    static String generarLetras(int cantidad) {
        String letras = "";

        // Garantiza cuatro vocales y cuatro consonantes.
        for (int i = 0; i < 4; i++) {
            letras += VOCALES.charAt(
                    aleatorio(AZAR, 0, VOCALES.length() - 1));

            letras += CONSONANTES.charAt(
                    aleatorio(AZAR, 0, CONSONANTES.length() - 1));
        }

        while (letras.length() < cantidad) {
            letras += ALFABETO.charAt(
                    aleatorio(AZAR, 0, ALFABETO.length() - 1));
        }

        return mezclar(letras);
    }

    static String mezclar(String texto) {
        String mezclado = "";

        while (!texto.isEmpty()) {
            int posicion = aleatorio(
                    AZAR, 0, texto.length() - 1);

            mezclado += texto.charAt(posicion);

            texto = texto.substring(0, posicion)
                    + texto.substring(posicion + 1);
        }

        return mezclado;
    }

    static String normalizar(String texto) {
        return texto.toUpperCase(Locale.ROOT)
                .replace('Á', 'A')
                .replace('É', 'E')
                .replace('Í', 'I')
                .replace('Ó', 'O')
                .replace('Ú', 'U')
                .replace('Ü', 'U');
    }

    static boolean puedeFormarse(String palabra, String letras) {
        if (palabra.isEmpty()) {
            return false;
        }

        palabra = normalizar(palabra);
        String restantes = normalizar(letras);

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);

            if (ALFABETO.indexOf(letra) < 0) {
                return false;
            }

            int posicion = restantes.indexOf(letra);

            if (posicion < 0) {
                return false;
            }

            // Elimina una aparicion de cada letra utilizada.
            restantes = restantes.substring(0, posicion)
                    + restantes.substring(posicion + 1);
        }

        return true;
    }

    // =====================================================
    // PRUEBA 3: LA CALCULADORA HUMANA
    // =====================================================

    static int rondaCalculadora(
            boolean empiezaUno, int operaciones) {

        double tiempoUno;
        double tiempoDos;

        System.out.println(
                "\nResuelve las operaciones lo mas rapido posible.");

        System.out.println(
                "Cada error suma 20 segundos al tiempo final.");

        System.out.println(
                "Las divisiones generadas tienen resultado entero.");

        // Cada jugador recibe una cadena aleatoria independiente.
        if (empiezaUno) {
            tiempoUno = turnoCalculadora(nombreUno, operaciones);
            tiempoDos = turnoCalculadora(nombreDos, operaciones);
        } else {
            tiempoDos = turnoCalculadora(nombreDos, operaciones);
            tiempoUno = turnoCalculadora(nombreUno, operaciones);
        }

        System.out.printf(
                Locale.ROOT,
                "%n%s: %.3f segundos con penalizaciones.%n",
                nombreUno, tiempoUno);

        System.out.printf(
                Locale.ROOT,
                "%s: %.3f segundos con penalizaciones.%n",
                nombreDos, tiempoDos);

        // Se compara con la misma precision que se muestra.
        return compararMenor(
                redondearMilesimas(tiempoUno),
                redondearMilesimas(tiempoDos));
    }

    static double turnoCalculadora(
            String jugador, int cantidad) {

        System.out.println(color(
                "\nTurno de " + jugador, CIAN));

        leerTexto("Escribe listo y pulsa Enter para comenzar: ");

        int actual = aleatorio(AZAR, 1, 100);
        int errores = 0;

        long inicio = System.nanoTime();

        for (int i = 1; i <= cantidad; i++) {
            char operacion = generarOperacionMental();
            int valor = generarValorMental(actual, operacion);

            double correcto = calcular(actual, valor, operacion);

            System.out.println(
                    "\nOperacion " + i + "/" + cantidad);

            System.out.println(
                    actual + " " + operacion + " " + valor + " = ?");

            double respuesta = leerDecimal("Respuesta: ");

            if (respuestaCorrecta(respuesta, correcto)) {
                System.out.println("Correcto.");
            } else {
                errores++;
                System.out.println("Incorrecto. Penalizacion: 20 segundos.");
            }

            System.out.println(
                    "Resultado correcto: " + (int) correcto);

            // Siempre continua con el resultado correcto.
            actual = (int) correcto;
        }

        long fin = System.nanoTime();

        double segundos = (fin - inicio) / 1_000_000_000.0;
        double total = calcularTiempoPenalizado(segundos, errores);

        System.out.println("Errores: " + errores);

        return total;
    }

    static char generarOperacionMental() {
        return "+-*/".charAt(aleatorio(AZAR, 0, 3));
    }

    static int generarValorMental(int actual, char operacion) {
        if (operacion == '+' || operacion == '-') {
            return aleatorio(AZAR, 10, 99);
        }

        if (operacion == '*') {
            // Limita el crecimiento para mantener calculos manejables.
            if (Math.abs(actual) > 10000) {
                return 1;
            }

            return aleatorio(AZAR, 1, 9);
        }

        // El divisor nunca es cero y debe dividir exactamente.
        int divisor;

        do {
            divisor = aleatorio(AZAR, 1, 9);
        } while (actual % divisor != 0);

        return divisor;
    }

    static boolean respuestaCorrecta(
            double respuesta, double correcto) {

        return Math.abs(respuesta - correcto) < 0.000001;
    }

    static double calcularTiempoPenalizado(
            double segundos, int errores) {

        return segundos + errores * 20.0;
    }

    static double redondearMilesimas(double valor) {
        return Math.round(valor * 1000.0) / 1000.0;
    }

    // =====================================================
    // PRUEBA 4: CODIGO SECRETO
    // =====================================================

    static int rondaCodigoSecreto(boolean empiezaUno) {
        String secreto = generarCodigoSecreto();

        int puntuacionUno = 0;
        int puntuacionDos = 0;

        boolean acertoUno = false;
        boolean acertoDos = false;

        System.out.println(color(
                "\n=== CODIGO SECRETO ===", CIAN));

        System.out.println(
                "Descubre una contraseña de cuatro numeros y una letra.");

        System.out.println("Ejemplo: 4827M");
        System.out.println(
                "Los numeros pueden repetirse y empezar por cero.");

        System.out.println(
                "La letra va de A a Z, sin incluir la Ñ.");

        System.out.println(
                "Hay cinco intentos y una pista nueva en cada uno.");

        System.out.println(
                "Puntos por acertar: 5, 4, 3, 2 o 1.");

        System.out.println(
                "No mireis mientras escribe el otro jugador.");

        for (int intento = 1;
             intento <= INTENTOS_CODIGO
                     && (!acertoUno || !acertoDos);
             intento++) {

            System.out.println(color(
                    "\n--- INTENTO " + intento
                    + " DE " + INTENTOS_CODIGO + " ---",
                    AMARILLO));

            for (int pista = 1; pista <= intento; pista++) {
                System.out.println(
                        "Pista " + pista + ": "
                        + obtenerPistaCodigo(secreto, pista));
            }

            String respuestaUno = "";
            String respuestaDos = "";

            boolean primeroUno = intento % 2 == 1
                    ? empiezaUno : !empiezaUno;

            // Se recogen ambas respuestas antes de corregir.
            if (primeroUno) {
                if (!acertoUno) {
                    respuestaUno = pedirIntentoCodigo(nombreUno);
                }

                if (!acertoDos) {
                    respuestaDos = pedirIntentoCodigo(nombreDos);
                }
            } else {
                if (!acertoDos) {
                    respuestaDos = pedirIntentoCodigo(nombreDos);
                }

                if (!acertoUno) {
                    respuestaUno = pedirIntentoCodigo(nombreUno);
                }
            }

            if (!acertoUno) {
                acertoUno = codigoAcertado(respuestaUno, secreto);

                mostrarResultadoIntento(
                        nombreUno, respuestaUno, secreto);

                if (acertoUno) {
                    puntuacionUno = puntuarCodigo(intento);
                }
            }

            if (!acertoDos) {
                acertoDos = codigoAcertado(respuestaDos, secreto);

                mostrarResultadoIntento(
                        nombreDos, respuestaDos, secreto);

                if (acertoDos) {
                    puntuacionDos = puntuarCodigo(intento);
                }
            }
        }

        System.out.println("\nLa contraseña era: " + secreto);

        System.out.println(
                nombreUno + ": " + puntuacionUno + " puntos.");

        System.out.println(
                nombreDos + ": " + puntuacionDos + " puntos.");

        return compararMayor(puntuacionUno, puntuacionDos);
    }

    static String pedirIntentoCodigo(String jugador) {
        while (true) {
            String respuesta = leerTexto(
                    jugador + ", introduce tu contraseña: ")
                    .toUpperCase(Locale.ROOT);

            if (formatoCodigoValido(respuesta)) {
                return respuesta;
            }

            System.out.println(
                    "Formato incorrecto: cuatro numeros "
                    + "seguidos de una letra de A a Z.");

            System.out.println("Ejemplo: 4827M");
            System.out.println("Esta entrada no consume un intento.");
        }
    }

    static void mostrarResultadoIntento(
            String jugador, String respuesta, String secreto) {

        if (codigoAcertado(respuesta, secreto)) {
            System.out.println(color(
                    jugador + " ha descubierto la contraseña.",
                    VERDE));
        } else {
            System.out.println(
                    jugador + ": "
                    + contarPosicionesCorrectas(respuesta, secreto)
                    + " de 5 caracteres en la posicion correcta.");
        }
    }

    static String generarCodigoSecreto() {
        String secreto = "";

        for (int i = 0; i < 4; i++) {
            secreto += aleatorio(AZAR, 0, 9);
        }

        secreto += LETRAS_CODIGO.charAt(
                aleatorio(AZAR, 0, LETRAS_CODIGO.length() - 1));

        return secreto;
    }

    static String obtenerPistaCodigo(
            String secreto, int numeroPista) {

        int primero = secreto.charAt(0) - '0';
        int segundo = secreto.charAt(1) - '0';
        int tercero = secreto.charAt(2) - '0';
        int cuarto = secreto.charAt(3) - '0';

        switch (numeroPista) {
            case 1:
                return "Si al primer numero le sumas 7, obtienes "
                        + (primero + 7) + ".";

            case 2:
                return "El segundo numero multiplicado por 3 es "
                        + (segundo * 3) + ".";

            case 3:
                return "El primer numero mas el tercero suman "
                        + (primero + tercero) + ".";

            case 4:
                return "El cuarto numero mas el segundo suman "
                        + (cuarto + segundo) + ".";

            case 5:
                int posicionLetra = secreto.charAt(4) - 'A' + 1;

                return "La letra ocupa la posicion "
                        + posicionLetra
                        + " en ABCDEFGHIJKLMNOPQRSTUVWXYZ "
                        + "(A=1, B=2, ..., Z=26).";

            default:
                return "No hay mas pistas.";
        }
    }

    static boolean formatoCodigoValido(String codigo) {
        if (codigo.length() != 5) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            char caracter = codigo.charAt(i);

            if (caracter < '0' || caracter > '9') {
                return false;
            }
        }

        char letra = codigo.charAt(4);

        return letra >= 'A' && letra <= 'Z';
    }

    static boolean codigoAcertado(
            String respuesta, String secreto) {

        return respuesta.equals(secreto);
    }

    static int contarPosicionesCorrectas(
            String respuesta, String secreto) {

        if (!formatoCodigoValido(respuesta)
                || !formatoCodigoValido(secreto)) {
            return 0;
        }

        int correctas = 0;

        for (int i = 0; i < secreto.length(); i++) {
            if (respuesta.charAt(i) == secreto.charAt(i)) {
                correctas++;
            }
        }

        return correctas;
    }

    static int puntuarCodigo(int intento) {
        if (intento < 1 || intento > INTENTOS_CODIGO) {
            return 0;
        }

        return INTENTOS_CODIGO + 1 - intento;
    }

    // =====================================================
    // LOGICA COMUN
    // Estas funciones no leen ni escriben en consola.
    // =====================================================

    static int aleatorio(
            Random generador, int minimo, int maximo) {

        return generador.nextInt(maximo - minimo + 1) + minimo;
    }

    static boolean operacionValida(
            double primero, double segundo, char operacion) {

        return "+-*".indexOf(operacion) >= 0
                || (operacion == '/' && segundo != 0);
    }

    static double calcular(
            double primero, double segundo, char operacion) {

        switch (operacion) {
            case '+':
                return primero + segundo;

            case '-':
                return primero - segundo;

            case '*':
                return primero * segundo;

            case '/':
                if (segundo != 0) {
                    return primero / segundo;
                }
                break;

            default:
                break;
        }

        throw new IllegalArgumentException("Operacion no valida.");
    }

    static int compararMenor(double uno, double dos) {
        return Double.compare(dos, uno);
    }

    static int compararMayor(int uno, int dos) {
        return Integer.compare(uno, dos);
    }

    static boolean enRango(int valor, int minimo, int maximo) {
        return valor >= minimo && valor <= maximo;
    }

    static boolean simboloOperacionValido(String texto) {
        return texto.length() == 1
                && "+-*/".indexOf(texto.charAt(0)) >= 0;
    }

    // =====================================================
    // ENTRADA POR CONSOLA
    // =====================================================

    static int leerEntero(
            String mensaje, int minimo, int maximo) {

        while (true) {
            System.out.print(mensaje);
            String texto = siguienteLinea().trim();

            try {
                int valor = Integer.parseInt(texto);

                if (enRango(valor, minimo, maximo)) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Una entrada incorrecta no interrumpe la partida.
            }

            System.out.println(
                    "Introduce un entero entre "
                    + minimo + " y " + maximo + ".");
        }
    }

    static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            String texto = siguienteLinea()
                    .trim()
                    .replace(',', '.');

            try {
                double valor = Double.parseDouble(texto);

                if (Double.isFinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Se vuelve a solicitar una entrada valida.
            }

            System.out.println("Introduce un numero finito valido.");
        }
    }

    static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);

            String texto = siguienteLinea().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("No puedes dejar la entrada vacia.");
        }
    }

    static boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje);

            if (respuesta.equalsIgnoreCase("s")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Responde s o n.");
        }
    }

    static char leerOperacion() {
        while (true) {
            String texto = leerTexto("Operacion (+, -, *, /): ");

            if (simboloOperacionValido(texto)) {
                return texto.charAt(0);
            }

            System.out.println("Operacion desconocida.");
        }
    }

    static String siguienteLinea() {
        if (!TECLADO.hasNextLine()) {
            System.out.println(
                    "\nEntrada finalizada. Se cierra el programa.");

            System.exit(0);
        }

        return TECLADO.nextLine();
    }
}