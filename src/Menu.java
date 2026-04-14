import java.util.Scanner;

public class Menu {

    private CharacterManager encyclopedia;
    private Scanner scanner;

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[38;5;51m";
    public static final String YELLOW = "\u001B[38;5;226m";
    public static final String GREEN = "\u001B[38;5;46m";
    public static final String RED = "\u001B[38;5;196m";
    public static final String GRAY = "\u001B[38;5;240m";
    public static final String BOLD = "\u001B[1m";

    public Menu(CharacterManager encyclopedia) {
        this.encyclopedia = encyclopedia;
        this.scanner = new Scanner(System.in);
    }
    private void typewriter(String text, int speed) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public void show() {
        typewriter(CYAN + BOLD + "  Iniciando Enciclopedia Anime..." + RESET, 40);
        typewriter(GRAY + "  Cargando datos del sistema..." + RESET, 30);
        System.out.println();
        int option;
        do {
            System.out.println(CYAN + BOLD + "\n╔═══════════════════════════════╗" + RESET);
            System.out.println(CYAN + BOLD + "║    ENCICLOPEDIA ANIME         ║" + RESET);
            System.out.println(CYAN + BOLD + "╚═══════════════════════════════╝" + RESET);
            System.out.println(YELLOW + "  1. Agregar personaje" + RESET);
            System.out.println(YELLOW + "  2. Listar todos" + RESET);
            System.out.println(YELLOW + "  3. Buscar personaje" + RESET);
            System.out.println(YELLOW + "  4. Actualizar personaje" + RESET);
            System.out.println(YELLOW + "  5. Eliminar personaje" + RESET);
            System.out.println(YELLOW + "  6. Ordenar por poder" + RESET);
            System.out.println(YELLOW + "  7. Estadisticas" + RESET);
            System.out.println(YELLOW + "  8. Exportar reporte" + RESET);
            System.out.println(RED +    "  0. Salir" + RESET);
            System.out.print(GREEN + BOLD + "  Elige una opcion: " + RESET);
            option = scanner.nextInt();
            processOption(option);
        } while (option != 0);
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                addCharacter();
                break;
            case 2:
                encyclopedia.listAll();
                break;
            case 3:
                searchCharacter();
                break;
            case 4:
                updateCharacter();
                break;
            case 5:
                deleteCharacter();
                break;
            case 6:
                encyclopedia.sortByPower();
                break;
            case 7:
                encyclopedia.showStatistics();
                break;
            case 8:
                encyclopedia.exportReport();
                break;
            case 0:
                System.out.println("Hasta luego!");
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }

    private void addCharacter() {
        scanner.nextLine();
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Clan: ");
        String clan = scanner.nextLine();
        System.out.print("Poder (0-100): ");
        int power;
        try {
            power = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("El poder debe ser un número.");
            scanner.nextLine();
            return;
        }
        scanner.nextLine();
        System.out.print("Elemento: ");
        String element = scanner.nextLine();
        if (Utils.isEmpty(name) || Utils.isEmpty(clan) || Utils.isEmpty(element)) {
            System.out.println("Los campos no pueden estar vacíos.");
            return;
        }
        if (!Utils.isValidPower(power)) {
            System.out.println("El poder debe estar entre 0 y 100.");
            return;
        }
        encyclopedia.addCharacter(new Character(name, clan, power, element));
    }

    private void searchCharacter() {
        scanner.nextLine();
        System.out.print("Nombre a buscar: ");
        String name = scanner.nextLine();
        encyclopedia.searchByName(name);
    }

    private void updateCharacter() {
        scanner.nextLine();
        System.out.print("Nombre del personaje a actualizar: ");
        String name = scanner.nextLine();
        System.out.print("Nuevo clan: ");
        String clan = scanner.nextLine();
        System.out.print("Nuevo poder (0-100): ");
        int power = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo elemento: ");
        String element = scanner.nextLine();
        encyclopedia.updateCharacter(name, clan, power, element);
    }

    private void deleteCharacter() {
        scanner.nextLine();
        System.out.print(YELLOW + "Nombre del personaje a eliminar: " + RESET);
        String name = scanner.nextLine();
        System.out.print(RED + "¿Está seguro? (s/n): " + RESET);
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("s")) {
            encyclopedia.deleteCharacter(name);
        } else {
            System.out.println(GRAY + "Eliminación cancelada." + RESET);
        }
    }

}

