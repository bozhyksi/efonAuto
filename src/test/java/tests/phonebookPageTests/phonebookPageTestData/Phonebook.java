package tests.phonebookPageTests.phonebookPageTestData;

import flow.BaseTestMethods;

public class Phonebook extends BaseTestMethods {
    private Phonebook[] arr;
    private String number;
    private String name;
    private String shortDial;

    public Phonebook(int len) {
        this.arr = new Phonebook[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Phonebook();
        }
    }

    public Phonebook(){
        this.name = getRandomString(5);
        if(Integer.parseInt(getRandomNumber(5))%2 == 0) {
            this.number = getRandomPhone("00");
        }else {
            this.number = getRandomPhone("+");
        };
        this.shortDial = getRandomNumber(290, 299);
    }

    public String getName() {
        return name;
    }

    public String getShortDial() {
        return shortDial;
    }

    public String getNumber() {
        return number;
    }

    public Phonebook[] getArr() {
        return arr;
    }

    @Override
    public String toString() {
        return "Phonebook{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", shortDial='" + shortDial + '\'' +
                '}';
    }
}
