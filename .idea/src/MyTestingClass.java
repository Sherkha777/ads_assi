public class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;

        // Mix the id
        result = prime * result + id;

        // Mix each character of name using a polynomial hash
        if (name != null) {
            for (int i = 0; i < name.length(); i++) {
                result = prime * result + name.charAt(i);
            }
        }

        // Extra avalanche step to spread bit patterns
        result ^= (result >>> 16);
        result *= 0x45d9f3b;
        result ^= (result >>> 16);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) o;
        return id == other.id && (name == null ? other.name == null : name.equals(other.name));
    }

    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name='" + name + "'}";
    }
}