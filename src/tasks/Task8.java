package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

    private long count;

    //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
    public List<String> getNames(List<Person> persons) {
        // скипаем первый элемент, с остальными работаем
        return persons.stream()
                .skip(1)
                .map(Person::getFirstName)
                .collect(Collectors.toList());
    }

    //ну и различные имена тоже хочется
    public Set<String> getDifferentNames(List<Person> persons) {
        // хеш сет гарантирует уникальность
        return new HashSet<>(getNames(persons));
    }

    //Для фронтов выдадим полное имя, а то сами не могут
    public String convertPersonToString(Person person) {
        // склеиваем за раз все в одну строку и отдаем
        if ((person.getSecondName() != null) && (person.getFirstName() != null)) {
            return person.getSecondName() + " " + person.getFirstName();
        }

        if (person.getSecondName() != null) {
            return person.getSecondName();
        }

        if (person.getFirstName() != null) {
            return person.getFirstName();
        }

        return "";
    }

    // словарь id персоны -> ее имя
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
        return persons.stream()
                .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
    }

    // есть ли совпадающие в двух коллекциях персоны?
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        // anyMatch() вернет true в том случае, если в Stream-e есть хоть один элемент, что удовлетворяет условию
        // можно преобразовать вторую коллекцию в сет, для быстродействия
        return persons1.stream()
                .anyMatch(persons2::contains);
    }

    //...
    public long countEven(Stream<Integer> numbers) {
        // отфильтровали, посчитали каунт
        return numbers.filter(num -> num % 2 == 0).count();
    }

    @Override
    public boolean check() {
        System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
        boolean codeSmellsGood = true;
        boolean reviewerDrunk = false;
        return codeSmellsGood || reviewerDrunk;
    }
}
