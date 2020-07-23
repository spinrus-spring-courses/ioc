@snap[west]
### Михаил Ильиных
#### Команда Hybris

Опыт Java и Spring разработки 4-5 лет, из них активной 1-2 года
Oracle Certified Professional, Java SE 8 Programmer II 

@snapend

---

@snap[center]
# Lord of The Spring

Spring Framework: IoC, Dependency Injection
@snapend

---

@snap[center]
#### Здесь должно быть несколько слайдов про инъекцию зависимостей
@snapend

---

@snap[center]
#### Здесь должно быть несколько слайдов про Spring Framework
@snapend

---

@snap[center]
### А теперь практика
@snapend

Note:
### Не тараторь!
- Обзор проекта 

---

@snap[north-east]
#### Person.java
@snapend

```java
public abstract class Person implements Named {

    protected String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
```

+++

@snap[north-east]
#### Hobbit.java
@snapend

```java
public class Hobbit extends Person {

}
```

+++

@snap[north-east]
#### Human.java
@snapend

```java
public class Human extends Person {

    public Human(String name) {
        this.name = name;
    }
}
```

+++

@snap[north-east]
#### Elf.java
@snapend

```java
public class Elf extends Person {

    private Elf(String name) {
        this.name = name;
    }

    public static Elf withName(String name) {
        return new Elf(name);
    }
}
```
@snap[south span-60]
@[3-5](Приватный конструктор)
@[7-9](Фабричный метод)
@snapend

+++

@snap[north-east]
#### Dwarf.java
@snapend

```java
public class Dwarf extends Person {

    public static class DwarfBuilder {

        private String name;

        public DwarfBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Dwarf build() {
            Dwarf dwarf = new Dwarf();
            dwarf.name = name;

            return dwarf;
        }
    }
}
```
@snap[south span-60]
@[3-18](Строитель, он же Builder)
@snapend

+++

@snap[north-east]
#### Mage.java
@snapend

```java
public class Mage extends Person {

    Mage(String name) {
        this.name = name;
    }
}
```
@snap[south span-60]
@[3-5](Конструктор c видимостью по-умолчанию)
@snapend

+++

@snap[north-east]
#### MageFactory.java
@snapend

```java
public class MageFactory {

    public Mage withName(String name) {
        return new Mage(name);
    }
}

```
@snap[south span-60]
@[3-5](Фабрика магов)
@snapend

---

@snap[center]
### А где же Spring?
@snapend

---

@snap[north-east]
#### SpringTrainingApplication.java
@snapend

```java
@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTrainingApplication.class, args);
    }
}
```
@snap[south span-60]
@[1](Магия от Spring Boot)
@[2](Рискнем добавить немного XML)
@[5-7](Запуск приложения)
@snapend

---

@snap[center]
## Задание №1. Объявление бинов, XML.
@snapend

@snap[south]
1-BeanDefinitionXml/Task
@snapend

+++?color=linear-gradient(90deg, white 65%, gold 35%)

@snap[north-west]
### Задание №1.1
@snapend

@snap[west span-65]
Объявить бины в 
*spring-configuration.xml*
@snapend

@snap[east span-35]
@ul[list-spaced-bullets]
- frodo
- sam
- aragorn
- legolas
- gandalf
@ulend
@snapend

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="aragorn" class="io.itlabs.springtraining.domain.person.Human">
    <constructor-arg name="name" value="Aragorn"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="legolas" class="io.itlabs.springtraining.domain.person.Elf" 
      factory-method="withName">
    <constructor-arg name="name" value="Legolas"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="mageFactory" 
      class="io.itlabs.springtraining.domain.person.MageFactory"/>

<bean id="gandalf" factory-method="withName" 
      factory-bean="mageFactory">
    <constructor-arg name="name" value="Gandalf"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="frodo" class="io.itlabs.springtraining.domain.person.Hobbit">
    <property name="name" value="Frodo"/>
</bean>

<bean id="sam" class="io.itlabs.springtraining.domain.person.Hobbit">
    <property name="name" value="Sam"/>
