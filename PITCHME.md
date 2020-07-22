@snap[center]
### Lord of The Spring
#### Spring Framework: IoC, Dependency Injection
@snapend

---

@snap[center]
### Предметная область
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

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Hobbit.java&lang=java
@[3-5]

@snap[north-east]
#### Hobbit.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Human.java&lang=java
@[5-7]

@snap[north-east]
#### Human.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Elf.java&lang=java
@[5-7]
@[9-11]

@snap[north-east]
#### Elf.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Dwarf.java&lang=java
@[5-20]

@snap[north-east]
#### Dwarf.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Mage.java&lang=java
@[5-7]

@snap[north-east]
#### Mage.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/MageFactory.java&lang=java
@[3-8]

@snap[north-east]
#### MageFactory.java
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
@[1]
@[2]
@[5-7]

---

@snap[north-west]
#### Задание №1. Объявление бинов, XML.
@snapend

@snap[west]
@ul[list-spaced-bullets]
- Объявить бины: **frodo, sam, aragorn, legolas, gandalf** - через XML конфигурацию.
- Вывести в консоль атрубут **name** всех объявленных бинов.
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
@[9-10]
@[12-14]

---

@snap[north-west]
#### Задание №2. Объявление бинов, аннотации.
@snapend

@snap[west]
@ul[list-spaced-bullets]
- Объявить бин сервиса приветствия **greetingService**, типа *LoggingGreetingService*.
- Задать значение для атрибута **greeting**.
- Получить из контекста все бины. Вызвать метод **greet** бина **greetingService** для каждого из бинов типа *Person*.
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

---

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
@[1]
@[4]

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
@[7-8]
@[10-11]
@[13-16]

---

@snap[north-west]
#### Задание №3. Объявление бинов, Java.
@snapend

@snap[west]
@ul[list-spaced-bullets]
- Объявить бины: **boromir, gimli, meriadoc, pippin, saruman** - через Java конфигурацию.
- Опционально: переписать XML объявления на Java.
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
@[1]
@[4-7]
@[9-15]
@[17-23]
@[25-31]
@[33-36]

---

@snap[north-west]
#### Задание №4. Инъекция зависимости.
@snapend

@snap[west]
Привязать бины хоббитов как атрибуты бина **shire** разными способами:
@ul[list-spaced-bullets]
- Инъекция атрибута (field injection).
- Инъекция через сеттер (setter-based injection).
- Инъекция через конструктор (constructor-based injection).
- Вывести имена всех жителей Шира(*Shire*) c помощью метода **persons** бина **shire**.
@ulend
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

