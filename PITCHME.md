@snap[center]
### Lord of The Spring
#### Spring Framework: IoC, Dependency Injection
@snapend

---
@snap[center]
### Предметная область
@snapend

---

@snap[north span-100]
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
+++?code=src/main/java/io/itlabs/springtraining/domain/person/Hobbit.java&lang=java&position=left
@[3-5]

@snap[north span-100]
#### Hobbit.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Human.java&lang=java&position=left
@[5-7]

@snap[north span-100]
#### Human.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Elf.java&lang=java&position=left
@[5-7]
@[9-11]

@snap[north span-100]
#### Elf.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Dwarf.java&lang=java&position=left
@[5-20]

@snap[north span-100]
#### Dwarf.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/Mage.java&lang=java&position=left
@[5-7]

@snap[north span-100]
#### Mage.java
@snapend

+++?code=src/main/java/io/itlabs/springtraining/domain/person/MageFactory.java&lang=java&position=left
@[3-8]

@snap[north span-100]
#### MageFactory.java
@snapend

---

@snap[center]
### А где же Spring?
@snapend

---

@snap[north span-100]
#### SpringTrainingApplication.java
@snapend

```java
package io.itlabs.springtraining;

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

@snap[north-west span-55]
#### Задание №1. Объявление бинов, XML.
@snapend

@snap[west span-55]
@ul[list-spaced-bullets text-09]
- Объявить бины: **frodo, sam, aragorn, legolas, gandalf** - через XML конфигурацию.
- Вывести в консоль атрубут **name** всех объявленных бинов.
@ulend
@snapend

---

@snap[north-west]
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="aragorn" class="io.itlabs.springtraining.domain.person.Human">
        <constructor-arg name="name" value="Aragorn"/>
    </bean>

    <bean id="legolas" class="io.itlabs.springtraining.domain.person.Elf" 
        factory-method="withName">
        <constructor-arg name="name" value="Legolas"/>
    </bean>

    <bean id="mageFactory" class="io.itlabs.springtraining.domain.person.MageFactory"/>

    <bean id="gandalf" factory-method="withName" factory-bean="mageFactory">
        <constructor-arg name="name" value="Gandalf"/>
    </bean>

    <bean id="frodo" class="io.itlabs.springtraining.domain.person.Hobbit">
        <property name="name" value="Frodo"/>
    </bean>

    <bean id="sam" class="io.itlabs.springtraining.domain.person.Hobbit">
        <property name="name" value="Sam"/>
    </bean>

</beans>
```
@[7-9]
@[11-14]
@[16, 18-20]
@[22-28]
@snapend

---

@snap[north span-100]
#### SpringTrainingApplication.java
@snapend

```java
@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringTrainingApplication.class);

    public static void main(String[] args) {
        final var applicationContext = SpringApplication.run(SpringTrainingApplication.class, args);
        Stream.of(applicationContext.getBeanNamesForType(Person.class)).forEach(LOG::info);
    }

}
```
@[8]
@[9]

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


---?image=assets/img/code.jpg&opacity=60&position=left&size=45% 100%

@snap[east span-50 text-center]
## Now It's **Your** Turn
@snapend

@snap[south-east span-50 text-center text-06]
[Download GitPitch Desktop @fa[external-link]](https://gitpitch.com/docs/getting-started/tutorial/)
@snapend

