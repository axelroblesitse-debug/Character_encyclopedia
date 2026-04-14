Enciclopedia Anime
Sistema de Gestión de Personajes

Aplicación de consola en Java con CRUD completo, persistencia en archivos .txt y ordenamiento Bubble Sort.


🎯 Objetivo
Desarrollar una enciclopedia de personajes de anime en consola que aplique:

POO — clases con responsabilidades separadas
CRUD — crear, consultar, actualizar y eliminar personajes
Bubble Sort — ordenamiento por nivel de poder
Persistencia — datos guardados entre ejecuciones en archivos .txt
Estadísticas — promedio de poder y ranking de personajes más fuertes
Colores ANSI — experiencia visual mejorada en consola


🛠️ Tecnologías
CategoríaHerramientaLenguajeJava JDK 8+ParadigmaProgramación Orientada a ObjetosAlmacenamiento temporalArrays estáticosPersistenciaFileWriter / BufferedReaderEntrada de usuarioScannerPresentaciónCódigos ANSI, String.format()OrdenamientoBubble Sort

📁 Estructura del Proyecto
anime-encyclopedia/
├── src/
│   ├── Main.java             # Punto de entrada del programa
│   ├── Character.java        # Modelo de personaje
│   ├── CharacterManager.java # Lógica central (CRUD, sort, stats)
│   ├── Menu.java             # Interfaz de usuario en consola
│   └── Utils.java            # Funciones auxiliares
└── data/
    └── characters.txt        # Almacenamiento persistente
    
Descripción de archivos
Main.java — Inicia la aplicación, crea el gestor, carga los datos guardados y lanza el menú principal.
Character.java — Define el modelo de cada personaje con atributos nombre, clan, nivelDePoder y elemento. Incluye getters, setters y una representación en texto con barra visual de poder.
CharacterManager.java — Núcleo del sistema. Implementa todas las operaciones CRUD, el ordenamiento por poder, la generación de estadísticas y la lectura/escritura del archivo de datos.
Menu.java — Interfaz interactiva en consola. Presenta las opciones al usuario y controla el flujo del programa mediante un ciclo que se ejecuta hasta que el usuario decide salir.
Utils.java — Funciones reutilizables: validación de campos, validación de rangos de poder y generación de la barra visual.

▶️ Cómo Ejecutar
Requisitos previos:

Java JDK 8 o superior
Terminal con soporte para colores ANSI

Pasos:
bash# 1. Crear la carpeta de datos
mkdir data

# 2. Compilar todos los archivos fuente
javac src/*.java -d out/

# 3. Ejecutar el programa
java -cp out/ Main
Al iniciar, el sistema muestra una animación de bienvenida, carga los personajes guardados y presenta el menú interactivo. Todos los cambios se guardan automáticamente.

🧪 Casos Probados
FuncionalidadResultadoAgregar personaje válido✅ Se almacena correctamente en el archivoPoder fuera de rango / campo vacío✅ Muestra error y solicita correcciónBúsqueda case-insensitive✅ Encuentra el personaje sin importar mayúsculasBúsqueda de personaje inexistente✅ Manejo adecuado del caso vacíoActualizar personaje✅ Cambios reflejados en archivo y listadoEliminar con confirmación✅ Solicita confirmación antes de borrarOrdenamiento Bubble Sort✅ Orden correcto por nivel de poderEstadísticas (promedio, ranking)✅ Resultados coherentesPersistencia entre ejecuciones✅ Datos conservados al reiniciar

🐛 Bug Conocido
exportReport() sobrescribe el archivo de datos principal.
El método guarda el reporte con formato visual (colores ANSI, encabezados decorativos) directamente sobre characters.txt, corrompiendo los datos y haciendo que el sistema no pueda cargarlos en la próxima ejecución.
Solución recomendada: escribir el reporte en un archivo separado:
java// ❌ Actual (sobrescribe datos)
FileWriter fw = new FileWriter("data/characters.txt");

// ✅ Correcto (archivo independiente)
FileWriter fw = new FileWriter("data/report.txt");

📝 Conclusiones

La POO facilitó dividir el sistema en componentes claros y fáciles de mantener.
Los archivos .txt cumplieron eficazmente como almacenamiento persistente sin necesidad de base de datos.
Las validaciones implementadas mantienen la integridad de la información y previenen errores comunes.
La detección y documentación del bug en exportReport() demuestra la importancia de las pruebas y la mejora continua.

Posibles mejoras futuras:

Migrar de arrays estáticos a ArrayList para colecciones dinámicas
Agregar filtros y búsquedas avanzadas (por elemento, por clan)
Integrar con una base de datos real (SQLite, H2)