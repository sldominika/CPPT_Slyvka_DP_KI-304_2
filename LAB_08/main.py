# main.py
# Головна програма, яка використовує модуль expression_module

from expression_module import (
    compute_expression,
    write_text, read_text,
    write_binary, read_binary
)


def main():
    # Декілька тестових значень x
    x_values = [0.5, 1.0, 1.5, 2.0]

    # Обчислення виразу
    results = [compute_expression(x) for x in x_values]

    print("Обчислені значення:")
    for x, y in zip(x_values, results):
        print(f"x={x} → y={y}")

    # ---- Запис у текстовий файл ----
    write_text("results.txt", results)

    # ---- Запис у двійковий файл ----
    write_binary("results.bin", results)

    # ---- Перевірка читання ----
    txt_loaded = read_text("results.txt")
    bin_loaded = read_binary("results.bin")

    print("\nЗчитано з текстового файлу:", txt_loaded)
    print("Зчитано з двійкового файлу:", bin_loaded)


if __name__ == "__main__":
    main()
