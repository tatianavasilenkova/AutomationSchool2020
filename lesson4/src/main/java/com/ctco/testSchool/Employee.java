class A {

    protected int a;
}

class B extends A {

    protected int b;

    public B(int a, int b) {
        super();     // 1
        super.a = a; // 2
        super.b = b; // 3
    }
}

class C extends B {

    protected int c;

    public C(int a, int b) {
        super(a); // 4
    }

    public C(int a) {
        super(a, 10); // 5
    }
}