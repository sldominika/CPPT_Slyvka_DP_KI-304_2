# matrix_triangle.py
# Програма генерує зубчастий список, який містить лише заштриховану
# праву верхню трикутну частину квадратної матриці.
# Автор: Slyvka
# Версія: 1.1

def generate_jagged_triangle(n, symbol):
    """
    Створює зубчастий список, який містить лише правий верхній трикутник
    квадратної матриці (включно з головною діагоналлю).
    """
    jagged = []
    for i in range(n):
        # Кожен рядок містить n - i елементів, але зсунутих вправо
        row = [" "] * i + [symbol for _ in range(n - i)]
        jagged.append(row)
    return jagged


def print_jagged(jagged):
    """Виводить зубчастий список у зручному форматі."""
    for row in jagged:
        print(" ".join(row))


def main():
    try:
        n = int(input("Введіть розмір квадратної матриці n: "))
        if n <= 0:
            raise ValueError("Розмір має бути додатнім числом!")

        symbol = input("Введіть символ-заповнювач: ")
        if len(symbol) != 1:
            raise ValueError("Необхідно ввести лише один символ!")

        jagged = generate_jagged_triangle(n, symbol)
        print("\nСформований зубчастий список (права верхня частина):")
        print_jagged(jagged)

    except ValueError as e:
        print(f"Помилка: {e}")


if __name__ == "__main__":
    main()