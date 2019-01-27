package ru.cherkasov.w2d2;

public class Starter {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Worker worker = new WorkerImpl();


        JavaWorkCompile javaWorkCompile = new JavaWorkCompile();
        javaWorkCompile.compileWork(javaWorkCompile.getBodyMethod());
        ClassLoaderWorker cl = new ClassLoaderWorker();
        Class<?> aClass = cl.findClass("ru.cherkasov.w2d2.WorkerImpl");
        Object o = aClass.newInstance();
        worker = (Worker) o;

        worker.doWork();

    }
}
