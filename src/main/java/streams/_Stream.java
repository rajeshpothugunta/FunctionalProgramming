package streams;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static streams._Stream.Gender.*;

public class _Stream {

    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Aisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE),
                new Person("Bob", PREFER_NOT_TO_SAY)
        );

        people.stream()
        .map(person -> person.name)
        .collect(Collectors.toSet())
        .forEach(System.out::println);
        
        people.stream()
                .map(person -> person.name) //1
                .mapToInt(String::length) //2
                .forEach(System.out::println); //3
        
        //***********In detail Functional representation**************
        System.out.println("same stuff printing again");
        //1
        Function<Person, String> personStringFunction = person -> person.name;
        //2
        ToIntFunction<String> length = String::length;
        //3
        IntConsumer println = x -> System.out.println(x);
        
        people.stream()
        .map(personStringFunction) //1
        .mapToInt(length) //2
        .forEach(println); //3


    }

    static class Person {
        private final String name;
        private final Gender gender;

        Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE, PREFER_NOT_TO_SAY
    }
}