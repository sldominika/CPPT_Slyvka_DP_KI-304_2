from .weapon import Weapon

class Pistol(Weapon):
    def __init__(self, name, caliber, magazine_capacity):
        super().__init__(name, caliber)
        self.magazine_capacity = magazine_capacity
        self.bullets = magazine_capacity

    def reload(self):
        self.bullets = self.magazine_capacity
        return "Пістолет перезаряджено."

    def fire(self):
        if self.bullets > 0:
            self.bullets -= 1
            return f"Постріл з {self.name}. Залишилось: {self.bullets}"
        return "Немає набоїв."
