public class Multicurrency {

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}

interface Expression {
    Money reduce(String to);
}

class Money implements Expression {
    protected int amount;
    protected String currency;

    Money (int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    protected String currency(){
        return currency;
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    Expression plus(Money addend) {
        return new Sum(this, addend);
    }

    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }
    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Money reduce(String to){
        return this;
    }



    public String toString() {
        return amount + " " + currency;
    }

}

class Sum implements Expression{
    Money augend;
    Money addend;

    Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(String to){
        int amount = augend.amount + addend.amount;
        return new Money(amount, to);
    }

}

class Bank {
    Money reduce(Expression source, String to) {
        return source.reduce(to);
    }

}


