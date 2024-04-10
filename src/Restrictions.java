public interface Restrictions {
    boolean cannotAdd(String[] info);
}

interface Limitations extends Restrictions{
    boolean limitationApplied(String[] info);
}