</bean>
```

+++

@snap[north-west]
### Задание №1.2
@snapend

@snap[west]
Вывести в консоль атрубут *name* всех объявленных бинов
@snapend

+++

@snap[north-east]
#### SpringTrainingApplication.java
@snapend

```java
@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    private static final Logger LOG = 
        LoggerFactory.getLogger(SpringTrainingApplication.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication
            .run(SpringTrainingApplication.class, args);
        
        Stream
            .of(applicationContext.getBeanNamesForType(Person.class))
            .forEach(LOG::info);
    }

}
```
@snap[south span-60]
@[9-10](Получим контекст)
@[12-14](Получим бины типа *Person* из контекста и выведем атрибут в консоль)
@snapend
---

@snap[center]
## Задание №2. Объявление бинов, аннотации.
@snapend

@snap[south]
2-BeanDefinitionAnnotation/Task
@snapend

+++

@snap[north-west]
### Задание №2.1
@snapend

@snap[west]
@ul[list-spaced-bullets]
- Объявить бин сервиса приветствия *greetingService*, типа *LoggingGreetingService*.
- Задать значение для атрибута *greeting*.
@ulend
@snapend

+++

@snap[north-east]
#### LoggingGreetingService.java
@snapend

```java
public class LoggingGreetingService implements GreetingService {

    private static final Logger LOG = 
        LoggerFactory.getLogger(LoggingGreetingService.class);

    private String greeting;

    @Override
    public void greet(Person person) {
        LOG.info("{}, {}", greeting, person.getName());
    }

    // Setters and getters

}
```

+++

@snap[north-east]
#### LoggingGreetingService.java
@snapend

```java
@Service("greetingService")
public class LoggingGreetingService implements GreetingService {
    
    @Value("Hello")
    private String greeting;
}
```
@snap[south span-60]
@[1](Объявление бина)
@[4](Инъекция зависимости, значение)
@snapend
+++

@snap[north-west]
### Задание №2.2
@snapend

@snap[west]
@ul[list-spaced-bullets]
- Получить из контекста все бины типа *Person*. 
- Вызвать метод *greet* бина *greetingService* для каждого из них.
@ulends
@snapend

+++

@snap[north-east]
#### SpringTrainingApplication.java
@snapend

```java
@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {
    
    public static void main(String[] args) {
        
        ApplicationContext context = SpringApplication
            .run(SpringTrainingApplication.class, args);
    
        GreetingService greetingService = applicationContext
            .getBean("greetingService", GreetingService.class);
        
        Map<String, Person> persons = context.getBeansOfType(Person.class);
        for (var person: persons.values()) {
            greetingService.greet(person);
        }
    }
}
```
@snap[south span-60]
@[7-8](Получение контекста)
@[10-11](Получение бина по имени)
@[13-16](Получение бинов по типу)
@snapend

---

@snap[center]
## Задание №3. Объявление бинов, Java.
@snapend

@snap[south]
3-BeanDefinitionJava/Task
@snapend

+++?color=linear-gradient(90deg, white 65%, gold 35%)

@snap[north-west]
### Задание №3.1
@snapend

@snap[west span-65]
Объявить бины
@snapend

@snap[east span-35]
@ul[list-spaced-bullets]
- boromir
- gimli
- meriadoc
- pippin
- saruman
@ulend
@snapend

+++

@snap[north-east]
#### DomainBeanDefinitions.java
@snapend

```java
@Configuration
public class DomainBeanDefinitions {

    @Bean
    public Human boromir() {
        return new Human("Boromir");
    }

    @Bean
    public Dwarf gimli() {
        return new Dwarf
                .DwarfBuilder()
                .withName("Gimli")
                .build();
    }

    @Bean
    public Hobbit meriadoc() {
        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");

        return hobbit;
    }

    @Bean
    public Hobbit pippin() {
        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");

        return hobbit;
    }

    @Bean
    public Mage saruman(MageFactory mageFactory) {
        return mageFactory.withName("Saruman");
    }
}
```
@snap[south span-60]
@[1](Конфигурация - тоже бин)
@[4-7]
@[9-15]
@[17-23]
@[25-31]
@[33-36]
@snapend

---

@snap[center]
## Задание №4. Инъекция зависимости.
@snapend

@snap[south]
4-DependencyInjection/Task
@snapend

+++

@snap[north-west]
### Задание №4.1
@snapend

@snap[west]
Привязать бины хоббитов как атрибуты бина *shire* инъекцией атрибута (*field injection*)
@snapend

+++

@snap[north-east]
#### Shire.java
@snapend

```java
@Component
public class Shire implements PersonGroup<Hobbit> {

    @Autowired
    private Hobbit frodo;

    @Autowired
    private Hobbit sam;

    @Autowired
    private Hobbit meriadoc;

    @Autowired
    private Hobbit pippin;

