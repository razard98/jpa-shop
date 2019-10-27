package jpabook.jpashop.domain.items;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughtStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categorise = new ArrayList<>();

    //==비지니스 로직==//

    /**
     * stock 증가
     *
     * @param quantity 수량
     */
    public void addSock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     *
     * @param quantity 수량
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughtStockException("");
        }
        this.stockQuantity = restStock;
    }

}
