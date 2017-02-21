package com;

import javax.persistence.*;

/**
 * Created by Makc on 19.02.2017.
 */
@Entity
@Table(name = "checkboxes", schema = "test", catalog = "")
public class CheckboxesEntity {
    private int id;
    private Integer checkboxRow;
    private Integer checkboxCol;

    public CheckboxesEntity() {
    }

    public CheckboxesEntity(int id) {
        this.id = id;
    }

    public CheckboxesEntity(Integer checkboxRow, Integer checkboxCol) {
        this.checkboxRow = checkboxRow;
        this.checkboxCol = checkboxCol;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "checkbox_row", nullable = true)
    public Integer getCheckboxRow() {
        return checkboxRow;
    }

    public void setCheckboxRow(Integer checkboxRow) {
        this.checkboxRow = checkboxRow;
    }

    @Basic
    @Column(name = "checkbox_col", nullable = true)
    public Integer getCheckboxCol() {
        return checkboxCol;
    }

    public void setCheckboxCol(Integer checkboxCol) {
        this.checkboxCol = checkboxCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckboxesEntity that = (CheckboxesEntity) o;

        if (id != that.id) return false;
        if (checkboxRow != null ? !checkboxRow.equals(that.checkboxRow) : that.checkboxRow != null) return false;
        if (checkboxCol != null ? !checkboxCol.equals(that.checkboxCol) : that.checkboxCol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (checkboxRow != null ? checkboxRow.hashCode() : 0);
        result = 31 * result + (checkboxCol != null ? checkboxCol.hashCode() : 0);
        return result;
    }
}