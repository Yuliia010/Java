
class OblastApiModel {
    private String ref;
    private String description;
    public OblastApiModel(String ref, String description) {
        this.ref = ref;
        this.description = description;
    }

    public String getRef() {
        return ref;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return  description + " область";
    }
}
