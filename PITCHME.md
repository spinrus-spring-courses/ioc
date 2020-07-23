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

+++?color=linear-gradient(90deg, white 50%, gold 50%)

@snap[north-west]
### Задание №1.1
@snapend

@snap[west span-45]
Объявить бины в 
*spring-configuration.xml*
@snapend

@snap[east span-45]
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

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

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
- Получить из контекста все типа *Person*. 
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

+++?color=linear-gradient(90deg, white 50%, gold 50%)

@snap[north-west]
#### Задание №3.1
@snapend

@snap[west span-45]
Объявить бины
@snapend

@snap[east span-45]
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

+++

@snap[north-west]
#### Задание №4.1
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
#### Задание №4.2
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
#### Задание №4.3
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
#### Задание №4.4
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
#### Задание №4.5
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

@snap[north-west]
#### Задание №5. Scope.
@snapend

@snap[west text-08]
Привязать бины хоббитов как атрибуты бина *shire* разными способами:
@ul[list-spaced-bullets]
- Вооружить всех хоббитов кинжалами. Для этого нужно объявить бин *dagger* и модифицировать объявления хоббитов.
- Добавить вывод информации об оружии в *LoggingGreetingService*.
- В методе main изменить атрибут *broken* бина *dagger* на *true*. Повторить вывод в консоль.
- Изменить *scope* бина *dagger* на *prototype*.
@ulend
@snapend

---

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
@[4-7]
@[9-19]
@[21-31]

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
@[1-8]
@[10-18]

+++



---

### Add Some Slide Candy

![IMAGE](assets/img/presentation.png)

---?color=linear-gradient(180deg, white 75%, black 25%)
@title[Customize Slide Layout]

@snap[west span-55]
## Customize the Layout
@snapend

@snap[north-east span-45]
![IMAGE](assets/img/presentation.png)
@snapend

@snap[south span-100]
Snap Layouts let you create custom slide designs directly within your markdown.
@snapend

---
@title[Add A Little Imagination]

@snap[north-west span-50 text-center]
#### Engage your Audience
@snapend

@snap[west span-55]
@ul[list-spaced-bullets text-09]
- You will be amazed
- What you can achieve
- With a **little imagination**
- And GitPitch Markdown
@ulend
@snapend

@snap[east span-45]
![IMAGE](assets/img/conference.png)
@snapend

@snap[south span-100 bg-black fragment]
@img[shadow](assets/img/conference.png)
@snapend

---

@snap[north-east span-100 text-pink text-06]
Let your code do the talking!
@snapend

```sql zoom-18
CREATE TABLE "topic" (
    "id" serial NOT NULL PRIMARY KEY,
    "forum_id" integer NOT NULL,
    "subject" varchar(255) NOT NULL
);
ALTER TABLE "topic"
ADD CONSTRAINT forum_id
FOREIGN KEY ("forum_id")
REFERENCES "forum" ("id");
```

@snap[south span-100 text-gray text-08]
@[1-5](You can step-and-ZOOM into fenced-code blocks, source files, and Github GIST.)
@[6,7, zoom-13](Using GitPitch live code presenting with optional annotations.)
@[8-9, zoom-12](This means no more switching between your slide deck and IDE on stage.)
@snapend


---?image=assets/img/code.jpg&opacity=60&size=45% 100%

@snap[east span-50 text-center]
## Now It's **Your** Turn
@snapend

@snap[south-east span-50 text-center text-06]
[Download GitPitch Desktop @fa[external-link]](https://gitpitch.com/docs/getting-started/tutorial/)
@snapend

