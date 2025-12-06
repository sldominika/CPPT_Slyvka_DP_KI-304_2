# expression_module.py
# Модуль для обчислення виразу y = sin(3x - 5) / ctg(2x)
# та роботи з текстовими і двійковими файлами.
# Автор: (ваше ім’я)
# Версія: 1.0

import math
import struct


def compute_expression(x):
    """
    Обчислює значення y = sin(3x - 5) / ctg(2x)
    Використано тотожність: 1/ctg(a) = tan(a)
    """
    return math.sin(3 * x - 5) * math.tan(2 * x)


# --------------------------- ТЕКСТОВІ ФАЙЛИ ---------------------------

def write_text(filename, data):
    """
    Записує список чисел у текстовий файл.
    Кожне число записується в новому рядку.
    """
    with open(filename, "w", encoding="utf-8") as f:
        for item in data:
            f.write(f"{item}\n")


def read_text(filename):
    """
    Зчитує текстовий файл, повертає список чисел (float).
    """
    results = []
    with open(filename, "r", encoding="utf-8") as f:
        for line in f:
            results.append(float(line.strip()))
    return results


# --------------------------- ДВІЙКОВІ ФАЙЛИ ---------------------------

def write_binary(filename, data):
    """
    Записує список чисел у двійковий файл.
    Використовується формат 'd' — число подвійної точності (double).
    """
    with open(filename, "wb") as f:
        for number in data:
            f.write(struct.pack("d", number))


def read_binary(filename):
    """
    Зчитує двійковий файл, повертає список чисел (float).
    """
    results = []
    with open(filename, "rb") as f:
        while chunk := f.read(8):     # 8 байт = double
            number = struct.unpack("d", chunk)[0]
            results.append(number)
    return results
