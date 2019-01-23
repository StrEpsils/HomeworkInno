package ru.cherkasov.w2d2;

public class Starter {

    public static void main(String[] args) throws ClassNotFoundException {
        Worker worker = new WorkerImpl();


        JavaWorkCompile javaWorkCompile = new JavaWorkCompile();
        javaWorkCompile.compileWork(javaWorkCompile.getBodyMethod());
        ClassLoaderWorker cl = new ClassLoaderWorker();
        cl.findClass("ru.cherkasov.w2d2.WorkerImpl");

        worker.doWork();

    }
}
