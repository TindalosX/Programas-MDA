#importar el módulo abc (Abstract Base Classes)
from abc import ABC, abstractmethod

# Interfaces FlyBehavior y QuackBehavior
#Para los métodos abstractos
class FlyBehavior(ABC):
    @abstractmethod
    def fly(self):
        pass

class QuackBehavior(ABC):
    @abstractmethod
    def quack(self):
        pass

# Implementaciones concretas de FlyBehavior
class FlyWithWings(FlyBehavior):
    def fly(self):
        print("Vuelo con alas")

class FlyNoWay(FlyBehavior):
    def fly(self):
        print("No vuelo")

class FlyWithBalloon(FlyBehavior):
    def fly(self):
        print("Vuelo con globo")

# Implementaciones concretas de QuackBehavior
class Quack(QuackBehavior):
    def quack(self):
        print("quack")

class Squeak(QuackBehavior):
    def quack(self):
        print("squeze")

class MuteQuack(QuackBehavior):
    def quack(self):
        print("no hago ruido")

# Clase abstracta Duck
class Duck(ABC):
    def __init__(self, fly_behavior, quack_behavior):
        self.fly_behavior = fly_behavior
        self.quack_behavior = quack_behavior

    @abstractmethod
    def display(self):
        pass

    def perform_fly(self):
        self.fly_behavior.fly()

    def perform_quack(self):
        self.quack_behavior.quack()

    def set_fly_behavior(self, fly_behavior):
        self.fly_behavior = fly_behavior

    def set_quack_behavior(self, quack_behavior):
        self.quack_behavior = quack_behavior

# Clases concretas de pato
class MallardDuck(Duck):
    def display(self):
        print("Soy un pato malard")

class RedheadDuck(Duck):
    def display(self):
        print("Soy un pato cabeza roja")

class RubberDuck(Duck):
    def display(self):
        print("Soy un pato de goma")

class DecoyDuck(Duck):
    def display(self):
        print("Soy un pato de reclamo")

# Programa principal (main)
if __name__ == "__main__":
    #Instancias de diferentes tipos de patos con diferentes comportamientos
    mallard_duck = MallardDuck(FlyWithWings(), Quack())
    redhead_duck = RedheadDuck(FlyNoWay(), Quack())
    rubber_duck = RubberDuck(FlyWithBalloon(), Squeak())
    decoy_duck = DecoyDuck(FlyNoWay(), MuteQuack())

    # Mostrar información y realizar acciones
    mallard_duck.display()
    mallard_duck.perform_fly()
    mallard_duck.perform_quack()
    print("\n")

    redhead_duck.display()
    redhead_duck.perform_fly()
    redhead_duck.perform_quack()
    print("\n")

    rubber_duck.display()
    rubber_duck.perform_fly()
    rubber_duck.perform_quack()
    print("\n")

    decoy_duck.display()
    decoy_duck.perform_fly()
    decoy_duck.perform_quack()
    

