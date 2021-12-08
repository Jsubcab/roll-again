package rollagain.main.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Categories implements Serializable
{
    @Id
    @SequenceGenerator(
        name = "categories_identity",
        sequenceName = "categories_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "categories_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private String category;
    @OneToMany(mappedBy = "category", cascade=CascadeType.ALL)
    private Set<Products> products = new HashSet<>();

    public Categories() {

    }

    public Categories(final Long id, final String category)
    {
        this.id = id;
        this.category = category;
    }

    public Categories(final String category)
    {
        this.category = category;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(final String category)
    {
        this.category = category.toLowerCase();
    }

    public Set<Products> getProducts()
    {
        return products;
    }
    {
        this.products = products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;

        for(Products p : products) {
            p.setCategory(this);
        }
    }

    @Override
    public String toString()
    {
        return "Categories{" +
            "id=" + id +
            ", category='" + category + '\'' +
            ", products=" + products +
            '}';
    }
}
