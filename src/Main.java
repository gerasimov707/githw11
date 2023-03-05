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
        System.out.println("количество несовершенно летних: " + persons.stream().filter(person->person.getAge()<18).count());

        System.out.println("фамилии призывников: "+ persons.stream().filter(person->person.getAge()>=18 && person.getAge()<27).map(Person::getFamily).toList()); //persons.stream().filter(person->person.getAge()>=18 && person.getAge()<27).count());

        System.out.println("работоспособные люди с высшим образованием: " + persons.stream().filter(it -> (it.getAge() >= 18 && it.getAge() < 65 && it.getSex().equals(Sex.MAN))
                        || (it.getAge() >= 18 && it.getAge() < 60 && it.getSex().equals(Sex.WOMAN))
                        && it.getEducation().equals(Education.HIGHER))
                .sorted(comparator)
                .collect(Collectors.toList())
        );
    }
}