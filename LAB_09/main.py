from gun_package.pistol import Pistol

def main():
    gun = Pistol("Glock 17", 9, 17)

    print(gun.info())
    print(gun.fire())
    print(gun.fire())
    print(gun.reload())
    print(gun.fire())

if __name__ == "__main__":
    main()
