import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class CharacterManager {

    private Character[] characters;
    private int count;
    private static final int CAPACITY = 100;

    public CharacterManager() {
        characters = new Character[CAPACITY];
        count = 0;
    }

    public void addCharacter(Character p) {
        if (count == CAPACITY) {
            System.out.println(Menu.RED + "La enciclopedia está llena." + Menu.RESET);
            return;
        }
        if (p.getPower() < 0 || p.getPower() > 100) {
            System.out.println(Menu.RED + "El poder debe estar entre 0 y 100." + Menu.RESET);
            return;
        }
        characters[count] = p;
        count++;
        saveToFile();
        System.out.println(Menu.GREEN + "Personaje agregado con éxito." + Menu.RESET);
    }

    public void listAll() {
        if (count == 0) {
            System.out.println(Menu.RED + "No hay personajes registrados." + Menu.RESET);
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(Menu.CYAN + characters[i] + Menu.RESET);
        }
    }

    public void searchByName(String name) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (characters[i].getName().equalsIgnoreCase(name)) {
                System.out.println(Menu.CYAN + characters[i] + Menu.RESET);
                found = true;
            }
        }
        if (!found) {
            System.out.println(Menu.RED + "Personaje no encontrado." + Menu.RESET);
        }
    }

    public void updateCharacter(String name, String clan, int power, String element) {
        for (int i = 0; i < count; i++) {
            if (characters[i].getName().equalsIgnoreCase(name)) {
                characters[i].setClan(clan);
                characters[i].setPower(power);
                characters[i].setElement(element);
                System.out.println(Menu.GREEN + "Personaje actualizado con éxito." + Menu.RESET);
                return;
            }
        }
        System.out.println(Menu.RED + "Personaje no encontrado." + Menu.RESET);
    }

    public void deleteCharacter(String name) {
        for (int i = 0; i < count; i++) {
            if (characters[i].getName().equalsIgnoreCase(name)) {
                for (int j = i; j < count - 1; j++) {
                    characters[j] = characters[j + 1];
                }
                characters[count - 1] = null;
                count--;
                System.out.println(Menu.GREEN + "Personaje eliminado con éxito." + Menu.RESET);
                return;
            }
        }
        System.out.println(Menu.RED + "Personaje no encontrado." + Menu.RESET);
    }

    public void sortByPower() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (characters[j].getPower() > characters[j + 1].getPower()) {
                    Character temp = characters[j];
                    characters[j] = characters[j + 1];
                    characters[j + 1] = temp;
                }
            }
        }
        System.out.println(Menu.CYAN + "\nPersonajes ordenados por poder:" + Menu.RESET);
        listAll();
    }

    public void showStatistics() {
        if (count == 0) {
            System.out.println(Menu.RED + "No hay personajes registrados." + Menu.RESET);
            return;
        }

        int total = 0;
        Character strongest = characters[0];
        for (int i = 0; i < count; i++) {
            total += characters[i].getPower();
            if (characters[i].getPower() > strongest.getPower()) {
                strongest = characters[i];
            }
        }
        int average = total / count;

        System.out.println(Menu.GRAY + "╔═══════════════════════════════════════════╗" + Menu.RESET);
        System.out.println(Menu.CYAN + "║         DASHBOARD DE ESTADÍSTICAS         ║" + Menu.RESET);
        System.out.println(Menu.GRAY + "╠═══════════════════════════════════════════╣" + Menu.RESET);
        System.out.println(Menu.YELLOW + "║  Promedio de poder : " + Utils.renderPowerBar(average) + Menu.RESET);
        System.out.println(Menu.GREEN  + "║  Más poderoso      : " + strongest.getName() + Menu.RESET);
        System.out.println(Menu.GRAY + "╠═══════════════════════════════════════════╣" + Menu.RESET);
        System.out.println(Menu.CYAN + "║            TOP 3 MÁS FUERTES              ║" + Menu.RESET);
        System.out.println(Menu.GRAY + "╠═══════════════════════════════════════════╣" + Menu.RESET);

        sortByPower();
        for (int i = count - 1; i >= count - 3 && i >= 0; i--) {
            System.out.println(Menu.YELLOW + "║  " + characters[i] + Menu.RESET);
        }

        System.out.println(Menu.GRAY + "╚═══════════════════════════════════════════╝" + Menu.RESET);
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("data/characters.txt");
            for (int i = 0; i < count; i++) {
                writer.write(characters[i].getName() + "," +
                             characters[i].getClan() + "," +
                             characters[i].getPower() + "," +
                             characters[i].getElement() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(Menu.RED + "Error al guardar datos." + Menu.RESET);
        }
    }
    public void loadFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/characters.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String clan = parts[1];
                    int power = Integer.parseInt(parts[2]);
                    String element = parts[3];
                    characters[count] = new Character(name, clan, power, element);
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            // archivo no existe todavía, no pasa nada
        }
    }

    public void exportReport() {
        try {
            FileWriter writer = new FileWriter("data/characters.txt");
            for (int i = 0; i < count; i++) {
                writer.write(characters[i].toString() + "\n");
            }
            writer.close();
            System.out.println(Menu.GREEN + "Reporte exportado a data/characters.txt" + Menu.RESET);
        } catch (IOException e) {
            System.out.println(Menu.RED + "Error al exportar el reporte." + Menu.RESET);
        }
    }

}