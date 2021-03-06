package generics;


import sun.nio.cs.Surrogate;

import java.util.*;

class Customer {
    private static long counter = 1;
    private final long id = counter++;

    private Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }
    public static Generator<Customer> generator(){
        return new Generator<Customer>() {
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }
}

class Teller{
    private static long counter=1;
    private final long id=counter++;
    private Teller(){

    }
    public String toString(){
        return "Teller"+id;
    }
    public static Generator<Teller> generator=new Generator<Teller>() {
        @Override
        public Teller next() {
            return new Teller();
        }
    };
}
public class BankTeller {
    public static void serve(Teller t,Customer c){
        System.out.println(t+"servers"+c);
    }
    public static void main(String[] args){
        Random random=new Random(47);
        Queue<Customer> line=new LinkedList<Customer>();
        Generators.fill(line,Customer.generator(),15);
        List<Teller> tellers=new ArrayList<Teller>();
        Generators.fill(tellers,Teller.generator,4);
        for (Customer c:line){
            serve(tellers.get(random.nextInt(tellers.size())),c);
        }
    }
}