    @Override
    public List<Hobbit> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }
}
```
@snap[south span-60]
@[1](*Shire* - это бин)
@[4-14](Никогда так не делайте)
@snapend

+++

@snap[north-west]
### Задание №4.2
@snapend

@snap[west]
Привязать бины хоббитов как атрибуты бина *shire* инъекцией через сеттер (*setter-based injection*)
@snapend

+++

@snap[north-east]
#### Shire.java
@snapend

```java
@Component
public class Shire implements PersonGroup<Hobbit> {

    private Hobbit frodo;

    private Hobbit sam;

    private Hobbit meriadoc;

    private Hobbit pippin;

    @Override
    public List<Hobbit> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }

    @Autowired
    public void setFrodo(Hobbit frodo) {
        this.frodo = frodo;
    }

    @Autowired
    public void setSam(Hobbit sam) {
        this.sam = sam;
    }

    @Autowired
    public void setMeriadoc(Hobbit meriadoc) {
        this.meriadoc = meriadoc;
    }

    @Autowired
    public void setPippin(Hobbit pippin) {
        this.pippin = pippin;
    }
}
```
@[1]
@[17-35]

+++

@snap[north-west]
### Задание №4.3
@snapend

@snap[west]
Привязать бины хоббитов как атрибуты бина *shire* через конструктор (*constructor-based injection*)
@snapend

+++

@snap[north-east]
#### Shire.java
@snapend

```java
@Component
public class Shire implements PersonGroup<Hobbit> {

    private final Hobbit frodo;

    private final Hobbit sam;

    private final Hobbit meriadoc;

    private final Hobbit pippin;

    public Shire(Hobbit frodo, Hobbit sam, Hobbit meriadoc, 
        Hobbit pippin) {

        this.frodo = frodo;
        this.sam = sam;
        this.meriadoc = meriadoc;
        this.pippin = pippin;
    }

    @Override
    public List<Hobbit> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }
}
```
@[1]
@[4-19]

+++

@snap[north-west]
### Задание №4.4
@snapend

@snap[west]
Привязать список бинов хоббитов как атрибут бина *shire*
@snapend

+++

@snap[north-east]
#### Shire.java
@snapend

```java
@Component
public class Shire implements PersonGroup<Hobbit> {

    private final List<Hobbit> hobbits;

    public Shire(List<Hobbit> hobbits) {
        this.hobbits = hobbits;
    }

    @Override
    public List<Hobbit> persons() {
        return hobbits;
    }
}
```

+++

@snap[north-west]
### Задание №4.5
@snapend

@snap[west]
Вывести имена всех жителей Шира(*Shire*) c помощью метода *persons* бина *shire*.
@snapend

+++

@snap[north-east]
#### SpringTrainingApplication.java
@snapend

```java
@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {
    
    public static void main(String[] args) {
        
        ApplicationContext context = SpringApplication
            .run(SpringTrainingApplication.class, args);
    
        GreetingService greetingService = applicationContext
            .getBean("greetingService", GreetingService.class);
        
        Shire shire = context.getBean("shire", Shire.class);
        for (var person: shire.values()) {
            greetingService.greet(person);
        }
    }
}
```
@[13-16]

---

@snap[center]
## Задание №5. Scope.
@snapend

@snap[south]
5-BeanScope/Task
@snapend

+++

@snap[north-east]
#### Weapon.java
@snapend

```java
public class Weapon {

    public enum Type {AXE, BOW, DAGGER, SWORD, STAFF}

    private final Type type;

    private boolean broken = false;

    public Weapon(Type type) {
        this.type = type;
    }

    // Setters and getters
}
```

+++

@snap[north-east]
#### Person.java
@snapend

```java
public abstract class Person implements Named {

    protected String name;

    protected List<Weapon> weapons;
    
    // Setters and getters

}
```

+++

@snap[north-west]
### Задание №5.1
Вооружить всех хоббитов кинжалами. Для этого нужно объявить бин *dagger* и модифицировать объявления хоббитов.
@snapend

+++

@snap[north-east]
#### DomainBeanDefinitions.java
@snapend

```java
@Configuration
public class DomainBeanDefinitions {

    @Bean
    public Weapon dagger() {
        return new Weapon(DAGGER);
    }

