package tacos.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Ingredient {
    /*Lombok的@Data注解 和 JPA的@Entity 注解在这里出现了一些冲突问题，总之直接用IDEA提供的自动生成功能形成本代码*/

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public Ingredient() {
        this.id = null;
        this.name = null;
        this.type = null;
    }


    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ingredient that = (Ingredient) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}