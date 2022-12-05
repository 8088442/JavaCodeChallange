package coding.challenge.common.persistence;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public abstract class AbstractUuidIdEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    @Getter
    protected UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof AbstractUuidIdEntity that)) { return false; }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