    @Bean
    public Hobbit meriadoc() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    public Hobbit pippin() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

}
```
@snap[south span-60]
@[4-7](Добавим бин кинжала, *dagger*)
@[11-12, 23-24] (Инъекция через вызов функции)
@snapend

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="frodo" class="io.itlabs.springtraining.domain.person.Hobbit">
    <property name="name" value="Frodo"/>
    <property name="weapons">
        <list>
            <ref bean="dagger"/>
        </list>
    </property>
</bean>

<bean id="sam" class="io.itlabs.springtraining.domain.person.Hobbit">
    <property name="name" value="Sam"/>
    <property name="weapons">
        <list>
            <ref bean="dagger"/>
        </list>
    </property>
</bean>
```
@snap[south span-60]
@[3-7б 12-16](Инъекция списка, ссылка на бин)
@snapend

+++

@snap[north-west]
### Задание №5.2
В методе main изменить атрибут *broken* бина *dagger* на *true*. Повторить вывод в консоль.
@snapend

+++

@snap[north-east]
#### LoggingGreetingService.java
@snapend

```java
@Service("greetingService")
public class LoggingGreetingService implements GreetingService {

    // ...

    @Override
    public void greet(Person person) {
        LOG.info("{}, {}", greeting, person.getName());
        LOG.info("{}, weapons {}", person.getName(), person.getWeapons());
    }
    
    // ...

}
```

+++

@snap[north-east]
#### SpringTrainingApplication.java
@snapend

```java
@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    public static void main(String[] args) {
        final var applicationContext = SpringApplication
            .run(SpringTrainingApplication.class, args);

        final var greetingService = applicationContext
            .getBean("greetingService", GreetingService.class);

        final var shire = applicationContext
            .getBean("shire", Shire.class);

        for (var person: shire.persons()) {
            greetingService.greet(person);
        }

        final var dagger = applicationContext
            .getBean("dagger", Weapon.class);
        
        dagger.setBroken(true);

        for (var person: shire.persons()) {
            greetingService.greet(person);
        }
    }
}
```
@[20-26]

+++

@snap[north-west]
### Задание №5.3
Изменить *scope* бина *dagger* на *prototype*.
@snapend

+++

@snap[north-east]
#### DomainBeanDefinitions.java
@snapend

```java
@Configuration
public class DomainBeanDefinitions {

    @Bean
    @Scope("prototype")
    public Weapon dagger() {
        return new Weapon(DAGGER);
    }
    
    // Other beans

}
```
@snap[south span-60]
@[5](Теперь каждый получит свой экземпляр)
@snapend

---

@snap[center]
## Задание №6. Квалификаторы.
@snapend

@snap[south]
6-Qualifiers/Task
@snapend

+++

@snap[north-east]
#### FellowshipOfTheRing.java
@snapend

```java
public class FellowshipOfTheRing implements PersonGroup<Person> {

    private final List<Person> persons;

    public FellowshipOfTheRing(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public List<Person> persons() {
        return persons;
    }
}
```

+++

@snap[north-west]
### Задание №6.1
Заполнить список участников братства кольца, *fellowshipOfTheRing*.
@snapend

+++

@snap[north-east]
#### DomainBeanDefinitions.java
@snapend

```java
@Configuration
public class DomainBeanDefinitions {

    @Bean
    @Scope("prototype")
    public Weapon dagger() {
        return new Weapon(DAGGER);
    }

    @Bean
    @Qualifier("kind")
    public Human boromir() {
        return new Human("Boromir");
    }

    @Bean
    @Qualifier("kind")
    public Dwarf gimli() {
        return new Dwarf
                .DwarfBuilder()
                .withName("Gimli")
                .build();
    }

    @Bean
    @Qualifier("kind")
    public Hobbit meriadoc() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    @Qualifier("kind")
    public Hobbit pippin() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    public Mage saruman(MageFactory mageFactory) {
        return mageFactory.withName("Saruman");
    }

}
```
@[10-14]
@[16-23]
@[25-36]
@[38-49]

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="aragorn" class="io.itlabs.springtraining.domain.person.Human">
    <qualifier value="kind"/>
    <constructor-arg name="name" value="Aragorn"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="legolas" class="io.itlabs.springtraining.domain.person.Elf" factory-method="withName">
    <qualifier value="kind"/>
    <constructor-arg name="name" value="Legolas"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="gandalf" factory-method="withName" factory-bean="mageFactory">
    <qualifier value="kind"/>
    <constructor-arg name="name" value="Gandalf"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="frodo" class="io.itlabs.springtraining.domain.person.Hobbit">
    <qualifier value="kind"/>
    <property name="name" value="Frodo"/>
    <property name="weapons">
        <list>
            <ref bean="dagger"/>
        </list>
    </property>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="sam" class="io.itlabs.springtraining.domain.person.Hobbit">
    <qualifier value="kind"/>
    <property name="name" value="Sam"/>
    <property name="weapons">
        <list>
            <ref bean="dagger"/>
        </list>
    </property>
