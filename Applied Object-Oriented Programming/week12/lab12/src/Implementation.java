interface IBase {
    default void foo() {
        System.out.println("Calling IBase.foo()");
    }
}
interface IDerived {
    default void foo() {
        System.out.println("Calling IDerived.foo()");
    }
}
class Implementation implements IDerived, IBase {
    public static void main(String[] args) {
        Implementation implementation = new Implementation();
        implementation.foo();
    }

    @Override
    public void foo() {
            IDerived.super.foo();
    }
}
