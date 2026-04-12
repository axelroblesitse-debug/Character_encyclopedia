public class Character {

    private String name;
    private String clan;
    private int power;
    private String element;

    public Character(String name, String clan, int power, String element) {
        this.name = name;
        this.clan = clan;
        this.power = power;
        this.element = element;
    }
        public String getName() {
            return name;
        }
        public String getClan() {
            return clan;
        }
        public int getPower() {
            return power;
        }
        public String getElement() {
            return element;
        }

        public void setName(String name) {
            this.name = name;
        }
        public void setClan(String clan) {
            this.clan = clan;
        }
        public void setPower(int power) {
            this.power = power;
        }
        public void setElement(String element) {
            this.element = element;
        }

        @Override
    public String toString() {
        return String.format("%-15s | %-15s | %-10s | %s",
                name, clan, element, Utils.renderPowerBar(power));
    }

}