public enum Driver {

    ISLAM("+996772424231"),
    BEKTURGAN("+996223452345"),
    AYDAR("+996990550171"),
    DAVRON("+996990223344"),
    ULUKMAN("+996555123143"),
    ISABEK("+996222564556"),
    ELDAR("+996505453467");
    private String phoneNumber;

    Driver( String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return
                "\nтел.номер: " + phoneNumber+ "\n"
                ;
    }
}
