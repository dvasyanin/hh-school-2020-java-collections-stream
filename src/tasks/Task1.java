package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

    // !!! Редактируйте этот метод !!!
    private List<Person> findOrderedPersons(List<Integer> personIds) {
        Set<Person> persons = PersonService.findPersons(personIds);

        // перекладываем каждую персону с ключом как ее айдишник в мапу O(n)
        Map<Integer, Person> sortedPersons = persons.stream()
                .collect(Collectors.toMap(
                        Person::getId,
                        Function.identity()
                ));

        // на каждый айдишник листа достаем из мапы персону за O(1), общее время O(n)
        return personIds.stream()
                .map(sortedPersons::get)
                .collect(Collectors.toList());
    }

    @Override
    public boolean check() {
        List<Integer> ids = List.of(1, 2, 3);

        return findOrderedPersons(ids).stream()
                .map(Person::getId)
                .collect(Collectors.toList())
                .equals(ids);
    }

}
