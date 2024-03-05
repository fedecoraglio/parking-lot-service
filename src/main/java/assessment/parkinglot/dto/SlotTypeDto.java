package assessment.parkinglot.dto;

public class SlotTypeDto {
    private Long id;
    private String name;
    private String alias;

    public SlotTypeDto() {
    }

    public SlotTypeDto(Long id, String name, String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
