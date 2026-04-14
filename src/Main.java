public class Main {
    public static void main(String[] args) {
        CharacterManager encyclopedia = new CharacterManager();
        encyclopedia.loadFromFile();
        Menu menu = new Menu(encyclopedia);
        menu.show();
    }
}
