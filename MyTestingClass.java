public class MyTestingClass {
    private int id;
    private String code;

    public MyTestingClass(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = id;

        if (code != null) {
            for (int i = 0; i < code.length(); i++) {
                hash = hash * 31 + code.charAt(i);
            }
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyTestingClass)) {
            return false;
        }

        MyTestingClass other = (MyTestingClass) obj;

        if (id != other.id) {
            return false;
        }

        if (code == null) {
            return other.code == null;
        }

        return code.equals(other.code);
    }

    @Override
    public String toString() {
        return id + "-" + code;
    }
}
