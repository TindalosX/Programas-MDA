//Tamaños

// Concrete component: Espresso
abstract class Beverage {
    String description = "Unknown Beverage";
    String size = "Medium";

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public abstract double cost();
}

abstract class CondimentDecorator extends Beverage{//public
	public abstract String getDescription();
}

class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    public double cost() {
        // Adjust cost based on size
        switch (getSize().toLowerCase()) {
            case "small":
                return 1.99;
            case "medium":
                return 1.99;
            case "large":
                return 1.99;
            default:
                throw new IllegalArgumentException("Invalid size: " + getSize());
        }
    }
}

class HouseBlend extends Beverage{//public
	public HouseBlend(){
		description = "House Blend Coffee";
	}
	public double cost(){
		return 0.89;
	}
}
class DarkRoast extends Beverage{ //public
	public DarkRoast(){
		description = "Dark Roast Coffee";
	}
	public double cost(){
		return 0.99;
	}
}
class Decaf extends Beverage{ //public
	public Decaf(){
		description = "Decaf Coffee";
	}
	public double cost(){
		return 1.05;
	}
}

class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double cost() {
        switch (beverage.getSize().toLowerCase()) {
            case "small":
                return 0.20 + beverage.cost();
            case "medium":
                return 0.20 + (beverage.cost() * 1.5);  // + 50%
            case "large":
                return 0.20 + (beverage.cost() * 2.0);  // + 100%
            default:
                throw new IllegalArgumentException("Invalid size: " + beverage.getSize());
        }
    }
}
class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    public double cost() {
        switch (beverage.getSize().toLowerCase()) {
            case "small":
                return 0.10 + beverage.cost();
            case "medium":
                return 0.10 + (beverage.cost() * 1.5);  // 50% increase for medium
            case "large":
                return 0.10 + (beverage.cost() * 2.0);  // 100% increase for large
            default:
                throw new IllegalArgumentException("Invalid size: " + beverage.getSize());
        }
    }
}
class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    public double cost() {
        switch (beverage.getSize().toLowerCase()) {
            case "small":
                return 0.15 + beverage.cost();
            case "medium":
                return 0.15 + (beverage.cost() * 1.5);  // 50% increase for medium
            case "large":
                return 0.15 + (beverage.cost() * 2.0);  // 100% increase for large
            default:
                throw new IllegalArgumentException("Invalid size: " + beverage.getSize());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Crear una bebida Espresso de tamaño medium(default)
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        espresso = new Mocha(espresso);
        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        // Espresso large
        espresso.setSize("Large");
        System.out.println( espresso.getDescription() + " $" + espresso.cost());

        // HouseBlend de tamaño small
        Beverage houseBlend = new HouseBlend();
        houseBlend.setSize("Small");
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());

        // Agregar Soy y Whip a la bebida HouseBlend
        houseBlend = new Soy(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());
    }
}




