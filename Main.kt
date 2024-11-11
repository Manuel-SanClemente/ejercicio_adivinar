import kotlin.random.Random
import java.io.File

fun generar(longitud: Int): MutableMap<Int, Int> {
    val mapa = mutableMapOf<Int, Int>()
    val numerosGenerados = mutableListOf<Int>()  // Lista para almacenar los números generados

    while (numerosGenerados.size < longitud) {
        val numeroAleatorio = Random.nextInt(0, 5)
        if (!numerosGenerados.contains(numeroAleatorio)) {  // Verificamos si el número ya fue generado
            numerosGenerados.add(numeroAleatorio)
        }
    }

    // Ahora agregamos los números únicos al mapa
    for (i in 0 until longitud) {
        mapa[i] = numerosGenerados[i]
    }

    return mapa
}


fun main() {

    //Colores
    val BG_RED = "\u001B[41m"
    val BG_GREEN = "\u001B[42m"
    val BG_YELLOW = "\u001B[43m"
    val RESET = "\u001B[0m"
    val BLACK = "\u001B[30m"
    val GREEN = "\u001B[32m"

    //DOCs
    val file = File("src/mifichero.txt")
    val contenido = file.readText()

    //Opciones
    println("${GREEN}1. Jugar")
    println("2. Ver traza de último intento")
    println("3. Borrar lo contenido en la traza hasta ahora${RESET}")
    print("opción: ")
    var eleccion= readlnOrNull()

    while (eleccion!=null) {
        //Eleccion 1: Generar Numero aleatorio
        if (eleccion == "1") {
            //nota: para que el bucle funcione correctamente, se le debe sumar mas uno, pues el bucle cuenta solo las sumas para llegar hasta el numero (es decir, si se quieren 2 intentos, esta variable debe ser 3)
            var intentos=3
            for (i in 1 until intentos) {
                var num = generar(4)
                var vnum1 = num.get(0).toString()
                var vnum2 = num.get(1).toString()
                var vnum3 = num.get(2).toString()
                var vnum4 = num.get(3).toString()
                var numsec = vnum1 + vnum2 + vnum3 + vnum4
                println("teclea un numero de 4 cifras sin numeros repetidos:")
                var entrada = readln()

                var contadorcoin=0

                if (entrada.length == 4) {
                    var contadorcar = 0
                    for (i in 0 until 4) {
                        if (entrada[i] == numsec[i]) {
                            contadorcar++
                        } else {
                            continue
                        }
                    }
                    for (i in 0 until 4) {
                        if (entrada.contains(numsec[i])) {
                            contadorcoin++
                        }
                    }

                    if (contadorcar==4) {
                        println("${BG_GREEN}${BLACK}¡Lo has adivinado!${RESET}")
                        println()
                    }
                    else {
                        println("$entrada ${BG_GREEN}${BLACK} $contadorcar ${BG_YELLOW} $contadorcoin ${RESET}")
                        println()
                    }

                    contadorcar.toString()
                    file.appendText("Intento $i: Numero secreto $numsec, Aciertos: $contadorcar, Coincidencias: $contadorcoin\n")
                } else {
                    println("${BG_RED}${BLACK}La entrada que has introducido es invalida${RESET}")
                    println()
                }
            }
            print("opción: ")
            eleccion = readln()
        }

        //Eleccion 2: Generar Lista anterior
        else if (eleccion == "2") {
            val content = File("src/mifichero.txt").readText()
            println()
            print(content)
            println()
            print("opción: ")
            eleccion= readln()
        }

        //Eleccion 3: Fin
        else if (eleccion == "3") {
            file.writeText("")
            println()
            print("opción: ")
            println()
            eleccion= readln()
        }

        //Eleccion 4: Repite
        else {
            println()
            println("${BG_RED}${BLACK}numero invalido, escribe un numero del 1 al 3${RESET}")
            print("opción: ")
            eleccion= readln()
        }
    }
}