</bean>
```

+++

@snap[north-east]
#### FellowshipOfTheRing.java
@snapend

```java
@Component
public class FellowshipOfTheRing implements PersonGroup<Person> {

    private final List<Person> persons;

    public FellowshipOfTheRing(@Qualifier("kind") List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public List<Person> persons() {
        return persons;
    }
}
```
@[6-8]

+++

@snap[north-west]
### Задание №6.2
Заполнить список участников братства кольца, *fellowshipOfTheRing*. Использовать свою аннотацию.
@snapend

+++

@snap[north-east]
#### Walker.java
@snapend

```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("kind")
public @interface Walker {

}
```
@snap[south span-60]
@[5-8](Qualifier - аннотация на аннотации)
@snapend

+++

@snap[north-east]
#### DomainBeanDefinitions.java
@snapend

```java
@Configuration
public class DomainBeanDefinitions {

    @Bean
    @Scope("prototype")
    public Weapon dagger() {
        return new Weapon(DAGGER);
    }

    @Bean
    @Walker
    public Human boromir() {
        return new Human("Boromir");
    }

    @Bean
    @Walker
    public Dwarf gimli() {
        return new Dwarf
                .DwarfBuilder()
                .withName("Gimli")
                .build();
    }

    @Bean
    @Walker
    public Hobbit meriadoc() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    @Walker
    public Hobbit pippin() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    public Mage saruman(MageFactory mageFactory) {
        return mageFactory.withName("Saruman");
    }

}
```
@[10-14]
@[16-23]
@[25-36]
@[38-49]

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="aragorn" class="io.itlabs.springtraining.domain.person.Human">
    <qualifier type="io.itlabs.springtraining.configuration.Walker"/>
    <constructor-arg name="name" value="Aragorn"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="legolas" class="io.itlabs.springtraining.domain.person.Elf" factory-method="withName">
    <qualifier type="io.itlabs.springtraining.configuration.Walker"/>
    <constructor-arg name="name" value="Legolas"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="gandalf" factory-method="withName" factory-bean="mageFactory">
    <qualifier type="io.itlabs.springtraining.configuration.Walker"/>
    <constructor-arg name="name" value="Gandalf"/>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="frodo" class="io.itlabs.springtraining.domain.person.Hobbit">
    <qualifier type="io.itlabs.springtraining.configuration.Walker"/>
    <property name="name" value="Frodo"/>
    <property name="weapons">
        <list>
            <ref bean="dagger"/>
        </list>
    </property>
</bean>
```

+++

@snap[north-east]
#### spring-configuration.xml
@snapend

```xml
<bean id="sam" class="io.itlabs.springtraining.domain.person.Hobbit">
    <qualifier type="io.itlabs.springtraining.configuration.Walker"/>
    <property name="name" value="Sam"/>
    <property name="weapons">
        <list>
            <ref bean="dagger"/>
        </list>
    </property>
</bean>
```

+++

@snap[north-east]
#### FellowshipOfTheRing.java
@snapend

```java
@Component
public class FellowshipOfTheRing implements PersonGroup<Person> {

    private final List<Person> persons;

    public FellowshipOfTheRing(@Walker List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public List<Person> persons() {
        return persons;
    }
}
```
@[6-8]

---

@snap[west]
### Полезные материалы
@ul
- [Статья М.Фаулера по инъекции зависимости @fa[external-link]](https://martinfowler.com/articles/injection.html)
- [Инъекция зависимости(Wiki) @fa[external-link]](https://ru.wikipedia.org/wiki/%D0%92%D0%BD%D0%B5%D0%B4%D1%80%D0%B5%D0%BD%D0%B8%D0%B5_%D0%B7%D0%B0%D0%B2%D0%B8%D1%81%D0%B8%D0%BC%D0%BE%D1%81%D1%82%D0%B8)
- [Spring Framework Docs @fa[external-link]](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-introduction)
- [Репозиторий проекта @fa[external-link]](https://github.com/spinrus-spring-courses/ioc)
@ulend
@snapend

---

@snap[center]
### Михаил Ильиных
@snapend

@snap[south]
#### milinyh@sdvor.com
[Telegram @fa[external-link]](https://t.me/ilinykhma)
@snapend
