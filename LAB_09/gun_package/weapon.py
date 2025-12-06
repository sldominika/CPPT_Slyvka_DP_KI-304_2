class Weapon:
    def __init__(self, name, caliber):
        self.name = name
        self.caliber = caliber

    def info(self):
        return f"Зброя: {self.name}, калібр: {self.caliber} мм"

    def fire(self):
        return "Постріл!"
