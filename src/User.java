import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String adress;
    private String phoneNumber;
    private BigDecimal availableBalance;
    private Car[] car = new Car[0];
    private Bank mBank;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = chekScanner(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = chekScanner(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email, User[] users) {
        this.email = chekEmail(email, users);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = chekPassword(password);
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = chekScanner(adress);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = chekPhoneNUmber(phoneNumber);
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance() {
        this.availableBalance = chekScannerkBigDecimal();
    }


    public void setCar(Car[] car) {
        this.car = car;
    }

    public Bank getmBank() {
        return mBank;
    }

    public void setmBank(Bank mBank) {
        this.mBank = mBank;
    }

    public Car[] getCar() {
        return car;
    }

    public void setCar(Car car) {
        Car[] newCars = Arrays.copyOf(this.car, this.car.length + 1);
        newCars[this.car.length] = car;
        this.car = newCars;
    }

    private String chekEmail(String email, User[] users) {
        while (true) {
            try {
                if (isValidEmail(email) && isEmailUnique(email, users)) {
                    return email;
                } else if (!isValidEmail(email)) {
                    throw new MyException("email должно заканчиваться на \"@gmail.com\"");
                } else {
                    throw new MyException("email уже используется.\n Пожалуйста, введите другой email : ");
                }
            } catch (MyException e) {
                System.out.println(e.getMessage());
                email = new Scanner(System.in).nextLine();
            }
        }
    }

    private boolean isValidEmail(String email) {
        return email.endsWith("@gmail.com") && email.length() > "@gmail.com".length();
    }

    private boolean isEmailUnique(String email, User[] users) {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email)) {
                return false;
            }
        }
        return true;
    }

    private String chekPassword(String password) {
        while (true) {
            if (password.length() > 3){
                return password;
            }
            else {
                System.out.println("Пароль должен содержать  от 4 до 12 символов❗️");
                password = new Scanner(System.in).nextLine();
            }
        }

    }

    private String chekPhoneNUmber(String phoneNumber) {
        while (true) {
            try {
                if (phoneNumber.startsWith("+996") && phoneNumber.length() == 13) {
                    return phoneNumber;
                } else if (phoneNumber.length() != 13) {
                    throw new MyException(" должен быть номер телефона  13 цифр ");
                } else
                    throw new MyException(" Номер телефона недействителен. Оно должно начинаться с \"+996\". ");
            } catch (MyException e) {
                System.out.println(e.getMessage());
                 phoneNumber = new Scanner(System.in).nextLine();
            }
        }
    }

    public static String chekScanner(String scannerWord) {
        boolean isTrue = true;
        while (isTrue) {
            if (!scannerWord.isBlank()) {
                return scannerWord;

            } else {
                System.out.println(" не должно быть пустым, напишите  : ");
                scannerWord = new Scanner(System.in).nextLine();
            }
        }
        return null;
    }

    private static BigDecimal chekScannerkBigDecimal() {
        while (true) {
            try {
                BigDecimal bigDecimal = new Scanner(System.in).nextBigDecimal();
                return bigDecimal;
            } catch (InputMismatchException e) {
                System.out.println("Неверный Ввод. Введите допустимое значение BigDecimal.");
            }
        }
    }


    public static User[] regstration(User user, User[] users) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        user.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилия: ");
        user.setLastName(scanner.nextLine());
        System.out.print("Введите Email: ");
        user.setEmail(scanner.nextLine(), users);
        System.out.print("Введите пароль: ");
        user.setPassword(scanner.nextLine());
        System.out.print("Введите адрес: ");
        user.setAdress(scanner.nextLine());
        System.out.print("Введите тел.номер:");
        user.setPhoneNumber(scanner.nextLine());
        System.out.print("Внесите оплату на счет: $");
        user.setAvailableBalance();
        users = Arrays.copyOf(users, users.length + 1);
        users[users.length - 1] = user;
        System.out.println("Успешно зарегистрирован 🙌\n");
        return users;
    }

    public static User login(User[] users) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        for (int i = 0; i < users.length; i++) {
            if (Objects.equals(users[i].getEmail(), email) && Objects.equals(users[i].getPassword(), password)) {
                System.out.println("Вход успешно выполнен 🙌\n ");
                return users[i];
            }
        }
        return null;
    }


    public boolean bookCar(Car car) {
        if (car.getPrice().floatValue() <= availableBalance.floatValue()) {
            setCar(car);
            car.setBook(false);
            pay(car);
            System.out.println("Вы успешно забронировали  машину, она к вашим услугам ");
            System.out.println(car.name() + car);
            return true;
        }
        return false;
    }

    public void pay(Car car) {
        System.out.println("Внесите оплату за машину : $" + car.getPrice());
        availableBalance = availableBalance.subtract(car.getPrice());
        System.out.println("Вы успешно вынесли оплату, оставшийся баланс: $" + availableBalance);
        Managment.setCompanyBalance(car.getPrice());
    }

    public boolean deposit(String choice) {
        switch (choice) {
            case "да" -> {
                System.out.print("Сумма депозита: $");
                BigDecimal deposit = chekScannerkBigDecimal();
                availableBalance = availableBalance.add(deposit);
                mBank.menegerBankAccount(deposit);
                System.out.println("Вы успешно внесли деньги на счет банка");
                System.out.println("Ваш баланс: $" + availableBalance);
            }
            case "нет" -> {
                return false;
            }
            default -> {
                System.out.println("Неправильный выбор❗️");
                return false;
            }
        }
        return true;
    }

    public void getcarsUser() {
        for (Car car1 : car) {
            System.out.println(car1.name());
        }
    }


}


