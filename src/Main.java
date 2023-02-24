import java.util.*;
import java.util.stream.Collectors;



public class Main {
    public static void main(String[] args) {
        Comparator<Person> comparator = Comparator.comparing(Person::getFamily);
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //   System.out.println(persons.stream().filter(person -> person.getAge()<18 ).count()); до 18 лет
        //System.out.println(persons.stream()
        //         .filter(it -> it.getAge() >= 18 && it.getAge() < 27) 18-27
        //        .filter(it -> it.getSex().equals(Sex.MAN))
        //       .map(Person::getFamily)
        //       .collect(Collectors.toList())
        // );

        System.out.println(persons.stream()
                .filter(it -> (it.getAge() >= 18 && it.getAge() < 65 && it.getSex().equals(Sex.MAN))
                        || (it.getAge() >= 18 && it.getAge() < 60 && it.getSex().equals(Sex.WOMAN))
                        && it.getEducation().equals(Education.HIGHER))
                .sorted(comparator)
                .collect(Collectors.toList())
        );
    }
}